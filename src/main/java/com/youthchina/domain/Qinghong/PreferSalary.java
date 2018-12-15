package com.youthchina.domain.Qinghong;

import java.util.Date;

public class PreferSalary {
    private int pre_sala_id;
    private int pre_sala_cap;
    private int pre_sala_floor;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getPre_sala_id() {
        return pre_sala_id;
    }

    public void setPre_sala_id(int pre_sala_id) {
        this.pre_sala_id = pre_sala_id;
    }

    public int getPre_sala_cap() {
        return pre_sala_cap;
    }

    public void setPre_sala_cap(int pre_sala_cap) {
        this.pre_sala_cap = pre_sala_cap;
    }

    public int getPre_sala_floor() {
        return pre_sala_floor;
    }

    public void setPre_sala_floor(int pre_sala_floor) {
        this.pre_sala_floor = pre_sala_floor;
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
