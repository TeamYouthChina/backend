package com.youthchina.dto.community.comment;

import com.youthchina.dto.util.RichTextDTOResponse;

public class RequestCommentDTO {
    private RichTextDTOResponse body;
    private boolean is_anonymous;

    public RichTextDTOResponse getBody() {
        return body;
    }

    public void setBody(RichTextDTOResponse body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
