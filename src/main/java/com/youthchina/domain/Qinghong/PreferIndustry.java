package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class PreferIndustry {
    private Integer pre_ind_id;
    private Integer pre_ind_num;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getPre_ind_id() {
        return pre_ind_id;
    }

    public void setPre_ind_id(Integer pre_ind_id) {
        this.pre_ind_id = pre_ind_id;
    }

    public Integer getPre_ind_num() {
        return pre_ind_num;
    }

    public void setPre_ind_num(Integer pre_ind_num) {
        this.pre_ind_num = pre_ind_num;
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
