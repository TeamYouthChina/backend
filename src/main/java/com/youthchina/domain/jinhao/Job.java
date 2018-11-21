package com.youthchina.domain.jinhao;

/**
 * create by jinhaohu on 11/12/18
 */
public class Job {
    /*
 * 主键,职位ID
     */
    private Integer job_id;

    /*
     * 职位名称
     */
    private String job_name;

    /*
     * 职位分类
     */
    private Integer job_class;

    /*
     * 行业分类
     */
    private Integer job_ind_class;

    /*
     * 职位起始时间
     */
    private String job_start_time;

    /*
     * 职位结束时间
     */
    private String job_end_time;
    /*
     *  是否全职
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
    private String job_req;

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
    private String cv_recei_mail;

    /*
     * 简历命名规则
     */
     private String cv_name_rule;

     /*
      * 职位发布者
      */
     private Integer job_pub_hr;

     /*
      * 职位状态
      */
     private Integer job_active;




    public void setJob_name(String job_name) {
        this.job_name = job_name;
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

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public void setJob_highlight(String job_highlight) {
        this.job_highlight = job_highlight;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public String getJob_name() {
        return job_name;
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

    public String getJob_location() {
        return job_location;
    }

    public String getJob_highlight() {
        return job_highlight;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public Integer getJob_class() {
        return job_class;
    }

    public void setJob_class(Integer job_class) {
        this.job_class = job_class;
    }

    public Integer getJob_ind_class() {
        return job_ind_class;
    }

    public void setJob_ind_class(Integer job_ind_class) {
        this.job_ind_class = job_ind_class;
    }

    public String getJob_start_time() {
        return job_start_time;
    }

    public void setJob_start_time(String job_start_time) {
        this.job_start_time = job_start_time;
    }

    public String getJob_end_time() {
        return job_end_time;
    }

    public void setJob_end_time(String job_end_time) {
        this.job_end_time = job_end_time;
    }

    public String getJob_req() {
        return job_req;
    }

    public void setJob_req(String job_req) {
        this.job_req = job_req;
    }

    public String getCv_recei_mail() {
        return cv_recei_mail;
    }

    public void setCv_recei_mail(String cv_recei_mail) {
        this.cv_recei_mail = cv_recei_mail;
    }

    public String getCv_name_rule() {
        return cv_name_rule;
    }

    public void setCv_name_rule(String cv_name_rule) {
        this.cv_name_rule = cv_name_rule;
    }

    public Integer getJob_pub_hr() {
        return job_pub_hr;
    }

    public void setJob_pub_hr(Integer job_pub_hr) {
        this.job_pub_hr = job_pub_hr;
    }

    public Integer getJob_active() {
        return job_active;
    }

    public void setJob_active(Integer job_active) {
        this.job_active = job_active;
    }
}
