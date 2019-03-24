package com.youthchina.domain.jinhao;

import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.question.QuestionRequestDTO;

import java.sql.Timestamp;
import java.util.List;

public class Question implements RichTextable {
    private Integer id;
    private String title;
    private String abbre;
    private String body;
    private Timestamp pubTime;
    private Timestamp editTime;
    private Integer isAnony;
    private Integer userId;
    private User user;
    private List<Answer> answers;
    private Integer rela_type;
    private Integer rela_id;
    private Integer richTextRelaType = 2;
    private ComRichText richText;

    public Question(QuestionRequestDTO questionRequestDTO){
        this.title = questionRequestDTO.getTitle();
        this.isAnony = (questionRequestDTO.getIs_anonymous()==true ? 1 : 0);
        this.richText.setJson_content(questionRequestDTO.getBody().getBraftEditorRaw());
        this.richText.setText_content(questionRequestDTO.getBody().getPreviewText());
        this.rela_type = questionRequestDTO.getRela_type();
        this.rela_id = questionRequestDTO.getRela_id();
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
