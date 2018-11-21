package com.youthchina.domain.jinhao;

/**
 * create by jinhaohu on 11/12/18
 */
public class Company {

    /*
     * 企业id
     * 主键
     */
    private Integer company_id;

    /*
     * 企业名称
     */
    private String company_name;

    /*
     * 企业三证号码
     */
    private String company_code;

    /*
     * 企业国别
     */
    private String company_country;

    /*
     * 企业简介
     */
    private String introduc;

    /*
     * 企业规模
     */
    private String company_scale;

    /*
     * 企业性质
     */
    private String company_nature;

    /*
     * 企业所在地
     */
    private String company_location;

    /*
     * 企业邮箱
     */
    private String company_mail;

    /*
     * 企业官网
     */
    private String company_website;

    /*
     * 成立日期
     */
    private String company_start_data;

    /*
     * 企业logo
     */
    private String company_logo;

    /*
     * 企业认证
     */
    private Integer company_verify;

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public void setCompany_country(String company_country) {
        this.company_country = company_country;
    }

    public void setIntroduc(String introduc) {
        this.introduc = introduc;
    }

    public void setCompany_scale(String company_scale) {
        this.company_scale = company_scale;
    }

    public void setCompany_nature(String company_nature) {
        this.company_nature = company_nature;
    }

    public void setCompany_location(String company_location) {
        this.company_location = company_location;
    }

    public void setCompany_mail(String company_mail) {
        this.company_mail = company_mail;
    }

    public void setCompany_website(String company_website) {
        this.company_website = company_website;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public String getCompany_country() {
        return company_country;
    }

    public String getIntroduc() {
        return introduc;
    }

    public String getCompany_scale() {
        return company_scale;
    }

    public String getCompany_nature() {
        return company_nature;
    }

    public String getCompany_location() {
        return company_location;
    }

    public String getCompany_mail() {
        return company_mail;
    }

    public String getCompany_website() {
        return company_website;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_start_data() {
        return company_start_data;
    }

    public void setCompany_start_data(String company_start_data) {
        this.company_start_data = company_start_data;
    }

    public Integer getCompany_verify() {
        return company_verify;
    }

    public void setCompany_verify(Integer company_verify) {
        this.company_verify = company_verify;
    }
}
