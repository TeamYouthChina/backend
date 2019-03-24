package com.youthchina.dto.community.briefreview;

import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.RichTextDTO;

public class BriefReviewRequestDTO implements RequestDTO {
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
