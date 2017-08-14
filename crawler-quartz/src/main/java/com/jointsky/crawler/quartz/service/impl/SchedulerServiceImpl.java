package com.jointsky.crawler.quartz.service.impl;

import com.jointsky.crawler.entity.mapper.JobInfoMapper;
import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.quartz.common.Constant;
import com.jointsky.crawler.quartz.common.JobStatus;
import com.jointsky.crawler.quartz.common.utils.IDGenerator;
import com.jointsky.crawler.quartz.factory.QuartzJobFactory;
import com.jointsky.crawler.quartz.model.AbstractExecutableJob;
import com.jointsky.crawler.quartz.service.SchedulerService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Author  zhangxiong
 * Date    17-7-26 下午1:52
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {


    private static Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobInfoMapper jobInfoMapper;

    @Override
    public boolean addJob(AbstractExecutableJob job) {
        try {
            //获取TriggerKey
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //从数据库中查询触发器
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //不存在则新建一个触发器
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).storeDurably(true)
                        .build();
                jobDetail.getJobDataMap().put(Constant.JOB_PARAM_KEY, job);
                //表达式调度构建器
                CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(cronBuilder).withDescription(job.getJobInfo().getDescription()).build();
                scheduler.scheduleJob(jobDetail, trigger);
                job.jobInfo.setJobId(IDGenerator.getUUID(job.getJobName() + job.getJobGroup()));
                job.jobInfo.setJobStatus(JobStatus.NORMAL);
                job.jobInfo.setAddTime(new Date());
                job.jobInfo.setUpdateTime(new Date());
                jobInfoMapper.insertSelective(job.jobInfo);
            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronBuilder).withDescription(job.getJobInfo().getDescription()).build();
                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
                job.jobInfo.setJobId(IDGenerator.getUUID(job.getJobName() + job.getJobGroup()));
                job.jobInfo.setUpdateTime(new Date());
                job.jobInfo.setJobStatus(JobStatus.NORMAL);
                jobInfoMapper.updateByPrimaryKey(job.jobInfo);
            }
            return true;
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateJob(JobInfo jobInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getJobName(), jobInfo.getJobGroup());

            JobDetail jobDetail = scheduler.getJobDetail(getJobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
            //jobDetail = jobDetail.getJobBuilder().ofType(jobClass).build();
            //更新参数 实际测试中发现无法更新
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            jobDataMap.put(Constant.JOB_PARAM_KEY, jobInfo);
            jobDetail.getJobBuilder().usingJobData(jobDataMap);

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //表达式调度构建器
            CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(jobInfo.getDescription())
                    .withSchedule(cronBuilder).build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            jobInfo.setJobId(IDGenerator.getUUID(jobInfo.getJobName() + jobInfo.getJobGroup()));
            jobInfo.setUpdateTime(new Date());
            jobInfo.setJobStatus(JobStatus.NORMAL);
            jobInfoMapper.updateByPrimaryKey(jobInfo);
            return true;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public List<JobInfo> getScheduledJobList() {
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            List<JobInfo> jobList = new ArrayList<JobInfo>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    JobInfo job = new JobInfo();
                    job.setJobName(jobKey.getName());
                    job.setJobGroup(jobKey.getGroup());
                    job.setDescription(trigger.getDescription());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setJobStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    jobList.add(job);
                }
            }
            return jobList;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public List<JobInfo> getRunningJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<JobInfo> jobList = new ArrayList<JobInfo>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                JobInfo job = new JobInfo();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription(trigger.getDescription());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public boolean pauseJob(JobInfo jobInfo) {
        try {
            JobKey jobKey = getJobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
            scheduler.pauseJob(jobKey);
            String jobId = IDGenerator.getUUID(jobInfo.getJobName() + jobInfo.getJobGroup());
            jobInfoMapper.updateJobStatusByJobId(jobId, JobStatus.PAUSE, new Date());
            return true;
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean resumeJob(JobInfo jobInfo) {
        try {
            JobKey jobKey = getJobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
            scheduler.resumeJob(jobKey);
            String jobId = IDGenerator.getUUID(jobInfo.getJobName() + jobInfo.getJobGroup());
            jobInfoMapper.updateJobStatusByJobId(jobId, JobStatus.NORMAL, new Date());
            return true;
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteJob(JobInfo jobInfo) {
        try {
            JobKey jobKey = getJobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
            scheduler.deleteJob(jobKey);
            String jobId = IDGenerator.getUUID(jobInfo.getJobName() + jobInfo.getJobGroup());
            jobInfoMapper.updateJobStatusByJobId(jobId, JobStatus.DELETE, new Date());
            return true;
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }


    private JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

}
