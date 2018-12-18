package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.domain.qingyang.Job_qingyang;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface JobCollectService extends DomainCRUDService<Job_qingyang,Integer> {
    public List<Job_qingyang> getAllJobCollect(Integer stu_id);
    public Job_qingyang getByJobId(Integer job_id);
    public JobCollect addOneJobCollect(Integer stu_id, Integer job_id);
}
