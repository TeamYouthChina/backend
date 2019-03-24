package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;
import java.util.List;

public class BriefReview implements Commentable, RichTextable {
    private Integer id;
    private Timestamp time;
    private Integer relaType;
    private Integer relaId;
    private Integer userId;
    private List<Comment> comments;
    private User user;
    private Integer commentTargetType = 2;
    private Integer richTextRelaType = 3;
    private ComRichText richText;

    @Override
    public Integer getRichTextRelaType() {
        return richTextRelaType;
    }

    public void setRichTextRelaType(Integer richTextRelaType) {
        this.richTextRelaType = richTextRelaType;
    }

    @Override
    public ComRichText getRichText() {
        return richText;
    }

    @Override
    public void setRichText(ComRichText richText) {
        this.richText = richText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRelaType(Integer relaType) {
        this.relaType = relaType;
    }

    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getRelaType() {
        return relaType;
    }

    public void setRelaType(int relaType) {
        this.relaType = relaType;
    }

    public int getRelaId() {
        return relaId;
    }

    public void setRelaId(int relaId) {
        this.relaId = relaId;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCommentTargetType() {
        return commentTargetType;
    }

    public void setCommentTargetType(Integer commentTargetType) {
        this.commentTargetType = commentTargetType;
    }
}
