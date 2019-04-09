package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Degree;
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

    JobCollect getOneJobCollect(Integer job_id,Integer user_id);

    CompCollect getOneCompCollect(Integer company_id,Integer user_id);

    Integer addJobCollect(JobCollect jobCollect);

    Integer deleteJobCollect(Integer collect_id);

    Integer deleteCompCollect(Integer collect_id);

    BaseInfo getBaseInfo(Integer user_id);

    Integer addCompCollect(CompCollect compCollect);

    Integer insertStuInfo(Student student);

    Integer insertEduInfo(EducationInfo educationInfo);

    Integer insertSubInfo(SubInfo subInfo);

    Integer insertStuCertificate(Certificate certificate);

    Integer insertStuProject(Project project);

    Integer insertStuWork(Work work);

    Integer insertStuActivity(Activity activity);

    JobApply getOneJobApply(Integer job_id, Integer stu_id);

    Degree getDegreeNum(String degreeNum);

    Integer updateUserInfo(Student student);

    Integer insertStuLabel(AdvantageLabel advantageLabel);

    Integer deleteStuInfo(Integer stu_id);

    Integer deleteSubInfo(Integer sub_id);

    Integer deleteEduInfo(Integer edu_id);

    Integer deleteProject(Integer proj_id);

    Integer deleteWork(Integer work_id);

    Integer deleteActivity(Integer act_id);

    Integer deleteCertificate(Integer certificate_id);

    Integer deleteSkill(Integer label_id);

    Integer updateEducation(EducationInfo educationInfo);

    Integer updateWork(Work work);

    Integer updateCertificate(Certificate certificate);

    Integer updateProject(Project project);

    Integer updateActivity(Activity activity);

    Integer deleteAllEduInfo(Integer stu_id);

    Integer deleteAllProject(Integer stu_id);

    Integer deleteAllWork(Integer stu_id);

    Integer deleteAllActivity(Integer stu_id);

    Integer deleteAllCertificate(Integer stu_id);

    Integer deleteAllSkills(Integer stu_id);

    List<LabelInfo> getAllSkills();

    Integer insertAdvantageSkills(AdvantageLabel advantageLabel);

    EducationInfo getEducationById(Integer edu_id);
    Work getWorkById(Integer work_id);
    Project getProjectById(Integer proj_id);
    Certificate getCertificateById(Integer certificate_id);
    Activity getActivityById(Integer act_id);
    AdvantageLabel getAdvantageLabelById(Integer label_id);
    List<AdvantageLabel> getAdvantageLabels(Integer id);


}
