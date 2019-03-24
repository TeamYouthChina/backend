package com.youthchina.domain.jinhao.communityQA;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.util.zhongyang.HasId;

import java.sql.Timestamp;
import java.util.List;

public class QuestionAnswer implements HasId<Integer> {
    private Integer answer_id;
    private String answer_content;
    private Integer user_id;
    private Integer user_anony;
    private Timestamp answer_pub_time;
    private Timestamp answer_edit_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private User answer_user;
    private List<Evaluate> evaluates;
    private List<Comment> comments;
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionAnswer(SimpleAnswerRequestDTO simpleAnswerDTO) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(simpleAnswerDTO.getBody());
            this.answer_content = requestJson;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.user_anony = (simpleAnswerDTO.getIs_anonymous()) ? 1 : 0;
    }

    public QuestionAnswer(){}

    public Integer getId() {
        return answer_id;
    }

    public User getAnswer_user() {
        return answer_user;
    }

    public void setAnswer_user(User answer_user) {
        this.answer_user = answer_user;
    }

    public List<Evaluate> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(List<Evaluate> evaluates) {
        this.evaluates = evaluates;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
