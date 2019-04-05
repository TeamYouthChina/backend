package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.jinhao.property.Invitable;
import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.question.QuestionRequestDTO;

import java.sql.Timestamp;
import java.util.List;

public class Question implements RichTextable, Evaluatable, Attentionable, Invitable {
    private Integer id;
    private String title;
    private String abbre;
    private ComRichText body;
    private Timestamp pubTime;
    private Timestamp editTime;
    private Integer isAnony;
    private User user;
    private List<Answer> answers;
    private Integer relaType;
    private Integer relaId;
    private static final Integer richTextRelaType = 2;
    private static final Integer evaluateTargetType = 1;
    private static final Integer attentionTargetType = 1;
    private static final Integer inviteTargetType = 1;

    public Question(){}
    public Question(QuestionRequestDTO questionRequestDTO){
        this.title = questionRequestDTO.getTitle();
        this.isAnony = (questionRequestDTO.getIs_anonymous()==true ? 1 : 0);
        this.body.setJsonContent(questionRequestDTO.getBody().getBraftEditorRaw());
        this.body.setTextContent(questionRequestDTO.getBody().getPreviewText());
        this.relaType = questionRequestDTO.getRela_type();
        this.relaId = questionRequestDTO.getRela_id();
    }
    @Override
    public ComRichText getBody() {
        return body;
    }

    @Override
    public Integer getInviteTargetType() {
        return inviteTargetType;
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

    public void setBody(ComRichText body) {
        this.body = body;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbbre() {
        return abbre;
    }

    public void setAbbre(String abbre) {
        this.abbre = abbre;
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

    public Integer getIsAnony() {
        return isAnony;
    }

    public void setIsAnony(Integer isAnony) {
        this.isAnony = isAnony;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
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
}
