package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;

public class PreferCity {
    private Integer pre_city_id;
    private String pre_region_num;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getPre_city_id() {
        return pre_city_id;
    }

    public void setPre_city_id(Integer pre_city_id) {
        this.pre_city_id = pre_city_id;
    }

    public String getPre_region_num() {
        return pre_region_num;
    }

    public void setPre_region_num(String pre_region_num) {
        this.pre_region_num = pre_region_num;
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