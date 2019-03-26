package com.youthchina.domain.Qinghong;

import com.youthchina.domain.qingyang.Degree;
import com.youthchina.dto.applicant.EducationDTO;

import java.sql.Timestamp;
import java.util.Date;

public class EducationInfo {
    private Integer edu_id;
    private com.youthchina.domain.qingyang.Degree degree;
    private String edu_school;
    private String edu_school_country;
    private Location location;
    private String edu_major;
    private String edu_college;
    private Float edu_gpa;
    private Date edu_start;
    private Date edu_end;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public EducationInfo(EducationDTO educationDTO) {
        this.edu_school = educationDTO.getUniversity();
        this.edu_major = educationDTO.getMajor();
        this.degree = new Degree();
        this.degree.setDegreeNum(Integer.parseInt(educationDTO.getDegree()));
        this.edu_start = educationDTO.getDuration().getBegin();
        this.edu_end = educationDTO.getDuration().getEnd();
        this.location = new Location();
        this.location.setCountry(educationDTO.getLocation().getNation_code());
        this.location.setRegionNum(Integer.parseInt(educationDTO.getLocation().getLocation_code()));
        this.edu_school_country = educationDTO.getLocation().getNation_code();

        //fix location
        //空值的设置

    }

    public EducationInfo() {
    }

    public Integer getEdu_id() {
        return edu_id;
    }

    public void setEdu_id(Integer edu_id) {
        this.edu_id = edu_id;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Float getEdu_gpa() {
        return edu_gpa;
    }

    public void setEdu_gpa(Float edu_gpa) {
        this.edu_gpa = edu_gpa;
    }

    public Date getEdu_start() {
        return edu_start;
    }

    public void setEdu_start(Date edu_start) {
        this.edu_start = edu_start;
    }

    public Date getEdu_end() {
        return edu_end;
    }

    public void setEdu_end(Date edu_end) {
        this.edu_end = edu_end;
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
