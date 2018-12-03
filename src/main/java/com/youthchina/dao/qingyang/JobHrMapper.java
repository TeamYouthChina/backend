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
    Integer deleteJob(String job_id);

    /**select Job information by Job_ID*/
    Job_qingyang selectJobByJobId(String job_id);

    /**select Job information by Company_ID*/
    List<Job_qingyang> selectJobByComId(String company_id);
}

