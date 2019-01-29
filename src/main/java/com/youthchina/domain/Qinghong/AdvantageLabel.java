package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class AdvantageLabel {
    private Integer label_id;
    private String label_code;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getLabel_id() {
        return label_id;
    }

    public void setLabel_id(Integer label_id) {
        this.label_id = label_id;
    }

    public String getLabel_code() {
        return label_code;
    }

    public void setLabel_code(String label_code) {
        this.label_code = label_code;
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
