package com.youthchina.domain.Qinghong;

import java.util.Date;

public class PreferCity {
    private int pre_citt_id;
    private String pre_city;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getPre_citt_id() {
        return pre_citt_id;
    }

    public void setPre_citt_id(int pre_citt_id) {
        this.pre_citt_id = pre_citt_id;
    }

    public String getPre_city() {
        return pre_city;
    }

    public void setPre_city(String pre_city) {
        this.pre_city = pre_city;
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
