package com.youthchina.jinhao;

import com.youthchina.domain.jinhao.Company;
import com.youthchina.domain.jinhao.InternPageInfo;
import com.youthchina.domain.jinhao.Job;
import com.youthchina.domain.jinhao.StuCollect;
import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.service.jinhao.InternPageService;
import com.youthchina.service.jinhao.communityQA.CommunityQAService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InternPageServiceTest extends BaseTest {
    @Autowired
    InternPageService internPageService;

    @Autowired
    CommunityQAService communityQAService;

    @Test
    public void testGetCompanyAndJob(){
        InternPageInfo companyAndJob = internPageService.getCompanyAndJob("1", "1");
        if(companyAndJob == null){
            System.out.println("fail");
        }else {
            System.out.println("success");
        }
    }
    @Test
    public void testGetJob(){
        Job job = internPageService.getJob("1");
        if(job == null){
            System.out.println("fail");
        }else{
            System.out.println("success");
        }
    }

    @Test
    public void testGetCompany(){
        Company company = internPageService.getCompany("1");
        if(company == null){
            System.out.println("fail");
        }else{
            System.out.println("success");
        }
    }

    @Test
    public void testIfJobCollected(){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setJob_id("1");
        stuCollect.setStu_id("1");
        if(internPageService.isJobCollected(stuCollect)){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testIfCompanyCollected(){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setCompany_id("1");
        stuCollect.setStu_id("2");
        if(internPageService.isCompanyCollected(stuCollect)){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCollectJob(){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id("1987");
        stuCollect.setJob_id("4");
        stuCollect.setJob_coll_time("2018");
        if(internPageService.collectJob(stuCollect) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCollectCompany(){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id("4");
        stuCollect.setCompany_id("500");
        stuCollect.setCompany_coll_time("2018");
        if(internPageService.collectCompany(stuCollect) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCancelCollectJob(){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id("1987");
        stuCollect.setJob_id("4");
        if(internPageService.cancelCollectJob(stuCollect) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

    @Test
    public void testCancelCollectCompany(){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id("4");
        stuCollect.setCompany_id("500");
        if(internPageService.cancelCollectJob(stuCollect) == 1){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
    @Test
    public void testAddQuestion(){
        Question question = new Question();
        question.setQues_abbre("222");
        int a = communityQAService.addQuestion(question);
        System.out.println(a);
    }
}
