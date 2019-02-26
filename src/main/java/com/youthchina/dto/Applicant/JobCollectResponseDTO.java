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
    private JobResponseDTO jobResponseDTO;

    public JobCollectResponseDTO(JobCollect jobCollect) {
        this.collect_id = jobCollect.getCollect_id();
        this.jobResponseDTO=new JobResponseDTO(jobCollect.getJob());
    }

    public JobCollectResponseDTO() {
    }

    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public JobResponseDTO getJobResponseDTO() {
        return jobResponseDTO;
    }

    public void setJobResponseDTO(JobResponseDTO jobResponseDTO) {
        this.jobResponseDTO = jobResponseDTO;
    }
}
