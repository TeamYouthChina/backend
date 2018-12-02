package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface JobHrMapper {
    /**insert Job*/
    Integer insertJob(Job job);

    /**update Job*/
    Integer updateJob(Job job);

    /**delete Job*/
    Integer deleteJob(Integer job_id);

    /**select Job information by Job_ID*/
    Job selectJobByJobId(Integer job_id);

    /**select Job information by Job_ID List*/
    List<Job> selectJobByJobIdList(List<Integer> id);

    /**select Job information by Company_ID*/
    List<Job> selectJobByComId(Integer company_id);


    List<Job> selectByIndustryId(List<Integer> indIds);

    List<Job> selectByIndustryString(String ind);
}

