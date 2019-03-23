package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;

/**
 * @program: youthchina
 * @description: 简历存储
 * @author: Qinghong Wang
 * @create: 2019-03-23 14:19
 **/
public class ResumeStore {
    private Integer store_id;
    private String resume_name;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getResume_name() {
        return resume_name;
    }

    public void setResume_name(String resume_name) {
        this.resume_name = resume_name;
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
