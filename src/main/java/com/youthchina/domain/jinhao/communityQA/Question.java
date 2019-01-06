package com.youthchina.domain.jinhao.communityQA;

import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;
import java.util.List;

public class Question {
    private Integer ques_id;
    private String ques_title;
    private String ques_abbre;
    private String ques_body;
    private Timestamp ques_pub_time;
    private Timestamp ques_edit_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private User user;
    private Integer followers;
    private QuestionAnswer answer;
    private List<Label> labels;

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public QuestionAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(QuestionAnswer answer) {
        this.answer = answer;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getQues_id() {
        return ques_id;
    }

    public void setQues_id(Integer ques_id) {
        this.ques_id = ques_id;
    }

    public String getQues_title() {
        return ques_title;
    }

    public void setQues_title(String ques_title) {
        this.ques_title = ques_title;
    }

    public String getQues_abbre() {
        return ques_abbre;
    }

    public void setQues_abbre(String ques_abbre) {
        this.ques_abbre = ques_abbre;
    }

    public String getQues_body() {
        return ques_body;
    }

    public void setQues_body(String ques_body) {
        this.ques_body = ques_body;
    }

    public Timestamp getQues_pub_time() {
        return ques_pub_time;
    }

    public void setQues_pub_time(Timestamp ques_pub_time) {
        this.ques_pub_time = ques_pub_time;
    }

    public Timestamp getQues_edit_time() {
        return ques_edit_time;
    }

    public void setQues_edit_time(Timestamp ques_edit_time) {
        this.ques_edit_time = ques_edit_time;
    }

}
