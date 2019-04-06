package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.briefreview.BriefReviewRequestDTO;

import java.sql.Timestamp;
import java.util.List;

public class BriefReview implements Commentable, RichTextable, Evaluatable, Attentionable {
    private Integer id;
    private Timestamp time;
    private Integer relaType;
    private Integer relaId;
    private ComRichText body;
    private List<Comment> comments;
    private User user;
    private static final Integer richTextRelaType = 3;
    private static final Integer commentTargetType = 2;
    private static final Integer evaluateTargetType = 3;
    private static final Integer attentionTargetType = 3;

    public BriefReview(){}
    public BriefReview(BriefReviewRequestDTO briefReviewRequestDTO) {
        this.body = new ComRichText(briefReviewRequestDTO.getBody());
        if(briefReviewRequestDTO.getCompany_id()!=null){
            this.relaId = briefReviewRequestDTO.getCompany_id();
            this.relaType = 1;
        }else
            this.relaType = 0;
    }

    @Override
    public ComRichText getBody() {
        return body;
    }

    @Override
    public void setBody(ComRichText body) {
        this.body = body;
    }

    @Override
    public Integer getEvaluateTargetType() {
        return evaluateTargetType;
    }

    @Override
    public Integer getAttentionTargetType() {
        return attentionTargetType;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getRelaType() {
        return relaType;
    }

    public void setRelaType(Integer relaType) {
        this.relaType = relaType;
    }

    public Integer getRelaId() {
        return relaId;
    }

    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Integer getCommentTargetType() {
        return commentTargetType;
    }

    @Override
    public Integer getRichTextRelaType() {
        return richTextRelaType;
    }
}
