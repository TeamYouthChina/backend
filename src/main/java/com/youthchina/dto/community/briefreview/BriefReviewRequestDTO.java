package com.youthchina.dto.community.briefreview;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;

public class BriefReviewRequestDTO implements RequestDTO<BriefReview> {
    private RichTextRequestDTO body;

    private Integer company_id;

    public RichTextRequestDTO getBody() {
        return body;
    }

    public void setBody(RichTextRequestDTO body) {
        this.body = body;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    @Override
    public BriefReview convertToDomain() {
        return new BriefReview(this);
    }
}
