package com.youthchina.service.recommendation;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.util.List;

public interface RecommendService {
    void addTag(int labelCode, int targetType, int targetId) throws NotFoundException;
    void deleteTag(int labelCode, int targetType, int targetId);
    List<User> getRecommendUser(int userId);
    List<Company> getRecommendCompany(int userId);
    List<ComEssay> getRecommendEssay(int userId);
    List<Question> getRecommendQuestion(int userId);
    List<Job> getRecommendJob(int userId);
    List<BriefReview> getRecommendBriefReview(int userId);
}
