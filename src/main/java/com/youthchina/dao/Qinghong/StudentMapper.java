package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Job;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentMapper {
    List<Integer> addEducationInfos(List<EducationInfo> educationInfos);

    List<Integer> addWorks(List<Work> works);

    List<Integer> addActivities(List<Activity> activities);

    List<Integer> addProjects(List<Project> projects);

    List<Integer> addJobCollects(List<JobCollect> jobCollects);

    List<Integer> addCompCollects(List<CompCollect> compCollects);

    List<Integer> addLabels(List<AdvantageLabel> advantageLabels);

    List<Integer> addPreJobs(List<PreferJob> preferJobs);

    List<Integer> addPreIndustries(List<PreferIndustry> preferIndustries);

    List<Integer> addPreCities(List<PreferCity> preferCities);

    List<Integer> addNotifications(List<Noti> notis);

    List<Integer> addJobApplies(List<JobApply> jobApplies);

    Integer addSubInfo(SubInfo subInfo);

    Integer addPreSalary(PreferSalary preferSalary);

    Integer addIntroducationVideo(IntroductionVideo introductionVideo);

    Integer add(Student student);

    Student getByPrimaryKey(Integer stu_id);

    List<CompCollect> getAllCompCollect(Integer stu_id);

    List<JobCollect> getAllJobCollect(Integer stu_id);

    Integer addOneCompCollect(CompCollect compCollect);

    CompCollect getOneCompCollect(Integer stu_id, Integer job_id);

    Integer addOneJobCollect(JobCollect jobCollect);

    JobCollect getOneJobCollect(Integer stu_id, Integer job_id);

    List<JobApply> getAllJobApply(Integer stu_id);

    Job getByJobId(Integer job_id);
}
