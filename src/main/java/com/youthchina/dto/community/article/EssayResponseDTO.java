package com.youthchina.dto.community.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTO;

import java.sql.Timestamp;

public class EssayDTO {
    private Integer id;
    private String title;
    private CompanyResponseDTO company;
    private Timestamp create_at;
    private Timestamp modified_at;
    private UserDTO author;
    private RichTextDTO body;
    private boolean is_anonymous;

    public EssayDTO(ComEssay comEssay) {
        this.id = comEssay.getEssay_id();
        this.title = comEssay.getEssay_title();
        this.create_at = comEssay.getEssay_pub_time();
        this.modified_at = comEssay.getEssay_edit_time();
        this.is_anonymous = (comEssay.getUser_anony() == 0) ? false : true;
        try {
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssay.getEssay_body(), RichTextDTO.class);
            this.body = richt;
        } catch (Exception e) {
            System.out.println("Exception");
        }
        this.is_anonymous = (comEssay.getUser_anony() == 0) ? false : true;
    }

    public EssayDTO() {
    }

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
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
