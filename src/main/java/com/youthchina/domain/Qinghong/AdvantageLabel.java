package com.youthchina.domain.Qinghong;

import io.swagger.models.auth.In;

import java.sql.Timestamp;
import java.util.Date;

public class AdvantageLabel {
    private Integer label_id;
    private Integer label_num;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getLabel_id() {
        return label_id;
    }

    public void setLabel_id(Integer label_id) {
        this.label_id = label_id;
    }

    public Integer getLabel_num() {
        return label_num;
    }

    public void setLabel_num(Integer label_num) {
        this.label_num = label_num;
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
