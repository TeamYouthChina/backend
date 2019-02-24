package com.youthchina.dto.community;

import com.youthchina.dto.RichTextDTO;

public class RequestBriefReviewDTO {
    private RichTextDTO body;

    private Integer company_id;

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
        this.body = body;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
