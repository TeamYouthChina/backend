package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.JobLocation;
import com.youthchina.domain.qingyang.Job_qingyang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Mapper
@Component
public interface JobHrMapper {
    /**insert Job*/
    Integer insertJob(Job_qingyang job);

    /**update Job*/
    Integer updateJob(Job_qingyang job);

    /**delete Job*/
    Integer deleteJob(Integer job_id);

    /**select Job information by Job_ID*/
    Job_qingyang selectJobByJobId(Integer job_id);

    /**select Job information by Job_ID List*/
    List<Job_qingyang> selectJobByJobIdList(List<Integer> id);

    List<Job_qingyang> selectByIndustryId(List<Integer> indIds);

    Map<String, List<Job_qingyang>> getJobByIndustries(@Param("industries") List<String> industries);

    List<Job_qingyang> getJobByMore(Integer jobId, String jobName, Integer comId, String comName, Integer location, Integer type, String deadline);

    void deleteJobLocation(Integer id);

    JobLocation getJobLocation(Integer id);

    List<JobLocation> getJobLocationList(List<Integer> ids);

    Integer updateJobLocation(JobLocation jobLocation);

    Integer insertJobLocation(JobLocation entity);


}

