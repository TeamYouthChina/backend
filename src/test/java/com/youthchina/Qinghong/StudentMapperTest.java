package com.youthchina.Qinghong;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.Qinghong.ApplicantMapper;
import com.youthchina.dao.Qinghong.StudentMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Job;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

/**
 * @program: V-0.1
 * @description: 对于studentmapper进行测试
 * @author: Qinghong Wang
 * @create: 2019-01-01 21:43
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:applicant.xml"})
public class StudentMapperTest {
    @Autowired
    ApplicantMapper applicantMapper;

    @Test
    public void testGetEducations(){
        List<EducationInfo> educationInfos=applicantMapper.getEducations(1);
        Assert.assertNotNull(educationInfos);
    }

    @Test
    public void testGetWorks(){
        List<Work> works=applicantMapper.getWorks(1);
        Assert.assertNotNull(works);

    }

    @Test
    public void testGetActivities(){
        List<Activity> activities=applicantMapper.getActivities(1);
        Assert.assertNotNull(activities);

    }

    @Test
    public void testGetProjects(){
        List<Project> projects=applicantMapper.getProjects(1);
        Assert.assertNotNull(projects);

    }

    @Test
    public void testGetCertificates(){
        List<Certificate> certificates=applicantMapper.getCertificates(1);
        Assert.assertNotNull(certificates);

    }

    @Test
    public void testGetStudentInfo(){
        Student student=applicantMapper.getStudentInfo(1);
        Assert.assertNotNull(student);

    }

    @Test
    public void testGetJob(){
        Job job=applicantMapper.getJob(1);
        Assert.assertNotNull(job);

    }

    @Test
    public void testAddApply(){

    }

    @Test
    public void testGetJobApplies(){

    }

    @Test
    public void testGetUserInfo(){

    }

    @Test
    public void testDeleteJobCollect(){

    }

    @Test
    public void testDeleteCompCollect(){

    }


}

