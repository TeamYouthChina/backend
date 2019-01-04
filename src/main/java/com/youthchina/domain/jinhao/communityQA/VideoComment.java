package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class VideoComment {
    private Integer comment_id;
    private String comment_content;
    private String user_id;
    private Integer user_anony;
    private Timestamp comment_pub_time;
    private Timestamp comment_edit_time;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_anony() {
        return user_anony;
    }

    public void setUser_anony(Integer user_anony) {
        this.user_anony = user_anony;
    }

    public Timestamp getComment_pub_time() {
        return comment_pub_time;
    }

    public void setComment_pub_time(Timestamp comment_pub_time) {
        this.comment_pub_time = comment_pub_time;
    }

    public Timestamp getComment_edit_time() {
        return comment_edit_time;
    }

    public void setComment_edit_time(Timestamp comment_edit_time) {
        this.comment_edit_time = comment_edit_time;
    }

    public Integer getis_delete() {
        return is_delete;
    }

    public void setis_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getis_delete_time() {
        return is_delete_time;
    }

    public void setis_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
