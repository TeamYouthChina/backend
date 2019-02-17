package com.youthchina.domain.jinhao.communityQA;

import com.youthchina.dto.community.BriefReviewDTO;

import java.sql.Timestamp;

public class BriefReview {
    private Integer review_id;
    private String review_content;
    private Timestamp review_time;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public BriefReview (BriefReviewDTO briefReviewDTO){
        this.review_id = briefReviewDTO.getId();
        this.review_content = briefReviewDTO.getBody();
    }

    public BriefReview(){}

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
}
