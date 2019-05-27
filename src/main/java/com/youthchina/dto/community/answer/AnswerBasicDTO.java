package com.youthchina.dto.community.answer;

import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

import java.sql.Timestamp;

/**
 * Created by xiaoyiwang on 2/24/19.
 */

public class AnswerBasicDTO {
    private Integer id;
    private RichTextResponseDTO body;
    private boolean is_anonymous;
    private UserDTO creator;
    private Timestamp modified_at;
    private Timestamp create_at;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer attentionCount;
    private boolean isAttention;
    private Integer evaluateStatus;

    public AnswerBasicDTO() {
    }

    public AnswerBasicDTO(Answer answer) {
        RichTextResponseDTO richt = new RichTextResponseDTO(answer.getBody());
        this.body = richt;
        this.is_anonymous = answer.getIsAnony() != 0;
        this.creator = this.is_anonymous ? null : new UserDTO(answer.getUser());
        this.modified_at = answer.getEditTime();
        this.create_at = answer.getPubTime();
        this.id = answer.getId();
        this.upvoteCount = answer.getUpvoteCount();
        this.downvoteCount = answer.getDownvoteCount();
        this.attentionCount = answer.getAttentionCount();
        this.isAttention = answer.isAttention();
        this.evaluateStatus = answer.getEvaluateStatus();
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

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public RichTextResponseDTO getBody() {
        return body;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setBody(RichTextResponseDTO body) {
        this.body = body;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    @JsonTimeStamp
    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    @JsonTimeStamp
    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

}