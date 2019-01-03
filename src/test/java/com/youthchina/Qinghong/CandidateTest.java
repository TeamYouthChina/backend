package com.youthchina.Qinghong;

import com.youthchina.dao.Qinghong.ApplicantMapper;
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

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEducations() throws NotFoundException {
        UserInfo userInfo=null;
        List<EducationInfo> educationInfos=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getEducations(0)).thenReturn(educationInfos);
        Assert.assertEquals(studentService.getEducations(0),educationInfos);
    }
    @Test
    public void getWorks() throws NotFoundException {
        UserInfo userInfo=null;
        List<Work> works=new ArrayList<>();
        Mockito.when(applicantMapper.getUserInfo(0)).thenReturn(userInfo);
        Mockito.when(applicantMapper.getWorks(0)).thenReturn(works);
        Assert.assertEquals(studentService.getWorks(0),works);

    }

    @Test
    public void getActivities() throws NotFoundException {
        UserInfo userInfo=null;
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
        job.setJobEndTime(new Date(2018,12,30));
        Assert.assertEquals(studentService.jobApply(0,0),job);



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



}
