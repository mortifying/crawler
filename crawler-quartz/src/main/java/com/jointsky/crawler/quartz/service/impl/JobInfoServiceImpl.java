package com.jointsky.crawler.quartz.service.impl;

import com.github.pagehelper.Page;
import com.jointsky.crawler.entity.mapper.JobInfoMapper;
import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.quartz.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-7-28 下午1:59
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {


    @Autowired
    private JobInfoMapper jobInfoMapper;

    @Override
    public List<JobInfo> getJobInfoListByJobStatus(String jobStatus) {
        return jobInfoMapper.getJobInfoListByJobStatus(jobStatus);
    }

    @Override
    public List<JobInfo> getJobInfoList(Integer pageNum, Integer pageSize) {
        return (Page<JobInfo>) jobInfoMapper.selectAll();
    }
}
