package com.youthchina.service.application;

import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.sql.Date;
import java.util.List;

public interface JobService extends DomainCRUDService<Job, Integer> {

    /**
     * HR删除Job信息
     */
    void delete(User user, Integer jobId) throws NotFoundException;

    /**
     * HR通过JobID查询Job信息, 能不能edit
     */
    Job getByHr(User user, Integer jobId) throws NotBelongException;

    public List<Job> getJobByMore(Integer jobId, String jobName, Integer comId, String comName,
                                  Date startTime, Date endTime, Integer type, Integer salaryFloor, Integer salaryCap,
                                  Integer active, String location, List<Degree> jobReqList,
                                  List<Industry> industryList) throws NotFoundException;


    Job getJobWithCollected(Integer jobId, Integer userId) throws NotFoundException;
}
