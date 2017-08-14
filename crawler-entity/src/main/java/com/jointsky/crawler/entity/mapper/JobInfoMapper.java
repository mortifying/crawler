package com.jointsky.crawler.entity.mapper;

import com.jointsky.crawler.entity.model.JobInfo;
import com.jointsky.crawler.entity.util.CrawlerMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface JobInfoMapper extends CrawlerMapper<JobInfo> {


    @Select("update job_info set job_status=#{jobStatus},update_time=#{updateTime} where job_id=#{jobId}")
    void updateJobStatusByJobId(@Param("jobId") String jobId, @Param("jobStatus") String jobStatus, @Param("updateTime") Date updateTime);


    @Select("select * from job_info where jobStatus=#{jobStatus}")
    List<JobInfo> getJobInfoListByJobStatus(@Param("jobStatus") String jobStatus);

}