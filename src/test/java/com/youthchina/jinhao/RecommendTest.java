package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.jinhao.communityQA.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"})
public class RecommendTest {
    @Resource
    RecommendMapper recommendMapper;

    @Resource
    UserRecommendService userRecommendService;

    @Resource
    CompanyRecommendService companyRecommendService;

    @Resource
    JobRecommendService jobRecommendService;

    @Resource
    EssayRecommendService essayRecommendService;

    @Resource
    QuestionRecommendService questionRecommendService;
    @Test
    public void recommend(){
        List<Integer> ids = recommendMapper.getRandomNewCompany();
        Assert.assertEquals(10,ids.size());
        List<Integer> ids1 = recommendMapper.getRandomEssay();
        Assert.assertEquals(10,ids1.size());
        List<Integer> ids2 = recommendMapper.getRandomIntern();
        Assert.assertEquals(10,ids2.size());
        List<Integer> ids3 = recommendMapper.getRandomJob();
        Assert.assertEquals(10,ids3.size());
        List<Integer> ids4 = recommendMapper.getRandomPopCompany();
        Assert.assertEquals(10,ids4.size());
        List<Integer> ids5 = recommendMapper.getRandomQuestion();
        Assert.assertEquals(10,ids5.size());
        List<Integer> ids6 = recommendMapper.getRandomUser();
        Assert.assertEquals(10, ids6.size());
        List<User> users = userRecommendService.getUserForYou();
        Assert.assertEquals(10,users.size());
        List<Company> companies = companyRecommendService.getNewCompanyForYou();
        Assert.assertEquals(10,companies.size());
        companies = companyRecommendService.getPopCompanyForYou();
        Assert.assertEquals(10,companies.size());
        List<Job> jobs = jobRecommendService.getInternForYou();
        Assert.assertEquals(10,jobs.size());
        jobs = jobRecommendService.getJobForYou();
        Assert.assertEquals(10,jobs.size());
        List<ComEssay> essays = essayRecommendService.getEssayForYou();
        Assert.assertEquals(10,essays.size());
        List<Question> questions = questionRecommendService.getQuestionForYou();
        Assert.assertEquals(10,questions.size());
    }

}
