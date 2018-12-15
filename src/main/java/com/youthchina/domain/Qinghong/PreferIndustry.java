package com.youthchina.domain.Qinghong;

import java.util.Date;

public class PreferIndustry {
    private int pre_ind_id;
    private String pre_ind;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;


    public int getPre_ind_id() {
        return pre_ind_id;
    }

    public void setPre_ind_id(int pre_ind_id) {
        this.pre_ind_id = pre_ind_id;
    }

    public String getPre_ind() {
        return pre_ind;
    }

    public void setPre_ind(String pre_ind) {
        this.pre_ind = pre_ind;
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
