package com.youthchina.domain.Qinghong;

import java.util.Date;

public class Project {
    private int proj_id;
    private String proj_name;
    private String proj_role;
    private String proj_start_time;
    private String proj_end_time;
    private String proj_delivery;
    private String delevery_publish;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }

    public String getProj_name() {
        return proj_name;
    }

    public void setProj_name(String proj_name) {
        this.proj_name = proj_name;
    }

    public String getProj_role() {
        return proj_role;
    }

    public void setProj_role(String proj_role) {
        this.proj_role = proj_role;
    }

    public String getProj_start_time() {
        return proj_start_time;
    }

    public void setProj_start_time(String proj_start_time) {
        this.proj_start_time = proj_start_time;
    }

    public String getProj_end_time() {
        return proj_end_time;
    }

    public void setProj_end_time(String proj_end_time) {
        this.proj_end_time = proj_end_time;
    }

    public String getProj_delivery() {
        return proj_delivery;
    }

    public void setProj_delivery(String proj_delivery) {
        this.proj_delivery = proj_delivery;
    }

    public String getDelevery_publish() {
        return delevery_publish;
    }

    public void setDelevery_publish(String delevery_publish) {
        this.delevery_publish = delevery_publish;
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
