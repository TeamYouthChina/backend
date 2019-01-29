package com.youthchina.dao.qingyang;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.domain.qingyang.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface JobMapper {
    /**insert Job*/
    Integer insertJob(Job job);

    /**update Job*/
    Integer updateJob(Job job);

    /**delete Job*/
    Integer deleteJob(Integer id);

    /**select Job information by Job_ID*/
    Job selectJobByJobId(Integer id);

    /**select Job information by Job_ID List*/
    List<Job> selectJobByJobIdList(List<Integer> ids);

    List<Job> getJobByMore(Integer jobId, String jobName, Integer comId, String comName, Integer location, Integer type, String deadline);

    List<Job> selectByIndustryId(List<Integer> indIds);

    List<Job> selectByIndustryString(String ind);

    void insertJobIndustry(List<Industry> industries);

    void insertJobDegree(List<Degree> degrees);

    void insertJobLocation(List<Location> locations);

    void deleteJobLocation(Integer jobId);

    void deleteJobIndustry(Integer jobId);

    void deleteJobDegree(Integer jobId);
}

