package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface StudentService extends DomainCRUDService<Student,Integer> {
    public List<EducationInfo> getEducations(Integer id) throws NotFoundException;
    public List<Work> getWorks(Integer id)throws NotFoundException;
    public List<Activity> getActivities(Integer id)throws NotFoundException;
    public List<Project> getProjects(Integer id)throws NotFoundException;
    public JobApply jobApply(Integer job_id,Integer stu_id)throws NotFoundException;
    public List<JobApply> getJobApplies(Integer user_id)throws NotFoundException;
    public UserInfo getContacts(Integer id) throws NotFoundException;
}
