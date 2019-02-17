package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ApplicantMapper {
    List<EducationInfo> getEducations(Integer id);
    List<Work> getWorks(Integer id);
    List<Activity> getActivities(Integer id);
    List<Project> getProjects(Integer id);
    List<Certificate> getCertificates(Integer id);
    Student getStudentInfo(Integer id);
    Job getJob(Integer job_id);
    Integer addApply(JobApply jobApply);
    List<JobApply> getJobApplies(Integer stu_id);
    UserInfo getUserInfo(Integer user_id);
    List<JobCollect> getJobCollects(Integer user_id);
    List<CompCollect> getCompCollects(Integer user_id);
    JobCollect getOneJobCollect(Integer job_id);
    Integer addJobCollect(JobCollect jobCollect);
    Integer deleteJobCollect(Integer collect_id);
    Integer deleteCompCollect(Integer collect_id);
    BaseInfo getBaseInfo(Integer user_id);
    Integer addCompCollect(CompCollect compCollect);
}
