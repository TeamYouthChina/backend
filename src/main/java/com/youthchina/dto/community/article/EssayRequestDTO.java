package com.youthchina.dto.community.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.util.RichTextDTORequest;
import com.youthchina.dto.util.RichTextDTOResponse;

public class EssayRequestDTO {
    private Integer id;
    private String title;
    private Integer company_id;
    private boolean is_anonymous;
    private RichTextDTORequest body;

    public EssayRequestDTO(ComEssay comEssay) {
        this.id = comEssay.getEssayId();
        this.title = comEssay.getEssayTitle();
        this.is_anonymous = (comEssay.getUserAnony()==1)? true:false;
        this.body = new RichTextDTORequest(comEssay.getRichText());
    }

    public EssayRequestDTO() {
    }

    public RichTextDTORequest getBody() {
        return body;
    }

    public void setBody(RichTextDTORequest body) {
        this.body = body;
    }

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
