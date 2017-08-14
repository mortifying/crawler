package com.jointsky.crawler.controller.job;

import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.quartz.common.utils.DefaultServiceResult;
import com.jointsky.crawler.quartz.job.ExecutableJob;
import com.jointsky.crawler.quartz.service.SchedulerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
