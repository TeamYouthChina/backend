package com.youthchina.service.application;

import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotBelongException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.sql.Date;
import java.util.List;

public interface JobService extends DomainCRUDService<Job, Integer> {

    public List<Job> getJobByMore(Integer jobId, String jobName, Integer comId, String comName,
                                  Date startTime, Date endTime, Integer type, Integer salaryFloor, Integer salaryCap,
                                  Integer active, String location, List<Degree> jobReqList,
                                  List<Industry> industryList) throws NotFoundException;


    List<Job> getJobByUserId(Integer userId) throws NotFoundException;

    Job getJobWithCollected(Integer jobId, Integer userId) throws NotFoundException;
}
