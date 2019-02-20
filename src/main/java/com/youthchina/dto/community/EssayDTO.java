package com.youthchina.dto.community;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;

public class EssayDTO {
    private Integer id;
    private String title;
    private String body;
    private Company company;
    private Timestamp creat_at;
    private Timestamp modified_at;
    private User user;
    private boolean user_anony;

    public EssayDTO(ComEssay comEssay){
        this.id = comEssay.getEssay_id();
        this.title = comEssay.getEssay_title();
        this.body = comEssay.getEssay_body();
        this.creat_at = comEssay.getEssay_pub_time();
        this.modified_at = comEssay.getEssay_edit_time();
        this.user_anony = (comEssay.getUser_anony() == 0)? false:true;
    }

    public EssayDTO(){}

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Timestamp getCreat_at() {
        return creat_at;
    }

    public void setCreat_at(Timestamp creat_at) {
        this.creat_at = creat_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUser_anony() {
        return user_anony;
    }

    public void setUser_anony(boolean user_anony) {
        this.user_anony = user_anony;
    }
}
