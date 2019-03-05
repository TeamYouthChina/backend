package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface StudentService extends DomainCRUDService<Student, Integer> {
    List<EducationInfo> getEducations(Integer id) throws NotFoundException;

    List<Work> getWorks(Integer id) throws NotFoundException;

    Student addStudent(Student student);

    List<Activity> getActivities(Integer id) throws NotFoundException;

    List<Project> getProjects(Integer id) throws NotFoundException;

    JobApply jobApply(Integer job_id,Integer user_id) throws NotFoundException;

    List<JobApply> getJobApplies(Integer user_id) throws NotFoundException;

    UserInfo getContacts(Integer id) throws NotFoundException;

    Integer deleteCollect(Integer id) throws NotFoundException;

    List<Certificate> getCertificates(Integer id) throws NotFoundException;
    List<JobCollect> getJobCollect(Integer user_id) throws NotFoundException;
    List<CompCollect> getCompCollect(Integer user_id) throws NotFoundException;
    Integer addJobCollection(Integer job_id,Integer user_id) throws NotFoundException;
    Integer deleteJobCollect(Integer collect_id) throws NotFoundException;
    Integer deleteCompCollect(Integer collect_id) throws NotFoundException;
    Integer addCompCollect(Integer company_id,Integer user_id) throws NotFoundException;
    List<EducationInfo> insertEducation(EducationInfo educationInfo,Integer user_id) throws NotFoundException;
    List<Work> insertWork(Work work,Integer user_id) throws NotFoundException;
    List<Project> insertProject(Project project,Integer user_id) throws NotFoundException;
    List<Activity> insertActivity(Activity activity,Integer user_id) throws NotFoundException;
    List<Certificate> insertCertificate(Certificate certificate,Integer user_id) throws NotFoundException;
    Integer deleteEducation(Integer id) throws NotFoundException;
    Integer deleteWork(Integer id) throws NotFoundException;
    Integer deleteProject(Integer id) throws NotFoundException;
    Integer deleteActivity(Integer id) throws NotFoundException;
    Integer deleteCertificate(Integer id) throws NotFoundException;

    List<EducationInfo> insertEducations(List<EducationInfo> educationInfos,Integer user_id) throws NotFoundException;
    List<Work> insertWorks(List<Work>  works,Integer user_id) throws NotFoundException;
    List<Project> insertProjects(List<Project> projects,Integer user_id) throws NotFoundException;
    List<Activity> insertActivities(List<Activity> activities,Integer user_id) throws NotFoundException;
    List<Certificate> insertCertificates(List<Certificate> certificates,Integer user_id)throws  NotFoundException;
    ResumeJson getResumeJson(Integer resume_id)throws NotFoundException;
    ResumeJson insertResumeJson(ResumeJson resumeJson) throws NotFoundException;
    Integer deleteResumeJson(Integer id) throws NotFoundException;
    List<ResumeJson> selectResumeJsonByStuId(Integer id) throws NotFoundException;
}