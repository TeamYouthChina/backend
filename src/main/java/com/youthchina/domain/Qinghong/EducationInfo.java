package com.youthchina.domain.Qinghong;

import java.util.Date;

public class EducationInfo {
    private int edu_id;
    private String edu_degree;
    private String edu_school;
    private String edu_school_country;
    private String edu_school_location;
    private String edu_major;
    private String edu_college;
    private String edu_gpa;
    private String edu_start;
    private String edu_end;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getEdu_id() {
        return edu_id;
    }

    public void setEdu_id(int edu_id) {
        this.edu_id = edu_id;
    }

    public String getEdu_degree() {
        return edu_degree;
    }

    public void setEdu_degree(String edu_degree) {
        this.edu_degree = edu_degree;
    }

    public String getEdu_school() {
        return edu_school;
    }

    public void setEdu_school(String edu_school) {
        this.edu_school = edu_school;
    }

    public String getEdu_school_country() {
        return edu_school_country;
    }

    public void setEdu_school_country(String edu_school_country) {
        this.edu_school_country = edu_school_country;
    }

    public String getEdu_school_location() {
        return edu_school_location;
    }

    public void setEdu_school_location(String edu_school_location) {
        this.edu_school_location = edu_school_location;
    }

    public String getEdu_major() {
        return edu_major;
    }

    public void setEdu_major(String edu_major) {
        this.edu_major = edu_major;
    }

    public String getEdu_college() {
        return edu_college;
    }

    public void setEdu_college(String edu_college) {
        this.edu_college = edu_college;
    }

    public String getEdu_gpa() {
        return edu_gpa;
    }

    public void setEdu_gpa(String edu_gpa) {
        this.edu_gpa = edu_gpa;
    }

    public String getEdu_start() {
        return edu_start;
    }

    public void setEdu_start(String edu_start) {
        this.edu_start = edu_start;
    }

    public String getEdu_end() {
        return edu_end;
    }

    public void setEdu_end(String edu_end) {
        this.edu_end = edu_end;
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
