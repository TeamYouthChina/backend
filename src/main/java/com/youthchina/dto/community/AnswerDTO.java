package com.youthchina.dto.community;

import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class AnswerDTO {
    private Integer id;
    private User creator;
    private String body;
    private Boolean isAnonymous;
    private Timestamp creatAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Timestamp getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Timestamp creatAt) {
        this.creatAt = creatAt;
    }
}
