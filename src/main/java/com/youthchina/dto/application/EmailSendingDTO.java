package com.youthchina.dto.application;

import java.net.URL;

/**
 * @program: youthchina
 * @description: 邮件发送DTO
 * @author: Qinghong Wang
 * @create: 2019-05-12 16:25
 **/
public class EmailSendingDTO {
    private String jobName;
    private String firstName;
    private String lastName;
    private URL url;
    private String ownEmail;
    private String hrEmail;
    private String fileName;

    public EmailSendingDTO() {
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getOwnEmail() {
        return ownEmail;
    }

    public void setOwnEmail(String ownEmail) {
        this.ownEmail = ownEmail;
    }

    public String getHrEmail() {
        return hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
