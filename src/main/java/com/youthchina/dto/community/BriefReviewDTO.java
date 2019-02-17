package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;

import java.sql.Timestamp;

public class BriefReviewDTO {
    private Integer review_id;
    private String body;
    private Timestamp create_at;
    private Integer user_id;
    private Integer rela_type;
    private Integer rela_id;
    private Comment comment;

    public BriefReviewDTO (BriefReview briefReview){
        this.review_id = briefReview.getReview_id();
        this.body = briefReview.getReview_content();
        this.create_at = briefReview.getReview_time();
    }

    public BriefReviewDTO(){}

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id) {
        this.rela_id = rela_id;
    }
}
