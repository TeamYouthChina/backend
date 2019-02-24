package com.youthchina.domain.jinhao.communityQA;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.CommentDTO;

import java.sql.Timestamp;
import java.util.List;

public class Comment {
    private Integer comment_id;
    private String comment_content;
    private Integer user_id;
    private Integer user_anony;
    private Timestamp comment_pub_time;
    private Timestamp comment_edit_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private User user;
    private List<Discuss> discusses;
    private List<CommentEvaluate> commentEvaluates;

    public Comment(){}

    public Comment(CommentDTO commentDTO){
        this.user_anony = (commentDTO.isIs_anonymous())? 1:0;
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(commentDTO.getBody());
            this.comment_content = requestJson;
        }catch (Exception e){
            System.out.println("Exception");
        }
    }

    public List<Discuss> getDiscusses() {
        return discusses;
    }

    public void setDiscusses(List<Discuss> discusses) {
        this.discusses = discusses;
    }

    public List<CommentEvaluate> getCommentEvaluates() {
        return commentEvaluates;
    }

    public void setCommentEvaluates(List<CommentEvaluate> commentEvaluates) {
        this.commentEvaluates = commentEvaluates;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
