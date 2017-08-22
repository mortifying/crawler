package com.jointsky.crawler.entity.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "job_info")
public class JobInfo implements Serializable {
    /**
     * 任务 id
     */
    @Id
    @Column(name = "job_id")
    private String jobId;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务分组
     */
    @Column(name = "job_group")
    private String jobGroup;

    /**
     * 任务状态0：正常，1：暂停，-1：删除
     */
    @Column(name = "job_status")
    private String jobStatus;

    /**
     * page process类名全路径
     */
    @Column(name = "page_processor_class")
    private String pageProcessorClass;

    /**
     * pipeline类名全路径
     */
    @Column(name = "pipeline_class")
    private String pipelineClass;

    /**
     * crawler url
     */
    @Column(name = "crawler_url")
    private String crawlerUrl;

    /**
     * url请求方式
     */
    private String type;

    /**
     * POST请求事，请求参数，json格式
     */
    private String params;

    /**
     * 线程数
     */
    @Column(name = "thread_num")
    private Integer threadNum;

    /**
     * 1:true,0:false
     */
    @Column(name = "exit_whith_complete")
    private String exitWhithComplete;

    /**
     * 任务运行时间表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public JobInfo(String jobId, String jobName, String jobGroup, String jobStatus, String pageProcessorClass, String pipelineClass, String crawlerUrl, String type, String params, Integer threadNum, String exitWhithComplete, String cronExpression, String description, Date addTime, Date updateTime) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobStatus = jobStatus;
        this.pageProcessorClass = pageProcessorClass;
        this.pipelineClass = pipelineClass;
        this.crawlerUrl = crawlerUrl;
        this.type = type;
        this.params = params;
        this.threadNum = threadNum;
        this.exitWhithComplete = exitWhithComplete;
        this.cronExpression = cronExpression;
        this.description = description;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public JobInfo() {
        super();
    }

    /**
     * 获取任务 id
     *
     * @return job_id - 任务 id
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * 设置任务 id
     *
     * @param jobId 任务 id
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * 获取任务名称
     *
     * @return job_name - 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置任务名称
     *
     * @param jobName 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * 获取任务分组
     *
     * @return job_group - 任务分组
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * 设置任务分组
     *
     * @param jobGroup 任务分组
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    /**
     * 获取任务状态0：正常，1：暂停，-1：删除
     *
     * @return job_status - 任务状态0：正常，1：暂停，-1：删除
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置任务状态0：正常，1：暂停，-1：删除
     *
     * @param jobStatus 任务状态0：正常，1：暂停，-1：删除
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    /**
     * 获取page process类名全路径
     *
     * @return page_processor_class - page process类名全路径
     */
    public String getPageProcessorClass() {
        return pageProcessorClass;
    }

    /**
     * 设置page process类名全路径
     *
     * @param pageProcessorClass page process类名全路径
     */
    public void setPageProcessorClass(String pageProcessorClass) {
        this.pageProcessorClass = pageProcessorClass == null ? null : pageProcessorClass.trim();
    }

    /**
     * 获取pipeline类名全路径
     *
     * @return pipeline_class - pipeline类名全路径
     */
    public String getPipelineClass() {
        return pipelineClass;
    }

    /**
     * 设置pipeline类名全路径
     *
     * @param pipelineClass pipeline类名全路径
     */
    public void setPipelineClass(String pipelineClass) {
        this.pipelineClass = pipelineClass == null ? null : pipelineClass.trim();
    }

    /**
     * 获取crawler url
     *
     * @return crawler_url - crawler url
     */
    public String getCrawlerUrl() {
        return crawlerUrl;
    }

    /**
     * 设置crawler url
     *
     * @param crawlerUrl crawler url
     */
    public void setCrawlerUrl(String crawlerUrl) {
        this.crawlerUrl = crawlerUrl == null ? null : crawlerUrl.trim();
    }

    /**
     * 获取url请求方式
     *
     * @return type - url请求方式
     */
    public String getType() {
        return type;
    }

    /**
     * 设置url请求方式
     *
     * @param type url请求方式
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取POST请求事，请求参数，json格式
     *
     * @return params - POST请求事，请求参数，json格式
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置POST请求事，请求参数，json格式
     *
     * @param params POST请求事，请求参数，json格式
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 获取线程数
     *
     * @return thread_num - 线程数
     */
    public Integer getThreadNum() {
        return threadNum;
    }

    /**
     * 设置线程数
     *
     * @param threadNum 线程数
     */
    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    /**
     * 获取1:true,0:false
     *
     * @return exit_whith_complete - 1:true,0:false
     */
    public String getExitWhithComplete() {
        return exitWhithComplete;
    }

    /**
     * 设置1:true,0:false
     *
     * @param exitWhithComplete 1:true,0:false
     */
    public void setExitWhithComplete(String exitWhithComplete) {
        this.exitWhithComplete = exitWhithComplete == null ? null : exitWhithComplete.trim();
    }

    /**
     * 获取任务运行时间表达式
     *
     * @return cron_expression - 任务运行时间表达式
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * 设置任务运行时间表达式
     *
     * @param cronExpression 任务运行时间表达式
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    /**
     * 获取任务描述
     *
     * @return description - 任务描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置任务描述
     *
     * @param description 任务描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}