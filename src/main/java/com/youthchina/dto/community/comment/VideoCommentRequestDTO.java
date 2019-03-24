package com.youthchina.dto.community.comment;

import com.youthchina.domain.jinhao.Comment;

public class VideoCommentDTO {
    private String body;
    private boolean is_anonymous;

    public VideoCommentDTO(Comment videocomment){
        this.body = videocomment.getContent();
        this.is_anonymous = (videocomment.getIsAnony()==1)? true:false;
    }

    public VideoCommentDTO(){}

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
