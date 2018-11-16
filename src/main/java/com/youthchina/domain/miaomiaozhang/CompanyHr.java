package com.youthchina.domain.miaomiaozhang;

import lombok.Data;

@Data
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


}
