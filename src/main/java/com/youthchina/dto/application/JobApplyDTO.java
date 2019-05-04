package com.youthchina.dto.application;

import com.youthchina.domain.Qinghong.JobApply;
import com.youthchina.dto.job.JobResponseDTO;

/**
 * @program: youthchina
 * @description: 职位申请DTO
 * @author: Qinghong Wang
 * @create: 2019-02-17 15:37
 **/
public class JobApplyDTO {
    private Integer id;
    private JobResponseDTO position;
    private String status;

    private String pdf_doc_id; //简历

    public JobApplyDTO(JobApply jobApply) {
        this.id = jobApply.getApply_id();
        this.position = new JobResponseDTO(jobApply.getJob());
        this.status = jobApply.getJob_apply_status();
        if(jobApply.getDocu_local_id() != null){
            this.pdf_doc_id = jobApply.getDocu_local_id();
        }
    }

    public String getPdf_doc_id() {
        return pdf_doc_id;
    }

    public void setPdf_doc_id(String pdf_doc_id) {
        this.pdf_doc_id = pdf_doc_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobResponseDTO getPosition() {
        return position;
    }

    public void setPosition(JobResponseDTO position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
