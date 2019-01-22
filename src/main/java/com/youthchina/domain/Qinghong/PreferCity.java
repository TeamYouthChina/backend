package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class PreferCity {
    private Integer pre_citt_id;
    private Integer pre_city_num;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getPre_citt_id() {
        return pre_citt_id;
    }

    public void setPre_citt_id(Integer pre_citt_id) {
        this.pre_citt_id = pre_citt_id;
    }

    public Integer getPre_city_num() {
        return pre_city_num;
    }

    public void setPre_city_num(Integer pre_city_num) {
        this.pre_city_num = pre_city_num;
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
