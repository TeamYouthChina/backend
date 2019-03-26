package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;

public class Discuss implements Evaluatable, Attentionable {
    private Integer id;
    private Integer commentId;
    private String content;
    private Integer isAnony;
    private Timestamp pubTime;
    private Integer userId;
    private User user;
    private Integer evaluateTargetType = 2;
    private Integer attentionTargetType = 2;

    @Override
    public Integer getEvaluateTargetType() {
        return evaluateTargetType;
    }

    public void setEvaluateTargetType(Integer evaluateTargetType) {
        this.evaluateTargetType = evaluateTargetType;
    }

    @Override
    public Integer getAttentionTargetType() {
        return attentionTargetType;
    }

    public void setAttentionTargetType(Integer attentionTargetType) {
        this.attentionTargetType = attentionTargetType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsAnony() {
        return isAnony;
    }

    public void setIsAnony(Integer isAnony) {
        this.isAnony = isAnony;
    }

    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
