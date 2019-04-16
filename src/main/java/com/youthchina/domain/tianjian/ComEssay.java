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
    private Integer id;
    private String title;
    private String abbre;
    private ComRichText body;
    private Timestamp pubTime;
    private Timestamp editTime;
    private Integer isAnony;
    private Integer relaId;
    private Integer relaType;
    private List<Comment> comments;
    private User user;
    private Integer upvotecount;
    private Integer downvotecount;
    private Integer isAttention;
    private Integer evaluatestatus;
    private static final Integer richTextRelaType = 1;
    private static final Integer commentTargetType = 1;
    private static final Integer evaluateTargetType = 2;
    private static final Integer attentionTargetType = 2;


    public ComEssay(EssayResponseDTO essayResponseDTO) {
        this.id = essayResponseDTO.getId();
        this.title = essayResponseDTO.getTitle();
        this.pubTime = essayResponseDTO.getCreate_at();
        this.editTime = essayResponseDTO.getModified_at();
        this.isAnony = (essayResponseDTO.isIs_anonymous()) ? 1 : 0;
        this.abbre = essayResponseDTO.getBody().getPreviewText();
        this.body = new ComRichText(essayResponseDTO.getBody());
    }


    public ComEssay(EssayRequestDTO essayRequestDTO) {
        this.id = essayRequestDTO.getId();
        this.title = essayRequestDTO.getTitle();
        this.abbre = essayRequestDTO.getBody().getPreviewText();
        this.body = new ComRichText(essayRequestDTO.getBody());
        this.isAnony = (essayRequestDTO.isIs_anonymous()) ? 1 : 0;
        this.relaId = essayRequestDTO.getRela_id();
        this.relaType = essayRequestDTO.getRela_type();

    }

    public ComEssay() {

    }


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public ComRichText getBody() {
        return body;
    }

    @Override
    public void setBody(ComRichText body) {
        this.body = body;
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

    public Integer getUpvotecount() {
        return upvotecount;
    }

    public void setUpvotecount(Integer upvotecount) {
        this.upvotecount = upvotecount;
    }

    public Integer getDownvotecount() {
        return downvotecount;
    }

    public void setDownvotecount(Integer downvotecount) {
        this.downvotecount = downvotecount;
    }

    public Integer getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }

    public Integer getEvaluatestatus() {
        return evaluatestatus;
    }

    public void setEvaluatestatus(Integer evaluatestatus) {
        this.evaluatestatus = evaluatestatus;
    }

    @Override
    public Integer getCommentTargetType() {
        return commentTargetType;
    }


    @Override
    public Integer getRichTextRelaType() {
        return richTextRelaType;
    }


    @Override
    public Integer getEvaluateTargetType() {
        return evaluateTargetType;
    }

    @Override
    public Integer getAttentionTargetType() {
        return attentionTargetType;
    }





}
