package com.youthchina.domain.jinhao;

/**
 * create by jinhaohu on 11/12/18
 */
public class StuCollect {
    private String stu_id;
    private String job_id;
    private String job_coll_time;
    private String company_id;
    private String company_coll_time;

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_coll_time() {
        return job_coll_time;
    }

    public void setJob_coll_time(String job_coll_time) {
        this.job_coll_time = job_coll_time;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_coll_time() {
        return company_coll_time;
    }

    public void setCompany_coll_time(String company_coll_time) {
        this.company_coll_time = company_coll_time;
    }
}
