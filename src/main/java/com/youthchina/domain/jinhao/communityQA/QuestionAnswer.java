package com.youthchina.domain.jinhao.communityQA;

import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;
import java.util.List;

public class QuestionAnswer {
    private Integer answer_id;
    private String answer_content;
    private Integer user_id;
    private Integer user_anony;
    private Timestamp answer_pub_time;
    private Timestamp answer_edit_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private User answer_user;
    private List<AnswerEvaluate> answerEvaluates;
    private List<AnswerComment> answerComments;

    public User getAnswer_user() {
        return answer_user;
    }

    public void setAnswer_user(User answer_user) {
        this.answer_user = answer_user;
    }

    public List<AnswerEvaluate> getAnswerEvaluates() {
        return answerEvaluates;
    }

    public void setAnswerEvaluates(List<AnswerEvaluate> answerEvaluates) {
        this.answerEvaluates = answerEvaluates;
    }

    public List<AnswerComment> getAnswerComments() {
        return answerComments;
    }

    public void setAnswerComments(List<AnswerComment> answerComments) {
        this.answerComments = answerComments;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
