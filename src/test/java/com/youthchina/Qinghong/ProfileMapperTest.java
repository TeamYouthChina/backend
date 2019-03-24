package com.youthchina.Qinghong;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.Qinghong.ApplicantMapper;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Country;
import com.youthchina.domain.qingyang.Degree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Date;

/**
 * @program: youthchina
 * @description: 对于有用的学生Profile的Mapper测试
 * @author: Qinghong Wang
 * @create: 2019-03-24 15:05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:testnew.xml"})
public class ProfileMapperTest {
    @Autowired
    ApplicantMapper applicantMapper;

    @Test
    public void testGetStudentInfo() {
        Student student = applicantMapper.getStudentInfo(1);
        System.out.print(student.getLabelInfos().get(0).getLabel_chn());

    }

    @Test
    public void testGetEduById(){
        EducationInfo educationInfo=applicantMapper.getEducationById(1);
    }

    @Test
    public void testGetWorkById(){
        Work work=applicantMapper.getWorkById(1);

    }

    @Test
    public void testGetCertificateById(){
        Certificate certificate=applicantMapper.getCertificateById(1);

    }

    @Test
    public void testGetProjectById(){
        Project project=applicantMapper.getProjectById(1);

    }

    @Test
    public void testGetActivityById(){
        Activity activity=applicantMapper.getActivityById(1);

    }

    @Test
    public void testAddEduInfo() {
        EducationInfo educationInfo = new EducationInfo();
        Degree degree = new Degree();
        degree.setDegreeNum(1);
        University university=new University();
        university.setUnivers_id(1);
        educationInfo.setUniversity(university);
        educationInfo.setDegree(degree);
        educationInfo.setEdu_major("计算机");
        educationInfo.setEdu_college("cssa");
        educationInfo.setEdu_gpa((float) 3.3);
        educationInfo.setEdu_start(new Date());
        educationInfo.setEdu_end(new Date());
        educationInfo.setStu_id(1);
        educationInfo.setIs_delete(0);
        Integer integer = applicantMapper.insertEduInfo(educationInfo);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddSubCertificate() {
        Certificate certificate = new Certificate();
        Country country=new Country();
        country.setCountryAbbre("CHN");
        certificate.setInsti_country(country);
        certificate.setCertificate_name("计算机证书");
        certificate.setCertificate_insti("sa");
        certificate.setDocu_local_id("1234");
        certificate.setCertificate_grant_date(new java.sql.Date(1));
        certificate.setCertificate_expir_date(new java.sql.Date(1));
        certificate.setStu_id(1);

        Integer integer = applicantMapper.insertStuCertificate(certificate);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddStuProject() {
        Project project = new Project();
        Country country=new Country();
        country.setCountryAbbre("CHN");
        project.setInsti_country(country);
        project.setProj_institute("教育部");
        project.setProj_name("1");
        project.setProj_role("1");
        project.setProj_start_time(new Date());
        project.setProj_end_time(new Date());
        project.setProj_deliver("1");
        project.setDeliver_publish(1);
        project.setDeliver_pub_insti("1");
        project.setStu_id(1);
        project.setIs_delete(0);
        Integer integer = applicantMapper.insertStuProject(project);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddStuWork() {
        Work work = new Work();
        Location location = new Location();
        location.setRegion_num(1);
        work.setLocation(location);
        work.setWork_company("1");
        work.setWork_position("1");
        work.setWork_sector("1");
        work.setWork_start_time(new Date());
        work.setWork_end_time(new Date());
        work.setWork_duty("1");
        work.setWork_nature(1);
        work.setStu_id(1);
        work.setIs_delete(0);
        Integer integer = applicantMapper.insertStuWork(work);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddStuActivity() {
        Activity activity = new Activity();
        Country country=new Country();
        country.setCountryAbbre("CHN");
        activity.setOrg_country(country);
        activity.setAct_name("1");
        activity.setAct_organization("1");
        activity.setAct_role("1");
        activity.setAct_start_time(new Date());
        activity.setAct_end_time(new Date());
        activity.setAct_detail("1");
        activity.setStu_id(1);
        activity.setIs_delete(0);
        Integer integer = applicantMapper.insertStuActivity(activity);
        if (integer != 0) {
            System.out.print(integer);
        }

    }

    @Test
    public void testAddStuAdvantageSkill(){
        AdvantageLabel advantageLabel=new AdvantageLabel();
        advantageLabel.setStu_id(1);
        advantageLabel.setLabel_code("11");
        applicantMapper.insertAdvantageSkills(advantageLabel);
    }

    @Test
    public void testUpdateStuEdu(){
        EducationInfo educationInfo=new EducationInfo();
        educationInfo.setEdu_id(1);
        Degree degree = new Degree();
        degree.setDegreeNum(1);
        University university=new University();
        university.setUnivers_id(1);
        educationInfo.setUniversity(university);
        educationInfo.setDegree(degree);
        educationInfo.setEdu_major("cs");
        educationInfo.setEdu_college("111");
        educationInfo.setEdu_gpa((float) 3.5);
        educationInfo.setEdu_start(new Date());
        educationInfo.setEdu_end(new Date());
        educationInfo.setStu_id(1);
        educationInfo.setIs_delete(0);
        applicantMapper.updateEducation(educationInfo);

    }

    @Test
    public void testUpdateStuCertifi(){
        Certificate certificate = new Certificate();
        Country country=new Country();
        country.setCountryAbbre("CHN");
        certificate.setInsti_country(country);
        certificate.setCertificate_name("会计证书");
        certificate.setCertificate_insti("sa");
        certificate.setDocu_local_id("1234");
        certificate.setCertificate_grant_date(new java.sql.Date(1));
        certificate.setCertificate_expir_date(new java.sql.Date(1));
        certificate.setStu_id(1);
        certificate.setCertificate_id(1);
        applicantMapper.updateCertificate(certificate);

    }

    @Test
    public void testUpdateWork(){
        Work work = new Work();
        Location location = new Location();
        location.setRegion_num(2);
        work.setLocation(location);
        work.setWork_company("1");
        work.setWork_position("1");
        work.setWork_sector("1");
        work.setWork_start_time(new Date());
        work.setWork_end_time(new Date());
        work.setWork_duty("1");
        work.setWork_nature(1);
        work.setStu_id(1);
        work.setIs_delete(0);
        work.setWork_id(1);
        applicantMapper.updateWork(work);

    }

    @Test
    public void testUpdateProject(){
        Project project = new Project();
        Country country=new Country();
        country.setCountryAbbre("CHN");
        project.setInsti_country(country);
        project.setProj_institute("教育部机构");
        project.setProj_name("1");
        project.setProj_role("1");
        project.setProj_start_time(new Date());
        project.setProj_end_time(new Date());
        project.setProj_deliver("1");
        project.setDeliver_publish(1);
        project.setDeliver_pub_insti("1");
        project.setStu_id(1);
        project.setIs_delete(0);
        project.setProj_id(1);
        applicantMapper.updateProject(project);

    }

    @Test
    public void testUpdateActivity(){
        Activity activity = new Activity();
        Country country=new Country();
        country.setCountryAbbre("CHN");
        activity.setOrg_country(country);
        activity.setAct_name("111");
        activity.setAct_organization("1");
        activity.setAct_role("1");
        activity.setAct_start_time(new Date());
        activity.setAct_end_time(new Date());
        activity.setAct_detail("1");
        activity.setStu_id(1);
        activity.setIs_delete(0);
        activity.setAct_id(1);
        applicantMapper.updateActivity(activity);

    }



    @Test
    public void testDeleteStuEDU(){
       Integer integer=applicantMapper.deleteEduInfo(1);
    }


    @Test
    public void testDeleteStuCertifi(){
        Integer integer=applicantMapper.deleteCertificate(1);

    }


    @Test
    public void testDeleteStuProject(){
        Integer integer=applicantMapper.deleteProject(1);

    }


    @Test
    public void testDeleteStuActivity(){
        Integer integer=applicantMapper.deleteActivity(1);

    }

    @Test
    public void testDeleteStuWork(){
        Integer integer=applicantMapper.deleteWork(1);

    }
    @Test
    public void testDeleteStuLabel(){
        Integer integer=applicantMapper.deleteSkill(1);

    }


}
