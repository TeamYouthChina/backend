package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.Job_qingyang;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


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

    /**select Job information by Company_ID*/
    List<Job_qingyang> selectJobByComId(Integer company_id);


    List<Job_qingyang> selectByIndustryId(List<Integer> indIds);

    List<Job_qingyang> selectByIndustryString(String ind);
}

