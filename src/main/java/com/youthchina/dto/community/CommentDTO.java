package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.VideoComment;
import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;


public class CommentDTO {
    private Integer id;
    private User user;
    private String body;
    private Timestamp creat_at;
    private boolean is_anonymous;

    public CommentDTO(Comment comment){
        this.id = comment.getComment_id();
        this.user = comment.getUser();
        this.body = comment.getComment_content();
        this.creat_at = comment.getComment_pub_time();
        this.is_anonymous = (comment.getUser_anony()==1)? true:false;
    }

    public CommentDTO(VideoComment comment){
        this.id = comment.getComment_id();
        this.user = comment.getUser();
        this.body = comment.getComment_content();
        this.creat_at = comment.getComment_pub_time();
        this.is_anonymous = (comment.getUser_anony()==1)? true:false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreat_at() {
        return creat_at;
    }

    public void setCreat_at(Timestamp creat_at) {
        this.creat_at = creat_at;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
