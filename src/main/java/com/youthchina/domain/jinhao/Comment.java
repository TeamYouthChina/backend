package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.util.dictionary.EvaluationTargetType;
import com.youthchina.util.dictionary.IsExistTargetType;

import java.sql.Timestamp;

public class Comment implements Evaluatable {
    private Integer id;
    private String content;
    private Integer isAnony;
    private Timestamp pubTime;
    private Timestamp editTime;
    private User user;
    private Integer targetType;
    private Integer targetId;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer evaluateStatus;
    private static final Integer evaluateTargetType = EvaluationTargetType.COMMENT;
    private static final Integer isExistTargetType = IsExistTargetType.COMMENT;

    public Comment(){}
    public Comment(CommentRequestDTO commentRequestDTO){
        this.content = commentRequestDTO.getBody();
        this.isAnony = (commentRequestDTO.getIs_anonymous())? 1:0;
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

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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
