package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;

import java.sql.Timestamp;
import java.util.List;

public class Answer implements Commentable, RichTextable, Evaluatable, Attentionable {
    private Integer id;
    private ComRichText body;
    private Integer targetType;
    private Integer targetId;
    private Integer isAnony;
    private Timestamp pubTime;
    private Timestamp editTime;
    private User user;
    private Question question;
    private List<Comment> comments;
    private static final Integer richTextRelaType = 4;
    private static final Integer commentTargetType = 4;
    private static final Integer evaluateTargetType = 7;
    private static final Integer attentionTargetType = 7;

    public Answer(SimpleAnswerRequestDTO simpleAnswerDTO){
        this.body = new ComRichText(simpleAnswerDTO.getBody());
        this.isAnony = (simpleAnswerDTO.getIs_anonymous())? 1:0;
    }
    public Answer(){}

    @Override
    public ComRichText getBody() {
        return body;
    }

    @Override
    public void setBody(ComRichText body) {
        this.body = body;
    }

    @Override
    public Integer getAttentionTargetType() {
        return attentionTargetType;
    }

    @Override
    public Integer getEvaluateTargetType() {
        return evaluateTargetType;
    }

    @Override
    public Integer getRichTextRelaType() {
        return richTextRelaType;
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
}
