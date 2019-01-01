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

    JobApply jobApply(Integer job_id, Integer stu_id) throws NotFoundException;

    List<JobApply> getJobApplies(Integer user_id) throws NotFoundException;

    UserInfo getContacts(Integer id) throws NotFoundException;

    Integer deleteCollect(Integer id) throws NotFoundException;

    List<Certificate> getCertificates(Integer id) throws NotFoundException;
}