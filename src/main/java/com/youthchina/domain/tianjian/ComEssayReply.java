package com.youthchina.domain.tianjian;

import java.sql.Timestamp;

public class ComEssayReply {
    private Integer reply_id;
    private String reply_content;
    private Integer user_id;
    private Integer user_anony;
    private Timestamp reply_pub_time;
    private Timestamp reply_edit_time;
    private Integer is_delete;
    private Timestamp is_delete_time;

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

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
