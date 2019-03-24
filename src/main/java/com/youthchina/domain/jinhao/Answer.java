package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.answer.RequestSimpleAnswerDTO;

import java.sql.Timestamp;
import java.util.List;

public class Answer implements Commentable, RichTextable {
    private Integer id;
    private Integer targetType;
    private Integer targetId;
    private Integer isAnony;
    private Timestamp pubTime;
    private Timestamp editTime;
    private Integer userId;
    private User user;
    private Question question;
    private Integer commentTargetType = 4;
    private List<Comment> comments;
    private Integer richTextRelaType = 4;
    private ComRichText richText;

    public Answer(RequestSimpleAnswerDTO simpleAnswerDTO){
        this.richText.setJson_content(simpleAnswerDTO.getBody().getBraftEditorRaw());
        this.richText.setText_content(simpleAnswerDTO.getBody().getPreviewText());
    }

    @Override
    public Integer getRichTextRelaType() {
        return richTextRelaType;
    }

    public void setRichTextRelaType(Integer richTextRelaType) {
        this.richTextRelaType = richTextRelaType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public ComRichText getRichText() {
        return richText;
    }

    @Override
    public void setRichText(ComRichText richText) {
        this.richText = richText;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getIsAnony() {
        return isAnony;
    }

    public void setIsAnony(Integer isAnony) {
        this.isAnony = isAnony;
    }

    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getCommentTargetType() {
        return commentTargetType;
    }

    public void setCommentTargetType(Integer commentTargetType) {
        this.commentTargetType = commentTargetType;
    }
}
