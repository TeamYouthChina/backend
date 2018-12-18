package com.youthchina.jinhao;

import com.youthchina.domain.jinhao.*;
import com.youthchina.service.jinhao.InternPageService;
import com.youthchina.service.jinhao.communityQA.CommunityQAService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public class InternPageServiceTest extends BaseTest {
    @Autowired
    InternPageService internPageService;

    @Autowired
    CommunityQAService communityQAService;

    @Test
    public void testGetCompanyAndJob(){
        InternPageInfo internPageInfo = internPageService.getInternPageInfo(1,1,1);
        if(internPageInfo == null){
            System.out.println("fail");
        }else {
            System.out.println("success");
        }
    }
    @Test
    public void testGetJob(){
        Job job = internPageService.getJob(1);
        if(job == null){
            System.out.println("fail");
        }else{
            System.out.println("success");
        }
    }

    @Test
    public void testGetCompany(){
        Company company = internPageService.getCompany(1);
        if(company == null){
            System.out.println("fail");
        }else{
            System.out.println("success");
        }
    }

    @Test
    public void testGetHR(){
        HR hr = internPageService.getHR(1);
        if(hr == null){
            System.out.println("fail");
        }else {
            System.out.println("success");
        }
    }
    @Test
    public void testIfJobCollected(){
        if(internPageService.isJobCollected(1,1)){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testIfCompanyCollected(){
        if(internPageService.isCompanyCollected(1,1)){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCollectJob(){
        JobCollect jobCollect = new JobCollect();
        java.sql.Timestamp now = new Timestamp(System.currentTimeMillis());
        jobCollect.setJob_coll_time(now);
        jobCollect.setJob_id(1);
        if(internPageService.collectJob(jobCollect,1) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCollectCompany(){
        CompanyCollect companyCollect = new CompanyCollect();
        java.sql.Timestamp now = new Timestamp(System.currentTimeMillis());
        companyCollect.setCompany_coll_time(now);
        companyCollect.setCompany_id(2);
        if(internPageService.collectCompany(companyCollect, 1) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCancelCollectJob(){
        if(internPageService.cancelCollectJob(2) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCancelCollectCompany(){
        if(internPageService.cancelCollectCompany(1) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testIsEverEvaluate(){
        System.out.println(communityQAService.isEverEvaluate(1,2));
    }
//    @Test
//    public void testAddQuestion(){
//        Question question = new Question();
//        question.setQues_abbre("222");
//        int a = communityQAService.addQuestion(question);
//        System.out.println(a);
//    }
}
