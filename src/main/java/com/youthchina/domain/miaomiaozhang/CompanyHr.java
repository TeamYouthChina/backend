package com.youthchina.domain.miaomiaozhang;

public class CompanyHr {

    private String hr_id;
    private String hr_name;
    private String hr_gender;
    private int age;
    private String hr_mail;
    private String hr_phone;

    /**所属企业*/
    private String hr_company_id;
    /**在职验证*/
    private String hr_on_job;

    private String hr_workingcardnum;

    public String getHr_id() {
        return hr_id;
    }

    public void setHr_id(String hr_id) {
        this.hr_id = hr_id;
    }

    public String getHr_name() {
        return hr_name;
    }

    public void setHr_name(String hr_name) {
        this.hr_name = hr_name;
    }

    public String getHr_gender() {
        return hr_gender;
    }

    public void setHr_gender(String hr_gender) {
        this.hr_gender = hr_gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHr_mail() {
        return hr_mail;
    }

    public void setHr_mail(String hr_mail) {
        this.hr_mail = hr_mail;
    }

    public String getHr_phone() {
        return hr_phone;
    }

    public void setHr_phone(String hr_phone) {
        this.hr_phone = hr_phone;
    }

    public String getHr_company_id() {
        return hr_company_id;
    }

    public void setHr_company_id(String hr_company_id) {
        this.hr_company_id = hr_company_id;
    }

    public String getHr_on_job() {
        return hr_on_job;
    }

    public void setHr_on_job(String hr_on_job) {
        this.hr_on_job = hr_on_job;
    }

    public String getHr_workingcardnum() { return hr_workingcardnum; }

    public void setHr_workingcardnum(String hr_workingcardnum) { this.hr_workingcardnum = hr_workingcardnum; }
}
