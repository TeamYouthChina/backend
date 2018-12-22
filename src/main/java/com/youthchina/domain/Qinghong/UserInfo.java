package com.youthchina.domain.Qinghong;

import com.sun.tools.corba.se.idl.constExpr.Times;

import java.sql.Timestamp;

/**
 * @program: youthchina
 * @description: 用户注册信息实体
 * @author: Qinghong Wang
 * @create: 2018-12-20 16:56
 **/
public class UserInfo {
    private Integer user_id;
    private String user_name;
    private String user_pass;
    private Timestamp user_regist_date;
    private String user_real_name;
    private String user_gender;
    private Integer user_age;
    private String user_nation;
    private String user_mail;
    private String user_phone;
    private String user_photo;
    private Integer user_location;
    private Integer role_id;
    private Integer permi_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public Timestamp getUser_regist_date() {
        return user_regist_date;
    }

    public void setUser_regist_date(Timestamp user_regist_date) {
        this.user_regist_date = user_regist_date;
    }

    public String getUser_real_name() {
        return user_real_name;
    }

    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public Integer getUser_age() {
        return user_age;
    }

    public void setUser_age(Integer user_age) {
        this.user_age = user_age;
    }

    public String getUser_nation() {
        return user_nation;
    }

    public void setUser_nation(String user_nation) {
        this.user_nation = user_nation;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public Integer getUser_location() {
        return user_location;
    }

    public void setUser_location(Integer user_location) {
        this.user_location = user_location;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getPermi_id() {
        return permi_id;
    }

    public void setPermi_id(Integer permi_id) {
        this.permi_id = permi_id;
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
