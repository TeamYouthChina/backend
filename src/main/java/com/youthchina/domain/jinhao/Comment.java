package com.youthchina.domain.jinhao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.article.EssayReplyRequestDTO;
import com.youthchina.dto.community.comment.CommentDTO;

import java.sql.Timestamp;

public class Comment {
    private Integer id;
    private String content;
    private Integer userId;
    private Integer isAnony;
    private Timestamp pubTime;
    private Timestamp editTime;
    private User user;
    private Integer targetType;
    private Integer targetId;

    public Comment(){}
    public Integer getId() {
        return id;
    }
    public Comment(CommentDTO commentDTO) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(commentDTO.getBody());
            this.content = requestJson;
        } catch (Exception e) {
            System.out.println("Exception");
        }
        this.pubTime = commentDTO.getCreate_at();
        this.user = new User(commentDTO.getCreator());
        this.id = commentDTO.getId();
        this.isAnony = (commentDTO.isIs_anonymous()) ? 1 : 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
