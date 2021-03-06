package com.youthchina.dto.community.comment;

import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.security.UserDTO;

import java.sql.Timestamp;


public class CommentResponseDTO implements ResponseDTO<Comment> {
    private Integer id;
    private UserDTO creator;
    private String body;
    private Timestamp create_at;
    private boolean is_anonymous;
    private Timestamp modified_at;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer evaluateStatus;

    public CommentResponseDTO() {

    }

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        if (comment.getIsAnony() == 0)
            this.creator = new UserDTO(comment.getUser());
        else
            this.creator = null;
        this.body = comment.getContent();
        this.create_at = comment.getPubTime();
        this.is_anonymous = (comment.getIsAnony() == 1) ? true : false;
        this.modified_at = comment.getEditTime();
        this.upvoteCount = comment.getUpvoteCount();
        this.downvoteCount = comment.getDownvoteCount();
        this.evaluateStatus = comment.getEvaluateStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonTimeStamp
    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
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

    @JsonTimeStamp
    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public void convertToDTO(Comment comment) {
        this.id = comment.getId();
        this.creator = new UserDTO(comment.getUser());
        this.body = comment.getContent();
        this.create_at = comment.getPubTime();
        this.is_anonymous = (comment.getIsAnony() == 1) ? true : false;
        this.modified_at = comment.getEditTime();
        this.upvoteCount = comment.getUpvoteCount();
        this.downvoteCount = comment.getDownvoteCount();
        this.evaluateStatus = comment.getEvaluateStatus();
    }
}
