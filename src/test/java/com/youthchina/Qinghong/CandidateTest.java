package com.youthchina.Qinghong;

import com.youthchina.dao.Qinghong.ApplicantMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.Qinghong.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CandidateTest {

    @InjectMocks
    private StudentService studentService=new StudentServiceImpl();

    @Mock
    private ApplicantMapper applicantMapper;

    @Mock
    private JobMapper jobMapper;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEducations() throws NotFoundException {
        UserInfo userInfo=new UserInfo();
        List<EducationInfo> educationInfos=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getEducations(0)).thenReturn(educationInfos);
        Assert.assertEquals(studentService.getEducations(0),educationInfos);
    }
    @Test
    public void getWorks() throws NotFoundException {
        UserInfo userInfo=new UserInfo();
        List<Work> works=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getWorks(0)).thenReturn(works);
        Assert.assertEquals(studentService.getWorks(0),works);

    }

    @Test
    public void getActivities() throws NotFoundException {
        UserInfo userInfo=new UserInfo();
        List<Activity> activities=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getActivities(0)).thenReturn(activities);
        Assert.assertEquals(studentService.getActivities(0),activities);

    }

    @Test
    public void getCertificates() throws NotFoundException {
        UserInfo userInfo=new UserInfo();
        List<Certificate> certificates=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getCertificates(0)).thenReturn(certificates);
        Assert.assertEquals(studentService.getCertificates(0),certificates);

    }

    @Test
    public void getProjects() throws NotFoundException {
        UserInfo userInfo=new UserInfo();
        List<Project> projects=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getProjects(0)).thenReturn(projects);
        Assert.assertEquals(studentService.getProjects(0),projects);
    }

    @Test
    public void addJobApply() throws NotFoundException {
        Job job=new Job();
        Mockito.when(applicantMapper.getJob(0)).thenReturn(job);
        job.setJobEndTime(new Date(2019,12,30));
        JobApply jobApply=new JobApply();
        Mockito.when(applicantMapper.addApply(jobApply)).thenReturn(0);
        if(studentService.jobApply(0,0)==0){
            System.out.print("测试成功");
        }else{
            System.out.print("测试失败");
        }



    }
    @Test
    public void getJobApply() throws NotFoundException {
        UserInfo userInfo=new UserInfo();
        List<JobApply> jobApplies=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getJobApplies(0)).thenReturn(jobApplies);
        Assert.assertEquals(studentService.getJobApplies(0),jobApplies);


    }
    @Test
    public void getContacts() throws NotFoundException {
        UserInfo userInfo=new UserInfo();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Assert.assertEquals(studentService.getContacts(0),userInfo);

    }

    @Test
    public void deleteCollects() throws NotFoundException {
        Integer num1=1;
        Integer num2=1;
        Mockito.when(applicantMapper.deleteCompCollect(0)).thenReturn(num1);
        Mockito.when(applicantMapper.deleteJobCollect(0)).thenReturn(num2);
        Integer num=studentService.deleteCollect(0);
        if(num==num1+num2){
            System.out.print("测试成功");
        }else{
            System.out.print("测试失败");
        }

    }

    @Test
    public void getJobCollect() throws NotFoundException{
        UserInfo userInfo=new UserInfo();
        List<JobCollect> jobCollects=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getJobCollects(0)).thenReturn(jobCollects);
        Assert.assertEquals(studentService.getJobCollect(0),jobCollects);
    }

    @Test
    public void getCompCollect() throws NotFoundException{
        UserInfo userInfo=new UserInfo();
        List<CompCollect> compCollects=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getCompCollects(0)).thenReturn(compCollects);
        Assert.assertEquals(studentService.getCompCollect(0),compCollects);
    }

    @Test
    public void addJobCollect() throws NotFoundException{
        UserInfo userInfo=new UserInfo();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        JobCollect jobCollect=null;
        Mockito.when(applicantMapper.getOneJobCollect(0)).thenReturn(jobCollect);
        Job job=new Job();
        job.setIsDelete(0);
        Mockito.when(jobMapper.selectJobByJobId(0)).thenReturn(job);
        JobCollect jobCollect1=new JobCollect();
        Mockito.when(applicantMapper.getStudentInfo(0)).thenReturn(new Student());
        Mockito.when(applicantMapper.addJobCollect(jobCollect1)).thenReturn(0);
        if(studentService.addJobCollection(0,0)==0){
            System.out.print("测试成功");
        }else {
            System.out.print("测试失败");
        }

    }



}
