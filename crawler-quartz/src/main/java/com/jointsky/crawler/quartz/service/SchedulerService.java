package com.jointsky.crawler.quartz.service;


import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.quartz.model.AbstractExecutableJob;

import java.util.List;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午1:50
 */
public interface SchedulerService {


    /**
     * 添加Job
     *
     * @param job
     * @return
     */
    boolean addJob(AbstractExecutableJob job);

    /**
     * 更新一个job
     *
     * @param jobInfo
     * @return
     */
    boolean updateJob(JobInfo jobInfo);


    /**
     * 获取计划
     *
     * @return
     */
    List<JobInfo> getScheduledJobList();


    /**
     * 获取正在运行的Job
     *
     * @return
     */
    List<JobInfo> getRunningJobList();


    /**
     * 暂停一个Job
     *
     * @param jobInfo
     * @return
     */
    boolean pauseJob(JobInfo jobInfo);


    /**
     * 恢复一个JOB
     *
     * @param jobInfo
     * @return
     */
    boolean resumeJob(JobInfo jobInfo);


    /**
     * 删除一个Job
     *
     * @param jobInfo
     * @return
     */
    boolean deleteJob(JobInfo jobInfo);
}
