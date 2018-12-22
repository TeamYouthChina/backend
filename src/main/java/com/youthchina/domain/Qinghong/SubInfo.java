package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class SubInfo {
    private Integer sub_id;
    private String sub_course;
    private String sub_honor;
    private String sub_award;
    private String sub_skill;
    private String sub_foreign;
    private String sub_interest;
    private String sub_introduction;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getSub_id() {
        return sub_id;
    }

    public void setSub_id(Integer sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_course() {
        return sub_course;
    }

    public void setSub_course(String sub_course) {
        this.sub_course = sub_course;
    }

    public String getSub_honor() {
        return sub_honor;
    }

    public void setSub_honor(String sub_honor) {
        this.sub_honor = sub_honor;
    }

    public String getSub_award() {
        return sub_award;
    }

    public void setSub_award(String sub_award) {
        this.sub_award = sub_award;
    }

    public String getSub_skill() {
        return sub_skill;
    }

    public void setSub_skill(String sub_skill) {
        this.sub_skill = sub_skill;
    }

    public String getSub_foreign() {
        return sub_foreign;
    }

    public void setSub_foreign(String sub_foreign) {
        this.sub_foreign = sub_foreign;
    }

    public String getSub_interest() {
        return sub_interest;
    }

    public void setSub_interest(String sub_interest) {
        this.sub_interest = sub_interest;
    }

    public String getSub_introduction() {
        return sub_introduction;
    }

    public void setSub_introduction(String sub_introduction) {
        this.sub_introduction = sub_introduction;
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
