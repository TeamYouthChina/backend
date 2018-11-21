package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class QuestionAnswer {
    private Integer answer_id;
    private String answer_content;
    private String user_id;
    private Integer user_anony;
    private Timestamp answer_pub_time;
    private Timestamp answer_edit_time;
    private Integer answer_delete;

    public Integer getAnswer_delete() {
        return answer_delete;
    }

    public void setAnswer_delete(Integer answer_delete) {
        this.answer_delete = answer_delete;
    }

    public Timestamp getAnswer_delete_time() {
        return answer_delete_time;
    }

    public void setAnswer_delete_time(Timestamp answer_delete_time) {
        this.answer_delete_time = answer_delete_time;
    }

    private Timestamp answer_delete_time;


    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public Integer getUser_anony() {
        return user_anony;
    }

    public void setUser_anony(Integer user_anony) {
        this.user_anony = user_anony;
    }

    public Timestamp getAnswer_pub_time() {
        return answer_pub_time;
    }

    public void setAnswer_pub_time(Timestamp answer_pub_time) {
        this.answer_pub_time = answer_pub_time;
    }

    public Timestamp getAnswer_edit_time() {
        return answer_edit_time;
    }

    public void setAnswer_edit_time(Timestamp answer_edit_time) {
        this.answer_edit_time = answer_edit_time;
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
