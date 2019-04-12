package com.youthchina.dto.applicant;

import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.dto.RequestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-28
 **/
public class ResumeJsonRequestDTO implements RequestDTO<ResumeJson> {
    String resume;
    Integer userId;

    public ResumeJsonRequestDTO() {
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public ResumeJson convertToDomain() {
        return new ResumeJson(this);
    }
}
