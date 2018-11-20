package com.youthchina.domain.Tianjian;

import java.sql.Timestamp;

public class ComEssay {
    private Integer essay_id;
    private String essay_title;

    public Integer getEssay_id() {
        return essay_id;
    }

    public void setEssay_id(Integer essay_id) {
        this.essay_id = essay_id;
    }

    public String getEssay_title() {
        return essay_title;
    }

    public void setEssay_title(String essay_title) {
        this.essay_title = essay_title;
    }

    public String getEssay_abbre() {
        return essay_abbre;
    }

    public void setEssay_abbre(String essay_abbre) {
        this.essay_abbre = essay_abbre;
    }

    public String getEssay_body() {
        return essay_body;
    }

    public void setEssay_body(String essay_body) {
        this.essay_body = essay_body;
    }

    public Timestamp getEssay_pub_time() {
        return essay_pub_time;
    }

    public void setEssay_pub_time(Timestamp essay_pub_time) {
        this.essay_pub_time = essay_pub_time;
    }

    public Timestamp getEssay_edit_time() {
        return essay_edit_time;
    }

    public void setEssay_edit_time(Timestamp essay_edit_time) {
        this.essay_edit_time = essay_edit_time;
    }

    public Integer getEssay_delete() {
        return essay_delete;
    }

    public void setEssay_delete(Integer essay_delete) {
        this.essay_delete = essay_delete;
    }

    private String essay_abbre;
    private String essay_body;
    private Timestamp essay_pub_time;
    private Timestamp  essay_edit_time;
    private Integer essay_delete;


}
