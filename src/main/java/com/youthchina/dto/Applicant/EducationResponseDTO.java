package com.youthchina.dto.Applicant;

import com.youthchina.domain.Qinghong.EducationInfo;
import com.youthchina.dto.DurationDTO;
import com.youthchina.dto.LocationDTO;

/**
 * @program: youthchina
 * @description: 教育信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:29
 **/
public class EducationResponseDTO {
    private String university;
    private String major;
    private String degree;
    private DurationDTO duration;
    private String location;
    private String note;

    public EducationResponseDTO() {
    }

    public EducationResponseDTO(EducationInfo educationInfo){
        this.university = educationInfo.getEdu_school();
        this.major = educationInfo.getEdu_major();
        this.degree = educationInfo.getDegree().getDegreeChn();
        this.duration = new DurationDTO(educationInfo.getEdu_start(), educationInfo.getEdu_end());
        this.location=educationInfo.getLocation().getRegion_chn();

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
