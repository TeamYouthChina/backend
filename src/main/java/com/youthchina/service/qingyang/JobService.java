package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Job_qingyang;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;
import java.util.Map;

public interface JobService extends DomainCRUDService<Job_qingyang, Integer>{


    /**HR删除Job信息*/
    void delete(User user, Integer jobId) throws NotFoundException;

    /**HR通过JobID查询Job信息, 能不能edit*/
    Job_qingyang getByHr(User user, Integer jobId) throws NotBelongException;

    Map<String, List<Job_qingyang>> getJobByIndustries(List<String> industries);

    Map<String, List<Job_qingyang>> getJobByTag(List<String> tags);

}
