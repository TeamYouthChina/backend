package com.youthchina.domain.tianjian;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.util.dictionary.*;

import java.sql.Timestamp;
import java.util.List;

public class ComEssay implements Commentable, RichTextable, Evaluatable, Attentionable, HasAuthor {
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
    private User author;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer attentionCount;
    private boolean isAttention;
    private Integer evaluateStatus;
    private static final Integer richTextRelaType = RichTextRelaType.ESSAY;
    private static final Integer commentTargetType = CommentTargetType.ESSAY;
    private static final Integer evaluateTargetType = EvaluationTargetType.ESSAY;
    private static final Integer attentionTargetType = AttentionTargetType.ESSAY;
    private static final Integer isExistTargetType = IsExistTargetType.ESSAY;


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
        this.isAttention = false;
        this.evaluateStatus = 3;
    }


    @Override
    public Integer getExistType() {
        return isExistTargetType;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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


    public Integer getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Integer getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(Integer downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public static Integer getIsExistTargetType() {
        return isExistTargetType;
    }
}
