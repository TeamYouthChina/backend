package com.youthchina.domain.jinhao;

/**
 * create by jinhaohu on 11/12/18
 */
public class Job {
    /*
     * 主键,职位ID
     */
    private String job_id;

    /*
     * 企业ID
     */
    private String company_id;

    /*
     * 职位名称
     */
    private String job_name;

    /*
     * 职能分类
     */
    private String job_class;

    /*
     * 是否全职
     */
    private String job_time;

    /*
     * 职位描述
     */
    private String job_description;

    /*
     * 职责描述
     */
    private String job_duty;

    /*
     * 学历要求
     */
    private String job_edu_req;

    /*
     * 工作地点
     */
    private String job_location;

    /*
     * 职位亮点
     */
    private String job_highlight;

    /*
     * 职位薪资
     */
    private String job_salary;


    /*
     * 简历接收邮箱
     */
    private String job_recei_mail;

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public void setJob_class(String job_class) {
        this.job_class = job_class;
    }

    public void setJob_time(String job_time) {
        this.job_time = job_time;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public void setJob_duty(String job_duty) {
        this.job_duty = job_duty;
    }

    public void setJob_edu_req(String job_edu_req) {
        this.job_edu_req = job_edu_req;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public void setJob_highlight(String job_highlight) {
        this.job_highlight = job_highlight;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public void setJob_recei_mail(String job_recei_mail) {
        this.job_recei_mail = job_recei_mail;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public String getJob_class() {
        return job_class;
    }

    public String getJob_time() {
        return job_time;
    }

    public String getJob_description() {
        return job_description;
    }

    public String getJob_duty() {
        return job_duty;
    }

    public String getJob_edu_req() {
        return job_edu_req;
    }

    public String getJob_location() {
        return job_location;
    }

    public String getJob_highlight() {
        return job_highlight;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public String getJob_recei_mail() {
        return job_recei_mail;
    }
}
