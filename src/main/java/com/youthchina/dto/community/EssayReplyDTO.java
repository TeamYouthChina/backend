package com.youthchina.dto.community;

import com.youthchina.domain.tianjian.ComEssayReply;

import java.sql.Timestamp;

public class EssayReplyDTO {
    private String body;
    private boolean isAnonymous;
    private Integer user_id;
    private Timestamp create_at;
    private Timestamp modified_at;

    public EssayReplyDTO(ComEssayReply comEssayReply){
        this.body = comEssayReply.getReply_content();
        this.create_at = comEssayReply.getReply_pub_time();
        this.isAnonymous = (comEssayReply.getUser_anony()==0)? false:true;
        this.modified_at = comEssayReply.getReply_edit_time();
        this.user_id = comEssayReply.getUser_id();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }
}
