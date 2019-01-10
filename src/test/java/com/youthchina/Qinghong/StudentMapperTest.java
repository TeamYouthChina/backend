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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
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
        if (educationInfos!=null){
            System.out.print("测试成功");
        }
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
        System.out.print(student.getUsername());

    }


    @Test
    public void testAddApply(){
        JobApply jobApply=new JobApply();
        jobApply.setJob_cv_send(2);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        jobApply.setJob_apply_time(d);
        jobApply.setJob_apply_status("success");
        jobApply.setStu_id(2);
        jobApply.setJob_id(2);

        Integer key=applicantMapper.addApply(jobApply);
        if(key!=0){
            System.out.print("测试成功");
        }



    }

    @Test
    public void testGetJobApplies(){
        List<JobApply> jobApplies=applicantMapper.getJobApplies(1);
        Assert.assertNotNull(jobApplies);

    }

    @Test
    public void testGetUserInfo(){
        UserInfo userInfo=applicantMapper.getUserInfo(1);
        Assert.assertNotNull(userInfo);

    }

    @Test
    public void testDeleteJobCollect(){
        Integer key=applicantMapper.deleteJobCollect(1);
        if(key!=0){
            System.out.print("测试成功");
        }

    }

    @Test
    public void testDeleteCompCollect(){
        Integer key=applicantMapper.deleteCompCollect(1);
        if(key!=0){
            System.out.print("测试成功");
        }

    }

    @Test
    public void testGetJobCollect(){
        List<JobCollect> jobCollects=applicantMapper.getJobCollects(1);
        Assert.assertNotNull(jobCollects);
    }

    @Test
    public void testGetCompCollect(){
        List<CompCollect> compCollects=applicantMapper.getCompCollects(1);
        Assert.assertNotNull(compCollects);
    }

    @Test
    public void testGetOneJobCollect(){
        JobCollect jobCollect=applicantMapper.getOneJobCollect(1);
        System.out.print(jobCollect.getJob_id());
        Assert.assertNotNull(jobCollect);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddJobCollect(){
        JobCollect jobCollect=new JobCollect();
        jobCollect.setJob_id(3);
        jobCollect.setStu_id(3);
        jobCollect.setJob_coll_time(new Date());
        jobCollect.setIs_delete(1);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        jobCollect.setIs_delete_time(d);
        Integer integer=applicantMapper.addJobCollect(jobCollect);
        JobCollect jobCollect1=applicantMapper.getOneJobCollect(3);



    }



}

