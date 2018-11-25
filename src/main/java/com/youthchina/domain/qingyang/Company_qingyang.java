package com.youthchina.domain.qingyang;
/*
* 企业ID		唯一标识符		COMPANY_ID	INTEGER		否
企业名称				COMPANY_NAME	VARCHAR(200)	否
企业三证号码				COMPANY_CODE	VARCHAR(200)		否
企业国别				COMPANY_COUNTRY	VARCHAR(10)		否
企业简介				COMPANY_INTRODUC	VARCHAR(200)		是
企业规模				COMPANY_SCALE	VARCHAR(200)		否
企业性质				COMPANY_NATURE	VARCHAR(200)		否
企业所在地				COMPANY_LOCATION	VARCHAR(200)	否	否
企业邮箱				COMPANY_MAIL	VARCHAR(200)		是
企业官网				COMPANY_WEBSITE	VARCHAR(200)		是
成立日期				COMPANY_START_DATE	VARCHAR(200)		是
企业LOGO				COMPANY_LOGO	VARCHAR(200)		是
企业认证				COMPANY_VERIFY	INTEGER		是*/

public class Company_qingyang {
    private Integer company_id;
    private String company_name;
    private String company_code;
    private String company_country;
    private String company_introduc;
    private String company_scale;
    private String company_nature;
    private String company_location;
    private String company_mail;
    private String company_website;
    private String company_start_date;
    private String company_logo;
    private Integer company_verify;


    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_country() {
        return company_country;
    }

    public void setCompany_country(String company_country) {
        this.company_country = company_country;
    }

    public String getCompany_introduc() {
        return company_introduc;
    }

    public void setCompany_introduc(String company_introduc) {
        this.company_introduc = company_introduc;
    }

    public String getCompany_scale() {
        return company_scale;
    }

    public void setCompany_scale(String company_scale) {
        this.company_scale = company_scale;
    }

    public String getCompany_nature() {
        return company_nature;
    }

    public void setCompany_nature(String company_nature) {
        this.company_nature = company_nature;
    }

    public String getCompany_location() {
        return company_location;
    }

    public void setCompany_location(String company_location) {
        this.company_location = company_location;
    }

    public String getCompany_mail() {
        return company_mail;
    }

    public void setCompany_mail(String company_mail) {
        this.company_mail = company_mail;
    }

    public String getCompany_website() {
        return company_website;
    }

    public void setCompany_website(String company_website) {
        this.company_website = company_website;
    }

    public String getCompany_start_date() {
        return company_start_date;
    }

    public void setCompany_start_date(String company_start_date) {
        this.company_start_date = company_start_date;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public Integer getCompany_verify() {
        return company_verify;
    }

    public void setCompany_verify(Integer company_verify) {
        this.company_verify = company_verify;
    }
}
