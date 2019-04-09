package com.youthchina.dto.community.discuss;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.dto.security.UserDTO;

import java.sql.Timestamp;

public class DiscussDTO {
    private Integer id;
    private Integer commentId;
    private UserDTO creator;
    private String body;
    private Timestamp create_at;
    private boolean is_anonymous;
    private Timestamp modified_at;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer evaluateStatus;

    public DiscussDTO (){

    }

    public DiscussDTO(Discuss discuss) {
        this.id = discuss.getId();
        this.commentId = discuss.getCommentId();
        this.creator = new UserDTO(discuss.getUser());
        this.body = discuss.getContent();
        this.create_at = discuss.getPubTime();
        this.is_anonymous = (discuss.getIsAnony()==1)? true:false;
        this.modified_at = discuss.getEditTime();
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

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }
}
