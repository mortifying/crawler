package com.jointsky.crawler.controller.job;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.quartz.common.utils.DefaultServiceResult;
import com.jointsky.crawler.quartz.service.JobInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author  zhangxiong
 * Date    17-7-28 下午2:20
 */
@RestController
@RequestMapping(value = "/job")
@Api(description = "Job信息接口")
public class JobInfoController {


    @Autowired
    private JobInfoService jobInfoService;

    @ApiOperation(value = "根据Job状态获取JobInfo列表接口")
    @PostMapping("/list/status")
    @ResponseBody
    public DefaultServiceResult getJobInfoListByJobStatus(@RequestParam(value = "jobStatus") String jobStatus) {
        return new DefaultServiceResult(jobInfoService.getJobInfoListByJobStatus(jobStatus));
    }

    @ApiOperation(value = "获取所有Job列表")
    @PostMapping("/list")
    @ResponseBody
    public DefaultServiceResult getJobInfoList(@RequestParam(value = "pageNum") Integer pageNum,
                                               @RequestParam(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new DefaultServiceResult(new PageInfo<JobInfo>(jobInfoService.getJobInfoList(pageNum, pageSize)));
    }

}
