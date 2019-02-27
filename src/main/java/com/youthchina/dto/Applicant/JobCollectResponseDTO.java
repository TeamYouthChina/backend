package com.youthchina.dto.Applicant;

import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.dto.JobResponseDTO;

/**
 * @program: youthchina
 * @description: 职位收藏返回DTO
 * @author: Qinghong Wang
 * @create: 2019-02-26 11:28
 **/
public class JobCollectResponseDTO {
    private Integer collect_id;
    private JobResponseDTO job;

    public JobCollectResponseDTO(JobCollect jobCollect) {
        this.collect_id = jobCollect.getCollect_id();
        this.job=new JobResponseDTO(jobCollect.getJob());
    }

    public JobCollectResponseDTO() {
    }

    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public JobResponseDTO getJob() {
        return job;
    }

    public void setJob(JobResponseDTO job) {
        this.job = job;
    }
}
