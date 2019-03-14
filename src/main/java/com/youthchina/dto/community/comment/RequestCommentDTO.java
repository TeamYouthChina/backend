package com.youthchina.dto.community.comment;

import com.youthchina.dto.util.RichTextDTO;

public class RequestCommentDTO {
    private RichTextDTO body;
    private boolean is_anonymous;

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
