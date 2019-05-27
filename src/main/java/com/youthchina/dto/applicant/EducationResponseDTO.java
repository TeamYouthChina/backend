package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.EducationInfo;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.util.DurationDTO;

/**
 * @program: youthchina
 * @description: 教育信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:29
 **/
public class EducationResponseDTO implements ResponseDTO<EducationInfo> {
    private Integer id;
    private String university;
    private String major;
    private String degree;
    private DurationDTO duration;
    private String note;

    public EducationResponseDTO() {
    }

    public EducationResponseDTO(EducationInfo educationInfo) {
        if(educationInfo!=null){
            if(educationInfo.getUniversity()!=null){
                this.university=educationInfo.getUniversity().getUnivers_name();
            }
            this.id = educationInfo.getEdu_id();
            if(educationInfo.getMajor()!=null){
                this.major = educationInfo.getMajor().getMajor_chn();
            }


            if(educationInfo.getDegree()!=null){
                this.degree = educationInfo.getDegree().getDegreeChn();
            }

            this.duration = new DurationDTO(educationInfo.getEdu_start(), educationInfo.getEdu_end());
        }


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public void convertToDTO(EducationInfo educationInfo) {
        this.university=educationInfo.getUniversity().getUnivers_name();
        this.id = educationInfo.getEdu_id();
        this.major = educationInfo.getMajor().getMajor_chn();
        this.degree = educationInfo.getDegree().getDegreeChn();
        this.duration = new DurationDTO(educationInfo.getEdu_start(), educationInfo.getEdu_end());
    }
}
