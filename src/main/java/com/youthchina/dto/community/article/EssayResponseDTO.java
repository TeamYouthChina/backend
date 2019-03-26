package com.youthchina.dto.community.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTOResponse;

import java.sql.Timestamp;

public class EssayResponseDTO {
    private Integer id;
    private String title;
    private CompanyResponseDTO company;
    private Timestamp create_at;
    private Timestamp modified_at;
    private UserDTO author;
    private RichTextDTOResponse body;
    private boolean is_anonymous;

    public EssayResponseDTO(ComEssay comEssay) {
        this.id = comEssay.getId();
        this.title = comEssay.getTitle();
        this.create_at = comEssay.getPubTime();
        this.modified_at = comEssay.getEditTime();
        this.is_anonymous = (comEssay.getIsAnony() == 0) ? false : true;
        this.author = new UserDTO(comEssay.getUser());
        this.body = new RichTextDTOResponse(comEssay.getBody());
    }

    public EssayResponseDTO() {
    }

    public RichTextDTOResponse getBody() {
        return body;
    }

    public void setBody(RichTextDTOResponse body) {
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

    public CompanyResponseDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDTO company) {
        this.company = company;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
