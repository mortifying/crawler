package com.jointsky.crawler.quartz.service;

import com.jointsky.crawler.entity.model.JobInfo;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-7-28 下午1:55
 */
public interface JobInfoService {


    /**
     * 获取Jobinfo列表
     *
     * @param jobStatus job的状态0：正常，1：暂停，-1：删除
     * @return
     */
    List<JobInfo> getJobInfoListByJobStatus(String jobStatus);


    /**
     * 获取所有的Job列表
     *
     * @return
     */
    List<JobInfo> getJobInfoList(Integer pageNum, Integer pageSize);

}
