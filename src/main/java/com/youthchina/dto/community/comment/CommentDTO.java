package com.youthchina.dto.community.comment;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.security.UserDTO;

import java.sql.Timestamp;


public class CommentDTO implements ResponseDTO<Comment> {
    private Integer id;
    private UserDTO creator;
    private String body;
    private Timestamp create_at;
    private boolean is_anonymous;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.creator = new UserDTO(comment.getUser());
        this.body = comment.getContent();
        this.create_at = comment.getPubTime();
        this.is_anonymous = (comment.getIsAnony()==1)? true:false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    @Override
    public void convertToDTO(Comment comment) {
        this.id = comment.getId();
        this.creator = new UserDTO(comment.getUser());
        this.body = comment.getContent();
        this.create_at = comment.getPubTime();
        this.is_anonymous = (comment.getIsAnony()==1)? true:false;
    }
}
