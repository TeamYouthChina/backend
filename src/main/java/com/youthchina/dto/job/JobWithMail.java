package com.youthchina.dto.job;

import com.youthchina.domain.qingyang.Job;

/**
 * @author: Qingyang Zhao
 * @create: 2019-05-04
 **/
public class JobWithMail extends JobResponseDTO {

    private String mail;
    public JobWithMail(Job job) {
        super(job);
        this.mail = job.getCvReceiMail();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
