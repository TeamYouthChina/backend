package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class PreferSalary {
    private Integer pre_sala_id;
    private Integer pre_sala_cap;
    private Integer pre_sala_floor;
    private Integer pre_prof_code;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getPre_sala_id() {
        return pre_sala_id;
    }

    public void setPre_sala_id(Integer pre_sala_id) {
        this.pre_sala_id = pre_sala_id;
    }

    public Integer getPre_sala_cap() {
        return pre_sala_cap;
    }

    public void setPre_sala_cap(Integer pre_sala_cap) {
        this.pre_sala_cap = pre_sala_cap;
    }

    public Integer getPre_sala_floor() {
        return pre_sala_floor;
    }

    public void setPre_sala_floor(Integer pre_sala_floor) {
        this.pre_sala_floor = pre_sala_floor;
    }

    public Integer getPre_prof_code() {
        return pre_prof_code;
    }

    public void setPre_prof_code(Integer pre_prof_code) {
        this.pre_prof_code = pre_prof_code;
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
