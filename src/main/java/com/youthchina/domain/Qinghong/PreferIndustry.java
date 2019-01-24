package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class PreferIndustry {
    private Integer pre_ind_id;
    private String pre_ind_code;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;


    public Integer getPre_ind_id() {
        return pre_ind_id;
    }

    public void setPre_ind_id(Integer pre_ind_id) {
        this.pre_ind_id = pre_ind_id;
    }

    public String getPre_ind_code() {
        return pre_ind_code;
    }

    public void setPre_ind_code(String pre_ind_code) {
        this.pre_ind_code = pre_ind_code;
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
