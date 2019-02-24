package com.youthchina.domain.Qinghong;

import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.JobApplyDTO;

import java.sql.Timestamp;

/**
 * @program: youthchina
 * @description: 申请职位记录实体
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:04
 **/
public class JobApply {
    private Integer apply_id;
    private Integer job_id;
    private Integer job_cv_send;
    private Timestamp job_apply_time;
    private String job_apply_status;
    private Integer stu_id;
    private Job job;

//    public JobApply(JobApplyDTO jobApplyDTO) {
//        this.apply_id=jobApplyDTO.getId();
//        this.job=new Job(jobApplyDTO.getPosition());
//
//    }

    public JobApply() {
    }

    public Integer getApply_id() {
        return apply_id;
    }

    public void setApply_id(Integer apply_id) {
        this.apply_id = apply_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public Integer getJob_cv_send() {
        return job_cv_send;
    }

    public void setJob_cv_send(Integer job_cv_send) {
        this.job_cv_send = job_cv_send;
    }

    public Timestamp getJob_apply_time() {
        return job_apply_time;
    }

    public void setJob_apply_time(Timestamp job_apply_time) {
        this.job_apply_time = job_apply_time;
    }

    public String getJob_apply_status() {
        return job_apply_status;
    }

    public void setJob_apply_status(String job_apply_status) {
        this.job_apply_status = job_apply_status;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}