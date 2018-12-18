package com.youthchina.domain.Qinghong;

import java.util.Date;

public class AdvantageLabel {
    private int label_id;
    private int label_num;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getLabel_id() {
        return label_id;
    }

    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }

    public int getLabel_num() {
        return label_num;
    }

    public void setLabel_num(int label_num) {
        this.label_num = label_num;
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
