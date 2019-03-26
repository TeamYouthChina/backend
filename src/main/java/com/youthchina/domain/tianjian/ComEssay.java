package com.youthchina.domain.tianjian;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;

import java.sql.Timestamp;
import java.util.List;

public class ComEssay implements Commentable, RichTextable, Evaluatable, Attentionable {
    private Integer essayId;
    private String essayTitle;
    private String essayAbbre;
    private Integer userId;
    private Timestamp essayPubTime;
    private Timestamp essayEditTime;
    private Integer isDelete;
    private Timestamp isDeleteTime;
    private Integer userAnony;
    private Integer relaId;
    private Integer relaType;
    private List<Comment> comments;
    private User user;
    private Integer commentTargetType = 2;
    private Integer richTextRelaType = 2;
    private Integer evaluateTargetType = 2;
    private Integer attentionTargetType = 2;
    private ComRichText richText;


    public ComEssay(EssayResponseDTO essayResponseDTO) {
        this.essayId = essayResponseDTO.getId();
        this.essayTitle = essayResponseDTO.getTitle();
        this.essayPubTime = essayResponseDTO.getCreate_at();
        this.essayEditTime = essayResponseDTO.getModified_at();
        this.userAnony = (essayResponseDTO.isIs_anonymous()) ? 1 : 0;
        this.essayAbbre = essayResponseDTO.getBody().getPreviewText();
        this.richText = new ComRichText(essayResponseDTO.getBody());
    }


    public ComEssay(EssayRequestDTO essayRequestDTO) {
        this.essayId = essayRequestDTO.getId();
        this.essayTitle = essayRequestDTO.getTitle();
        this.essayAbbre = essayRequestDTO.getBody().getPreviewText();
        this.richText = new ComRichText(essayRequestDTO.getBody());
        this.userAnony = (essayRequestDTO.isIs_anonymous()) ? 1 : 0;
    }

    public ComEssay() {

    }

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public String getEssayTitle() {
        return essayTitle;
    }

    public void setEssayTitle(String essayTitle) {
        this.essayTitle = essayTitle;
    }

    public Integer getRelaId() {
        return relaId;
    }

    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }

    public Integer getRelaType() {
        return relaType;
    }

    public void setRelaType(Integer relaType) {
        this.relaType = relaType;
    }

    public String getEssayAbbre() {
        return essayAbbre;
    }

    public void setEssayAbbre(String essayAbbre) {
        this.essayAbbre = essayAbbre;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getEssayPubTime() {
        return essayPubTime;
    }

    public void setEssayPubTime(Timestamp essayPubTime) {
        this.essayPubTime = essayPubTime;
    }

    public Timestamp getEssayEditTime() {
        return essayEditTime;
    }

    public void setEssayEditTime(Timestamp essayEditTime) {
        this.essayEditTime = essayEditTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getIsDeleteTime() {
        return isDeleteTime;
    }

    public void setIsDeleteTime(Timestamp isDeleteTime) {
        this.isDeleteTime = isDeleteTime;
    }

    public Integer getUserAnony() {
        return userAnony;
    }

    public void setUserAnony(Integer userAnony) {
        this.userAnony = userAnony;
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
    public Integer getId() {
        return essayId;
    }

    public void setCommentTargetType(Integer commentTargetType) {
        this.commentTargetType = commentTargetType;
    }

    @Override
    public Integer getRichTextRelaType() {
        return richTextRelaType;
    }

    public void setRichTextRelaType(Integer richTextRelaType) {
        this.richTextRelaType = richTextRelaType;
    }

    @Override
    public Integer getEvaluateTargetType() {
        return evaluateTargetType;
    }

    public void setEvaluateTargetType(Integer evaluateTargetType) {
        this.evaluateTargetType = evaluateTargetType;
    }

    @Override
    public Integer getAttentionTargetType() {
        return attentionTargetType;
    }

    public void setAttentionTargetType(Integer attentionTargetType) {
        this.attentionTargetType = attentionTargetType;
    }

    @Override
    public ComRichText getRichText() {
        return richText;
    }

    @Override
    public void setRichText(ComRichText richText) {
        this.richText = richText;
    }

}
