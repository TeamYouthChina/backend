package com.youthchina.dto.security;

import com.youthchina.domain.zhongyang.User;

/**
 * Created by zhongyangwu on 5/6/19.
 */
public class VerifyEmailDTO {
    private String mailTo;
    private String firstName;
    private String lastName;
    private String code;

    public VerifyEmailDTO() {
    }

    public VerifyEmailDTO(User user, String code) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mailTo = user.getEmail();
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }
}
