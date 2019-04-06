package com.youthchina.dto.community.comment;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.RequestDTO;

public class CommentRequestDTO implements RequestDTO<Comment> {
    private String body;
    private boolean is_anonymous;

    CommentRequestDTO(Comment comment){
        this.body = comment.getContent();
        this.is_anonymous = (comment.getIsAnony()==0)? false:true;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    @Override
    public Comment convertToDomain() {
        return new Comment(this);
    }
}
