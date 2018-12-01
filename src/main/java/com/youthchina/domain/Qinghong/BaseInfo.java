package com.youthchina.domain.Qinghong;

import java.util.List;

public class BaseInfo {
    private int stu_id;
    private String stu_in_job;
    private String stu_in_job_comp;
    private List<EducationInfo> educationInfos;
    private List<Work> works;

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_in_job() {
        return stu_in_job;
    }

    public void setStu_in_job(String stu_in_job) {
        this.stu_in_job = stu_in_job;
    }

    public String getStu_in_job_comp() {
        return stu_in_job_comp;
    }

    public void setStu_in_job_comp(String stu_in_job_comp) {
        this.stu_in_job_comp = stu_in_job_comp;
    }

    public List<EducationInfo> getEducationInfos() {
        return educationInfos;
    }

    public void setEducationInfos(List<EducationInfo> educationInfos) {
        this.educationInfos = educationInfos;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
