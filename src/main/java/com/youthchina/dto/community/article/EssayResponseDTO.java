package com.youthchina.dto.community.article;

import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

import java.sql.Timestamp;

public class EssayResponseDTO implements ResponseDTO<ComEssay> {
    private Integer id;
    private String title;
    private CompanyResponseDTO company;
    private Timestamp create_at;
    private Timestamp modified_at;
    private UserDTO author;
    private RichTextResponseDTO body;
    private boolean is_anonymous;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer attentionCount;
    private boolean isAttention;
    private Integer evaluateStatus;

    public EssayResponseDTO(ComEssay comEssay) {
        this.id = comEssay.getId();
        this.title = comEssay.getTitle();
        this.create_at = comEssay.getPubTime();
        this.modified_at = comEssay.getEditTime();
        this.is_anonymous = (comEssay.getIsAnony() == 0) ? false : true;
        this.author = new UserDTO(comEssay.getUser());
        this.body = new RichTextResponseDTO(comEssay.getBody());
    }

    public EssayResponseDTO() {
    }

    public RichTextResponseDTO getBody() {
        return body;
    }

    public void setBody(RichTextResponseDTO body) {
        this.body = body;
    }

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

    public CompanyResponseDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDTO company) {
        this.company = company;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
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

    @Override
    public void convertToDTO(ComEssay comEssay) {
        this.id = comEssay.getId();
        this.title = comEssay.getTitle();
        this.create_at = comEssay.getPubTime();
        this.modified_at = comEssay.getEditTime();
        this.is_anonymous = (comEssay.getIsAnony() == 0) ? false : true;
        this.author = new UserDTO(comEssay.getUser());
        this.body = new RichTextResponseDTO(comEssay.getBody());
    }
}
