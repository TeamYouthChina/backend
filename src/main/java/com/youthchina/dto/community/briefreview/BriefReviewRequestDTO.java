package com.youthchina.dto.community.briefreview;

import com.youthchina.dto.util.RichTextDTORequest;

public class BriefReviewRequestDTO {
    private RichTextDTORequest body;

    private Integer company_id;

    public RichTextDTORequest getBody() {
        return body;
    }

    public void setBody(RichTextDTORequest body) {
        this.body = body;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
