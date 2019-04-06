package com.youthchina.dto.community.video;

import com.youthchina.dto.RequestDTO;

public class VideoRequestDTO implements RequestDTO {
    private Integer company_id;

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
