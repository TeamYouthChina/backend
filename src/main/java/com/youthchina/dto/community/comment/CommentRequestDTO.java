package com.youthchina.dto.community.comment;

import com.youthchina.dto.util.RichTextResponseDTO;

public class CommentRequestDTO {
    private RichTextResponseDTO body;
    private boolean is_anonymous;

    public RichTextResponseDTO getBody() {
        return body;
    }

    public void setBody(RichTextResponseDTO body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
