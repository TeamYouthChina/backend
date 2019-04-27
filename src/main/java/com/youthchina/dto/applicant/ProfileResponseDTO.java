package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.EducationInfo;
import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.Qinghong.Work;

import java.util.Date;

/**
 * @program: youthchina
 * @description: 学生信息卡片DTO
 * @author: Qinghong Wang
 * @create: 2019-04-18 14:39
 **/
public class ProfileResponseDTO {
    private Integer id;
    private String username;
    private String name;
    private String company;
    private String postion;
    private String university;
    private String major;

    public ProfileResponseDTO(Student student) {
        this.id = student.getId();
        this.username=student.getUsername();
        this.name=student.getLastName()+student.getFirstName();
        Work work1=new Work();
        Date date=new Date(0);
        for(Work work:student.getWorks()){
            if(work!=null){
                if(work.getWork_end_time().after(date)){
                    work1=work;
                    date=work.getWork_end_time();
                }
            }
        }
        EducationInfo educationInfo=new EducationInfo();
        Date date1=new Date(0);
        for(EducationInfo educationInfo1:student.getEducationInfos()){
            if(educationInfo1!=null){
                if(educationInfo1.getEdu_end().after(date1)){
                    educationInfo=educationInfo1;
                    date1=educationInfo1.getEdu_end();
                }
            }
        }
        if(work1!=null){
            this.company=work1.getWork_company();
            this.postion=work1.getWork_position();
        }
        if(educationInfo!=null){
            if(educationInfo.getUniversity()!=null){
                this.university=educationInfo.getUniversity().getUnivers_name();
            }
            if(educationInfo.getMajor()!=null){
                this.major=educationInfo.getMajor().getMajor_chn();
            }
        }


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
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
}
