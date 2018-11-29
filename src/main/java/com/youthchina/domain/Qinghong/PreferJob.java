package com.youthchina.domain.Qinghong;

import java.util.Date;

public class PreferJob {
    private int pre_job_id;
    private String pre_job;
    private String pre_avail_time;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getPre_job_id() {
        return pre_job_id;
    }

    public void setPre_job_id(int pre_job_id) {
        this.pre_job_id = pre_job_id;
    }

    public String getPre_job() {
        return pre_job;
    }

    public void setPre_job(String pre_job) {
        this.pre_job = pre_job;
    }

    public String getPre_avail_time() {
        return pre_avail_time;
    }

    public void setPre_avail_time(String pre_avail_time) {
        this.pre_avail_time = pre_avail_time;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public Date getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Date is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
