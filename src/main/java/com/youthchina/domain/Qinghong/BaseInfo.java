package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.List;

public class BaseInfo {
    private int stu_id;
    private String stu_in_job;
    private String stu_in_job_comp;
    private Integer user_id;
    private Integer is_delete;
    private Timestamp is_delete_Time;

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_in_job() {
        return stu_in_job;
    }

    public void setStu_in_job(String stu_in_job) {
        this.stu_in_job = stu_in_job;
    }

    public String getStu_in_job_comp() {
        return stu_in_job_comp;
    }

    public void setStu_in_job_comp(String stu_in_job_comp) {
        this.stu_in_job_comp = stu_in_job_comp;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_Time() {
        return is_delete_Time;
    }

    public void setIs_delete_Time(Timestamp is_delete_Time) {
        this.is_delete_Time = is_delete_Time;
    }
}
