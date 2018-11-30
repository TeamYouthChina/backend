package com.youthchina.domain.Qinghong;

import java.util.Date;

public class Work {
    private int work_id;
    private String work_company;
    private String work_location;
    private String work_position;
    private String work_sector;
    private String work_start_time;
    private String work_end_time;
    private String work_duty;
    private String work_nature;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public String getWork_company() {
        return work_company;
    }

    public void setWork_company(String work_company) {
        this.work_company = work_company;
    }

    public String getWork_location() {
        return work_location;
    }

    public void setWork_location(String work_location) {
        this.work_location = work_location;
    }

    public String getWork_position() {
        return work_position;
    }

    public void setWork_position(String work_position) {
        this.work_position = work_position;
    }

    public String getWork_sector() {
        return work_sector;
    }

    public void setWork_sector(String work_sector) {
        this.work_sector = work_sector;
    }

    public String getWork_start_time() {
        return work_start_time;
    }

    public void setWork_start_time(String work_start_time) {
        this.work_start_time = work_start_time;
    }

    public String getWork_end_time() {
        return work_end_time;
    }

    public void setWork_end_time(String work_end_time) {
        this.work_end_time = work_end_time;
    }

    public String getWork_duty() {
        return work_duty;
    }

    public void setWork_duty(String work_duty) {
        this.work_duty = work_duty;
    }

    public String getWork_nature() {
        return work_nature;
    }

    public void setWork_nature(String work_nature) {
        this.work_nature = work_nature;
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
