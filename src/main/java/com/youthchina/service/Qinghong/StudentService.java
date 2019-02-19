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
    Integer addStudentInfo(Student student) throws NotFoundException;
}