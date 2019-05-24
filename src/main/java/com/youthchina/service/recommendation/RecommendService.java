package com.youthchina.service.recommendation;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Label;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.util.List;

public interface RecommendService {
    void addTag(String labelCode, int targetType, int targetId) throws NotFoundException;
    void deleteTag(String labelCode, int targetType, int targetId) throws NotFoundException;
    List<Label> getUserLabels(int userId);
    List<User> getRecommendUser(int userId) throws NotFoundException;
    List<Company> getRecommendCompany(int userId) throws NotFoundException;
    List<ComEssay> getRecommendEssay(int userId) throws NotFoundException;
    List<Question> getRecommendQuestion(int userId) throws NotFoundException;
    List<Job> getRecommendJob(int userId) throws NotFoundException;
    List<BriefReview> getRecommendBriefReview(int userId) throws NotFoundException;
}
