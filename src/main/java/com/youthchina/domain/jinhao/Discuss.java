package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.discuss.DiscussRequestDTO;
import com.youthchina.util.dictionary.EvaluationTargetType;
import com.youthchina.util.dictionary.IsExistTargetType;

import java.sql.Timestamp;

public class Discuss implements Evaluatable {
    private Integer id;
    private Integer commentId;
    private String content;
    private Integer isAnony;
    private Timestamp pubTime;
    private Timestamp editTime;
    private User user;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer evaluateStatus;
    private static final Integer evaluateTargetType = EvaluationTargetType.DISCUSS;
    private static final Integer isExistTargetType = IsExistTargetType.DISCUSS;
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
    public Integer getExistType() {
        return isExistTargetType;
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

    public Integer getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Integer getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(Integer downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public static Integer getIsExistTargetType() {
        return isExistTargetType;
    }
}
