package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.EducationInfo;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.DurationDTO;
import com.youthchina.dto.util.LocationDTO;

/**
 * Created by zhong on 2018/12/30.
 */
public class EducationRequestDTO implements RequestDTO<EducationInfo> {
    private Integer id;
    private Integer university_id;
    private String major;
    private String degree;
    private DurationDTO duration;
    private LocationDTO location;
    private String note;

    public EducationRequestDTO() {
    }

    public EducationRequestDTO(EducationInfo educationInfo) {
        this.major = educationInfo.getEdu_major();
        this.degree = educationInfo.getDegree().getDegreeChn();
        this.duration = new DurationDTO(educationInfo.getEdu_start(), educationInfo.getEdu_end());

    }

    public Integer getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(Integer university_id) {
        this.university_id = university_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public EducationInfo convertToDomain() {
        return new EducationInfo(this);
    }
}
