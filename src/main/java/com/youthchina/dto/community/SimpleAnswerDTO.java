package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class SimpleAnswerDTO {
    private Integer id;
    private User creator;
    private String body;
    private Boolean isAnonymous;
    private Timestamp creatAt;

    public SimpleAnswerDTO(){}

    public SimpleAnswerDTO(QuestionAnswer questionAnser){
        this.id = questionAnser.getAnswer_id();
        this.creator = questionAnser.getAnswer_user();
        this.body = questionAnser.getAnswer_content();
        this.isAnonymous = (questionAnser.getUser_anony() == 1)?true:false;
        this.creatAt = questionAnser.getAnswer_pub_time();
    }

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
