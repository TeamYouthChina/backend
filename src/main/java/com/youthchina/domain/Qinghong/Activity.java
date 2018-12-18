package com.youthchina.domain.Qinghong;

import java.util.Date;

public class Activity {
    private int act_id;
    private String act_name;
    private String act_organization;
    private String act_role;
    private String act_start_time;
    private String act_end_time;
    private String act_detail;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getAct_id() {
        return act_id;
    }

    public void setAct_id(int act_id) {
        this.act_id = act_id;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_organization() {
        return act_organization;
    }

    public void setAct_organization(String act_organization) {
        this.act_organization = act_organization;
    }

    public String getAct_role() {
        return act_role;
    }

    public void setAct_role(String act_role) {
        this.act_role = act_role;
    }

    public String getAct_start_time() {
        return act_start_time;
    }

    public void setAct_start_time(String act_start_time) {
        this.act_start_time = act_start_time;
    }

    public String getAct_end_time() {
        return act_end_time;
    }

    public void setAct_end_time(String act_end_time) {
        this.act_end_time = act_end_time;
    }

    public String getAct_detail() {
        return act_detail;
    }

    public void setAct_detail(String act_detail) {
        this.act_detail = act_detail;
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
