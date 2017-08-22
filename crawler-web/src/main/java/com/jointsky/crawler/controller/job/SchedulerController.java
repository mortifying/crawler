package com.jointsky.crawler.controller.job;

import com.alibaba.fastjson.JSON;
import com.jointsky.crawler.entity.model.BusinessCategory;
import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.entity.model.Params;
import com.jointsky.crawler.entity.model.Region;
import com.jointsky.crawler.quartz.common.utils.DefaultServiceResult;
import com.jointsky.crawler.quartz.job.ExecutableJob;
import com.jointsky.crawler.quartz.service.SchedulerService;
import com.jointsky.crawler.service.datastats.DataStatsBusinessService;
import com.jointsky.crawler.service.datastats.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午4:48
 */
@RestController
@RequestMapping(value = "/scheduler")
@Api(description = "任务信息接口")
public class SchedulerController {

    Logger LOGGER = LoggerFactory.getLogger(SchedulerController.class);

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private DataStatsBusinessService dataStatsBusinessService;

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "添加任务接口")
    @PostMapping("/add")
    @ResponseBody
    public DefaultServiceResult addJob(@RequestBody JobInfo jobInfo) {
        boolean result = schedulerService.addJob(new ExecutableJob(jobInfo));
        if (result) {
            return new DefaultServiceResult(true, "添加任务成功");
        }
        return new DefaultServiceResult(false, "添加任务失败");
    }


    @ApiOperation(value = "批量添加任务接口")
    @PostMapping("/addbatch")
    @ResponseBody
    public DefaultServiceResult addBatchJob(@RequestBody JobInfo jobInfo) {
        String paramJson = jobInfo.getParams();
        Params params = JSON.parseObject(paramJson, Params.class);
        List<BusinessCategory> businessCategoryList = dataStatsBusinessService.findBusinessCategoryByHasParent("1");
        for (BusinessCategory bc : businessCategoryList) {
            params.setId(bc.getBid());
            String jsonParam = JSON.toJSONString(params);
            jobInfo.setJobName(bc.getName());
            jobInfo.setJobGroup(bc.getId());
            jobInfo.setParams(jsonParam);
            boolean result = schedulerService.addJob(new ExecutableJob(jobInfo));
            if (!result) {
                return new DefaultServiceResult(false, "添加任务失败");
            }
        }
        return new DefaultServiceResult(true, "添加任务成功");
    }


    @ApiOperation(value = "省月数据添加任务接口")
    @PostMapping("/add_province_month_data")
    @ResponseBody
    public DefaultServiceResult addProvinceMonthJob(@RequestBody JobInfo jobInfo) {
        String paramJson = "";
        List<Region> regionList = regionService.findAll();
        String sbUrl = "";
        String url = jobInfo.getCrawlerUrl();
        for (Region region : regionList) {
            paramJson = jobInfo.getParams().replace("'", "\"");
            String wds = "[{\"wdcode\":\"reg\",\"valuecode\":\"" + region.getId() + "\"}]";
            try {
                wds = URLEncoder.encode(wds, "UTF-8");
                paramJson = URLEncoder.encode(paramJson, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return new DefaultServiceResult(false, "添加任务失败");
            }
            sbUrl = url +
                    "&wds=" + wds +
                    "&dfwds=" + paramJson +
                    "&k1=" + System.currentTimeMillis();
            System.out.println("length:" + sbUrl.length() + ",content:" + sbUrl);
            jobInfo.setCrawlerUrl(sbUrl);
            jobInfo.setJobName(region.getName());
            jobInfo.setJobGroup(region.getId());
            sbUrl = "";
            boolean result = schedulerService.addJob(new ExecutableJob(jobInfo));
            if (!result) {
                return new DefaultServiceResult(false, "添加任务失败");
            }
        }
        return new DefaultServiceResult(true, "添加任务成功");
    }


    @ApiOperation(value = "暂停任务信息接口")
    @PostMapping("/pause")
    @ResponseBody
    public DefaultServiceResult pause(@RequestBody JobInfo jobInfo) {
        boolean result = schedulerService.pauseJob(jobInfo);
        if (result) {
            return new DefaultServiceResult(true, "暂停任务成功");
        }
        return new DefaultServiceResult(false, "暂停任务失败");
    }

    @ApiOperation(value = "恢复任务信息接口")
    @PostMapping("/resume")
    @ResponseBody
    public DefaultServiceResult resume(@RequestBody JobInfo jobInfo) {
        System.out.println(jobInfo.getJobName() + "," + jobInfo.getJobGroup());
        boolean result = schedulerService.resumeJob(jobInfo);
        if (result) {
            return new DefaultServiceResult(true, "恢复任务成功");
        }
        return new DefaultServiceResult(false, "恢复任务失败");
    }

    @ApiOperation(value = "删除任务接口")
    @DeleteMapping("/delete")
    @ResponseBody
    public DefaultServiceResult deleteJob(@RequestBody JobInfo jobInfo) {
        boolean result = schedulerService.deleteJob(jobInfo);
        if (result) {
            return new DefaultServiceResult(true, "删除任务成功");
        }
        return new DefaultServiceResult(false, "删除任务失败");
    }

}
