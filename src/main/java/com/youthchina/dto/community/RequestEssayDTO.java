package com.youthchina.dto.community;

import com.youthchina.domain.tianjian.ComEssay;

public class RequestEssayDTO {
    private String title;
    private String body;
    private Integer company_id;
    private boolean is_anonymous;

    public RequestEssayDTO(ComEssay comEssay){
        this.title = comEssay.getEssay_title();
        this.body = comEssay.getEssay_body();
        this.is_anonymous = (comEssay.getUser_anony()==1)? true:false;
    }
    public RequestEssayDTO(){}

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

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
