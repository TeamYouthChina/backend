package com.youthchina.dto.community.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTO;

import java.sql.Timestamp;


public class CommentDTO {
    private Integer id;
    private UserDTO creator;
    private RichTextDTO body;
    private Timestamp create_at;
    private boolean is_anonymous;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.creator = new UserDTO(comment.getUser());
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comment.getContent(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
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

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
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
}
