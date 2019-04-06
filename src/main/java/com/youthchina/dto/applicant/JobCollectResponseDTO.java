package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;

/**
 * @program: youthchina
 * @description: 职位收藏返回DTO
 * @author: Qinghong Wang
 * @create: 2019-02-26 11:28
 **/
public class JobCollectResponseDTO implements ResponseDTO<JobCollect> {
    private Integer id;
    private JobResponseDTO job;

    public JobCollectResponseDTO(JobCollect jobCollect) {
        this.id = jobCollect.getCollect_id();
        this.job = new JobResponseDTO(jobCollect.getJob());
    }

    public JobCollectResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobResponseDTO getJob() {
        return job;
    }

    public void setJob(JobResponseDTO job) {
        this.job = job;
    }


    @Override
    public void convertToDTO(JobCollect jobCollect) {
        this.id = jobCollect.getCollect_id();
        this.job = new JobResponseDTO(jobCollect.getJob());
    }
}
