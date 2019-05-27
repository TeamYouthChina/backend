package com.youthchina.dto.community.discuss;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.dto.RequestDTO;

public class DiscussRequestDTO implements RequestDTO<Discuss> {
    private String body;
    private boolean is_anonymous;

    public DiscussRequestDTO() {}

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

    @Override
    public Discuss convertToDomain() {
        return null;
    }
}
