package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class PreferJob {
    private Integer pre_job_id;
    private String pre_job_code;
    private Date pre_avail_time;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getPre_job_id() {
        return pre_job_id;
    }

    public void setPre_job_id(Integer pre_job_id) {
        this.pre_job_id = pre_job_id;
    }

    public String getPre_job_code() {
        return pre_job_code;
    }

    public void setPre_job_code(String pre_job_code) {
        this.pre_job_code = pre_job_code;
    }

    public Date getPre_avail_time() {
        return pre_avail_time;
    }

    public void setPre_avail_time(Date pre_avail_time) {
        this.pre_avail_time = pre_avail_time;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
