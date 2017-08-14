package com.jointsky.crawler.quartz.model;

import com.jointsky.crawler.entity.model.JobInfo;

import java.io.Serializable;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午3:40
 */
public abstract class AbstractExecutableJob implements IExecutable, Serializable {
    public JobInfo jobInfo;

    public AbstractExecutableJob(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }


    public JobInfo getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getJobName() {
        return jobInfo.getJobName();
    }

    public String getJobGroup() {
        return jobInfo.getJobGroup();
    }

    public String getCronExpression() {
        return jobInfo.getCronExpression();
    }

    public String getPageProcessorClass() {
        return jobInfo.getPageProcessorClass();
    }

}
