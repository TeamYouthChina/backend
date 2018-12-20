package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Job_qingyang;

import java.util.List;

public interface ApplicantMapper {
    List<EducationInfo> getEducations(Integer id);
    List<Work> getWorks(Integer id);
    List<Activity> getActivities(Integer id);
    List<Project> getProjects(Integer id);
    Student getStudentInfo(Integer id);
    Job_qingyang getJob(Integer job_id);
    Integer addApply(JobApply jobApply);
    List<JobApply> getJobApplies(Integer user_id);
}
