package com.youthchina.service.recommendation;

import com.youthchina.dao.jinhao.NewRecommendMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.application.CompanyCURDService;
import com.youthchina.service.application.JobService;
import com.youthchina.service.community.BriefReviewService;
import com.youthchina.service.community.EssayService;
import com.youthchina.service.community.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Resource
    NewRecommendMapper newRecommendMapper;

    @Resource
    BriefReviewService briefReviewService;

    @Resource
    EssayService essayService;

    @Resource
    QuestionService questionService;

    @Resource
    JobService jobService;

    @Resource
    CompanyCURDService companyCURDService;

    @Override
    public void addTag(int labelCode, int targetType, int targetId) throws NotFoundException {

    }

    @Override
    public void deleteTag(int labelCode, int targetType, int targetId) {

    }

    @Override
    public List<User> getRecommendUser(int userId) {
        return null;
    }

    @Override
    public List<Company> getRecommendCompany(int userId) {
        return null;
    }

    @Override
    public List<ComEssay> getRecommendEssay(int userId) {
        return null;
    }

    @Override
    public List<Question> getRecommendQuestion(int userId) {
        return null;
    }

    @Override
    public List<Job> getRecommendJob(int userId) {
        return null;
    }

    @Override
    public List<BriefReview> getRecommendBriefReview(int userId) {
        return null;
    }

}
