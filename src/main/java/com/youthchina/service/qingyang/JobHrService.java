package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Job;

import java.util.List;
import java.util.Map;

public interface JobHrService {
    /**添加Job*/
    Map<String,Object> insertJob(Job job);

    /**修改Job信息*/
    Map<String,Object> updateJob(Job job);

    /**删除Job信息*/
    Map<String,Object> deleteJob(String job_id);

    /**通过JobID查询Job信息*/
    Job getJobByJobId(String job_id);

    /**通过CompanyID查询Job信息*/
    List<Job> getJobByComId(String company_id);

}
