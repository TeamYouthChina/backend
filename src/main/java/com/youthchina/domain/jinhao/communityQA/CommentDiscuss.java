package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class CommentDiscuss {
    private Integer discuss_id;
    private Integer discuss_target_id;
    private String discuss_content;
    private String user_id;
    private Integer user_anony;
    private Timestamp discuss_pub_time;
    private Integer discuss_delete;
    private Timestamp discuss_delete_time;

    public Integer getDiscuss_id() {
        return discuss_id;
    }

    public void setDiscuss_id(Integer discuss_id) {
        this.discuss_id = discuss_id;
    }

    public String getDiscuss_content() {
        return discuss_content;
    }

    public void setDiscuss_content(String discuss_content) {
        this.discuss_content = discuss_content;
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

    public Timestamp getDiscuss_pub_time() {
        return discuss_pub_time;
    }

    public void setDiscuss_pub_time(Timestamp discuss_pub_time) {
        this.discuss_pub_time = discuss_pub_time;
    }

    public Integer getDiscuss_target_id() {
        return discuss_target_id;
    }

    public void setDiscuss_target_id(Integer discuss_target_id) {
        this.discuss_target_id = discuss_target_id;
    }

    public Integer getDiscuss_delete() {
        return discuss_delete;
    }

    public void setDiscuss_delete(Integer discuss_delete) {
        this.discuss_delete = discuss_delete;
    }

    public Timestamp getDiscuss_delete_time() {
        return discuss_delete_time;
    }

    public void setDiscuss_delete_time(Timestamp discuss_delete_time) {
        this.discuss_delete_time = discuss_delete_time;
    }
}
