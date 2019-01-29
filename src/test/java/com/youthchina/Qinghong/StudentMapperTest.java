package com.youthchina.Qinghong;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
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
        System.out.print(educationInfos.get(0));
        if (educationInfos!=null){
            System.out.print("测试成功");
        }
        System.out.print(educationInfos.get(1).getEdu_school());
    }

    @Test
    public void testGetWorks(){
        List<Work> works=applicantMapper.getWorks(1);
        Assert.assertNotNull(works);
        System.out.print(works.get(0).getWork_company());

    }

    @Test
    public void testGetActivities(){
        List<Activity> activities=applicantMapper.getActivities(1);
        Assert.assertNotNull(activities);
        System.out.print(activities.get(0).getAct_detail());

    }

    @Test
    public void testGetProjects(){
        List<Project> projects=applicantMapper.getProjects(1);
        Assert.assertNotNull(projects);
        System.out.print(projects.get(0).getDeliver_pub_insti());

    }

    @Test
    public void testGetCertificates(){
        List<Certificate> certificates=applicantMapper.getCertificates(1);
        Assert.assertNotNull(certificates);
        System.out.print(certificates.get(0).getCertificate_insti());

    }

    @Test
    public void testGetStudentInfo(){
        Student student=applicantMapper.getStudentInfo(1);
        Assert.assertNotNull(student);
        System.out.print(student.getLabelInfos().get(0).getLabel_chn());

    }


    @Test
    public void testAddApply(){
        JobApply jobApply=new JobApply();
        jobApply.setJob_cv_send(1);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        jobApply.setJob_apply_time(d);
        jobApply.setJob_apply_status("success");
        jobApply.setStu_id(1);
        jobApply.setJob_id(1);

        Integer key=applicantMapper.addApply(jobApply);
        if(key!=0){
            System.out.print("测试成功");
        }
        System.out.print(applicantMapper.getJobApplies(1).get(0).getJob_cv_send());



    }

    @Test
    public void testGetJobApplies(){
        List<JobApply> jobApplies=applicantMapper.getJobApplies(1);
        Assert.assertNotNull(jobApplies);
        System.out.print(jobApplies.get(0).getJob_cv_send());

    }

    @Test
    public void testGetUserInfo(){
        UserInfo userInfo=applicantMapper.getUserInfo(1);
        Assert.assertNotNull(userInfo);
        System.out.print(userInfo.getUser_pass());

    }

    @Test
    public void testDeleteJobCollect(){
        Integer key=applicantMapper.deleteJobCollect(1);
        if(key!=0){
            System.out.print("测试成功");
        }
        System.out.print(key);

    }

    @Test
    public void testDeleteCompCollect(){
        Integer key=applicantMapper.deleteCompCollect(1);
        if(key!=0){
            System.out.print("测试成功");
        }
        System.out.print(key);

    }

    @Test
    public void testGetJobCollect(){
        List<JobCollect> jobCollects=applicantMapper.getJobCollects(1);
        Assert.assertNotNull(jobCollects);
        System.out.print(jobCollects.get(0).getJob_id());

    }

    @Test
    public void testGetCompCollect(){
        List<CompCollect> compCollects=applicantMapper.getCompCollects(1);
        Assert.assertNotNull(compCollects);
        System.out.print(compCollects.get(0).getCompany().getCompanyNature().getNatureDetail());
    }

    @Test
    public void testGetOneJobCollect(){
        JobCollect jobCollect=applicantMapper.getOneJobCollect(1);
        System.out.print(jobCollect.getJob_id());
        Assert.assertNotNull(jobCollect);
        System.out.print(jobCollect.getJob_id());
    }

    @Test
    @Rollback
    public void testAddJobCollect(){
        JobCollect jobCollect=new JobCollect();
        jobCollect.setJob_id(1);
        jobCollect.setStu_id(1);
        jobCollect.setIs_delete(1);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        jobCollect.setJob_coll_time(d);
        jobCollect.setIs_delete_time(d);
        Integer integer=applicantMapper.addJobCollect(jobCollect);
        JobCollect jobCollect1=applicantMapper.getOneJobCollect(1);
        if (integer!=0){
            System.out.print(integer);
        }



    }



}

