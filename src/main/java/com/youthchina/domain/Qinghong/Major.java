package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;

/**
 * @program: youthchina
 * @description: 专业信息表
 * @author: Qinghong Wang
 * @create: 2019-04-09 09:34
 **/
public class Major {
    private Integer major_num;
    private Integer major_level;
    private String major_code;
    private String major_abbre;
    private String major_chn;
    private String major_eng;
    private Timestamp start_date;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getMajor_num() {
        return major_num;
    }

    public void setMajor_num(Integer major_num) {
        this.major_num = major_num;
    }

    public Integer getMajor_level() {
        return major_level;
    }

    public void setMajor_level(Integer major_level) {
        this.major_level = major_level;
    }

    public String getMajor_code() {
        return major_code;
    }

    public void setMajor_code(String major_code) {
        this.major_code = major_code;
    }

    public String getMajor_abbre() {
        return major_abbre;
    }

    public void setMajor_abbre(String major_abbre) {
        this.major_abbre = major_abbre;
    }

    public String getMajor_chn() {
        return major_chn;
    }

    public void setMajor_chn(String major_chn) {
        this.major_chn = major_chn;
    }

    public String getMajor_eng() {
        return major_eng;
    }

    public void setMajor_eng(String major_eng) {
        this.major_eng = major_eng;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
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
