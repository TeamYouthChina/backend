package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: youthchina
 * @description: 学生实体，包含所有学生信息
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:08
 **/
public class Student {
    private Integer stu_id;
    private String stu_in_job;
    private String stu_in_job_comp;
    private List<EducationInfo> educationInfos;
    private SubInfo subInfo;
    private List<Project> projects;
    private List<Work> works;
    private List<Activity> activities;
    private List<JobCollect> jobCollects;
    private List<CompCollect> compCollects;
    private List<AdvantageLabel> advantageLabels;
    private List<PreferJob> preferJobs;
    private List<PreferIndustry> preferIndustries;
    private List<PreferCity> preferCities;
    private PreferSalary preferSalary;
    private IntroductionVideo introductionVideo;
    private List<Notification> notifications;
    private List<JobApply> jobApplies;
    private Integer user_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
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

    public SubInfo getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(SubInfo subInfo) {
        this.subInfo = subInfo;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<JobCollect> getJobCollects() {
        return jobCollects;
    }

    public void setJobCollects(List<JobCollect> jobCollects) {
        this.jobCollects = jobCollects;
    }

    public List<CompCollect> getCompCollects() {
        return compCollects;
    }

    public void setCompCollects(List<CompCollect> compCollects) {
        this.compCollects = compCollects;
    }

    public List<AdvantageLabel> getAdvantageLabels() {
        return advantageLabels;
    }

    public void setAdvantageLabels(List<AdvantageLabel> advantageLabels) {
        this.advantageLabels = advantageLabels;
    }

    public List<PreferJob> getPreferJobs() {
        return preferJobs;
    }

    public void setPreferJobs(List<PreferJob> preferJobs) {
        this.preferJobs = preferJobs;
    }

    public List<PreferIndustry> getPreferIndustries() {
        return preferIndustries;
    }

    public void setPreferIndustries(List<PreferIndustry> preferIndustries) {
        this.preferIndustries = preferIndustries;
    }

    public List<PreferCity> getPreferCities() {
        return preferCities;
    }

    public void setPreferCities(List<PreferCity> preferCities) {
        this.preferCities = preferCities;
    }

    public PreferSalary getPreferSalary() {
        return preferSalary;
    }

    public void setPreferSalary(PreferSalary preferSalary) {
        this.preferSalary = preferSalary;
    }

    public IntroductionVideo getIntroductionVideo() {
        return introductionVideo;
    }

    public void setIntroductionVideo(IntroductionVideo introductionVideo) {
        this.introductionVideo = introductionVideo;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<JobApply> getJobApplies() {
        return jobApplies;
    }

    public void setJobApplies(List<JobApply> jobApplies) {
        this.jobApplies = jobApplies;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
