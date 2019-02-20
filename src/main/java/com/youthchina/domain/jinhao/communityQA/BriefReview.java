package com.youthchina.domain.jinhao.communityQA;

import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;
import java.util.List;

public class BriefReview {
    private Integer review_id;
    private String review_content;
    private Timestamp review_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private int rela_type;
    private int rela_id;
    private List<Comment> comments;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public Timestamp getReview_time() {
        return review_time;
    }

    public void setReview_time(Timestamp review_time) {
        this.review_time = review_time;
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

    public int getRela_type() {
        return rela_type;
    }

    public void setRela_type(int rela_type) {
        this.rela_type = rela_type;
    }

    public int getRela_id() {
        return rela_id;
    }

    public void setRela_id(int rela_id) {
        this.rela_id = rela_id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
