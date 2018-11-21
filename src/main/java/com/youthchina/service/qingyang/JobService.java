package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Job_qingyang;

import java.util.List;
import java.util.Map;

public interface JobService {
    /**添加Job*/
    Map<String,Object> insertJob(Job_qingyang job);

    /**修改Job信息*/
    Map<String,Object> updateJob(Job_qingyang job);

    /**删除Job信息*/
    Map<String,Object> deleteJob(String job_id);

    /**通过JobID查询Job信息*/
    Job_qingyang getJobByJobId(String job_id);

    /**通过CompanyID查询Job信息*/
    List<Job_qingyang> getJobByComId(String company_id);


}
