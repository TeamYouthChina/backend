package com.youthchina.dto.job;

import com.youthchina.domain.qingyang.Job;

/**
 * @author: Qingyang Zhao
 * @create: 2019-05-04
 **/
public class JobWithMail extends JobResponseDTO {

    private String mail;
    public JobWithMail(Job job) {
        this.convertToDTO(job);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public void convertToDTO(Job job){
        super.convertToDTO(job);
        this.mail = job.getCvReceiMail();
    }
}
