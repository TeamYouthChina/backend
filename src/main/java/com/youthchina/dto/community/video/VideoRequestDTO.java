package com.youthchina.dto.community.video;

import com.youthchina.domain.jinhao.Video;
import com.youthchina.dto.RequestDTO;

public class VideoRequestDTO implements RequestDTO<Video> {
    private Integer company_id;

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    @Override
    public Video convertToDomain() {
        return new Video();
    }
}
