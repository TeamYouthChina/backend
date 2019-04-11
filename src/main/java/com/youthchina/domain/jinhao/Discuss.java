package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.discuss.DiscussRequestDTO;
import com.youthchina.dto.community.discuss.DiscussResponseDTO;

import java.sql.Timestamp;

public class Discuss implements Evaluatable, Attentionable {
    private Integer id;
    private Integer commentId;
    private String content;
    private Integer isAnony;
    private Timestamp pubTime;
    private Timestamp editTime;
    private User user;
    private static final Integer evaluateTargetType = 6;
    private static final Integer attentionTargetType = 6;

    public Discuss() {

    }

    public Discuss(DiscussRequestDTO discussRequestDTO) {
        this.content = discussRequestDTO.getBody();
        this.isAnony = (discussRequestDTO.isIs_anonymous())? 1:0;
    }

    @Override
    public Integer getEvaluateTargetType() {
        return evaluateTargetType;
    }

    @Override
    public Integer getAttentionTargetType() {
        return attentionTargetType;
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

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
