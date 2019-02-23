package com.youthchina.dto;

import com.youthchina.domain.Qinghong.EducationInfo;

/**
 * Created by zhong on 2018/12/30.
 */
public class EducationDTO {
    private String university;
    private String major;
    private String degree;
    private DurationDTO duration;
    private LocationDTO location;
    private String note;

    public EducationDTO() {
    }

    public EducationDTO(EducationInfo educationInfo){
        this.university = educationInfo.getEdu_college();
        this.major = educationInfo.getEdu_major();
        this.degree = educationInfo.getDegree().getDegreeChn();
        this.duration = new DurationDTO(educationInfo.getEdu_start(), educationInfo.getEdu_end());
        this.location = new LocationDTO(educationInfo.getLocation());//todo: fixme

    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public DurationDTO getDuration() {
        return duration;
    }

    public void setDuration(DurationDTO duration) {
        this.duration = duration;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
