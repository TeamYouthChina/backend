package com.youthchina.dto.Applicant;

import com.youthchina.domain.qingyang.Company;

/**
 * @program: youthchina
 * @description: 传输邮件DTO
 * @author: Qinghong Wang
 * @create: 2019-03-05 10:24
 **/
public class SendingEmailDTO {
    private Integer user_id;
    private String company_email;
    private byte[] bytes;

    public SendingEmailDTO() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
