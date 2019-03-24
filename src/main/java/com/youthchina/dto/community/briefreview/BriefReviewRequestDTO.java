package com.youthchina.dto.community.briefreview;

import com.youthchina.dto.util.RichTextDTOResponse;

public class RequestBriefReviewDTO {
    private RichTextDTOResponse body;

    private Integer company_id;

    public RichTextDTOResponse getBody() {
        return body;
    }

    public void setBody(RichTextDTOResponse body) {
        this.body = body;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
