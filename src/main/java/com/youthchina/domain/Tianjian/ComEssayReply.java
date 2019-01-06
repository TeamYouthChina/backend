package com.youthchina.domain.tianjian;

import java.sql.Timestamp;

public class ComEssayReply {
    private Integer reply_id;
    private String reply_content;
    private Integer user_id;
    private Integer user_anony;
    private Timestamp reply_pub_time;
    private Timestamp reply_edit_time;
    private Integer reply_delete;
    private Timestamp reply_delete_time;

    public Integer getReply_id() {
        return reply_id;
    }

    public void setReply_id(Integer reply_id) {
        this.reply_id = reply_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_anony() {
        return user_anony;
    }

    public void setUser_anony(Integer user_anony) {
        this.user_anony = user_anony;
    }

    public Timestamp getReply_pub_time() {
        return reply_pub_time;
    }

    public void setReply_pub_time(Timestamp reply_pub_time) {
        this.reply_pub_time = reply_pub_time;
    }

    public Timestamp getReply_edit_time() {
        return reply_edit_time;
    }

    public void setReply_edit_time(Timestamp reply_edit_time) {
        this.reply_edit_time = reply_edit_time;
    }

    public Integer getReply_delete() {
        return reply_delete;
    }

    public void setReply_delete(Integer reply_delete) {
        this.reply_delete = reply_delete;
    }

    public Timestamp getReply_delete_time() {
        return reply_delete_time;
    }

    public void setReply_delete_time(Timestamp reply_delete_time) {
        this.reply_delete_time = reply_delete_time;
    }
}
