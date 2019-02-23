package com.youthchina.dto.community;

import com.youthchina.domain.tianjian.ComEssay;

public class RequestEssayDTO {
    private Integer id;
    private String title;
    private String body;
    private Integer company_id;

    public RequestEssayDTO(ComEssay comEssay){
        this.id = comEssay.getEssay_id();
        this.title = comEssay.getEssay_title();
        this.body = comEssay.getEssay_body();
    }
    public RequestEssayDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
