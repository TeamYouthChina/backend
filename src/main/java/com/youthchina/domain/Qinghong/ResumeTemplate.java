package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;

/**
 * @program: V-0.1
 * @description: 应聘者简历模版表
 * @author: Qinghong Wang
 * @create: 2019-01-20 17:37
 **/
public class ResumeTemplate {
    private Integer template_id;
    private String template_path;
    private Integer stu_id;
    private Timestamp template_time;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_path() {
        return template_path;
    }

    public void setTemplate_path(String template_path) {
        this.template_path = template_path;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Timestamp getTemplate_time() {
        return template_time;
    }

    public void setTemplate_time(Timestamp template_time) {
        this.template_time = template_time;
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
