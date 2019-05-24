package com.youthchina.service.recommendation;

import com.youthchina.dao.jinhao.RecommendMapper;
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
import com.youthchina.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Resource
    RecommendMapper recommendMapper;

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

    @Resource
    UserService userService;



    @Override
    @Transactional
    public void addTag(int labelCode, int targetType, int targetId) throws NotFoundException {
        switch (targetType){
            case 1: questionService.get(targetId); break;
            case 2: essayService.get(targetId); break;
            case 3: briefReviewService.get(targetId); break;
            case 100: userService.get(targetId); break;
            case 200: companyCURDService.get(targetId); break;
            case 300: jobService.get(targetId); break;
            default: throw new NotFoundException(404,404,"The target type does not exist!");
        }
        recommendMapper.addTag(labelCode,targetType,targetId);
    }

    @Override
    @Transactional
    public void deleteTag(int labelCode, int targetType, int targetId) throws NotFoundException {
        Integer id = recommendMapper.isTagExist(labelCode,targetType,targetId);
        if(id == null) throw new NotFoundException(404,404,"This label does not exist and cannot be deleted");
        recommendMapper.deleteTag(labelCode,targetType,targetId);
    }

    @Override
    @Transactional
    public List<User> getRecommendUser(int userId) throws NotFoundException{
        List<Integer> userLabels = recommendMapper.getUserLabel(userId);
        if(userLabels.size() == 0){
            throw new NotFoundException(404,404,"User do not have any labels");
        }
        List<Integer> userIds = recommendMapper.getRecommendUser(userLabels);
        List<User> users = new LinkedList<>();
        for(Integer id : userIds){
            if(id == userId) continue;
            try {
                users.add(userService.get(id));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    @Transactional
    public List<Company> getRecommendCompany(int userId) throws NotFoundException{
        List<Integer> userLabels = recommendMapper.getUserLabel(userId);
        if(userLabels.size() == 0){
            throw new NotFoundException(404,404,"User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendCompany(userLabels);
        return companyCURDService.get(ids);
    }

    @Override
    @Transactional
    public List<ComEssay> getRecommendEssay(int userId) throws NotFoundException{
        List<Integer> userLabels = recommendMapper.getUserLabel(userId);
        if(userLabels.size() == 0){
            throw new NotFoundException(404,404,"User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendEassy(userLabels);
        return essayService.get(ids);
    }

    @Override
    public List<Question> getRecommendQuestion(int userId) throws NotFoundException{
        List<Integer> userLabels = recommendMapper.getUserLabel(userId);
        if(userLabels.size() == 0){
            throw new NotFoundException(404,404,"User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendQuestion(userLabels);
        return questionService.get(ids);
    }

    @Override
    public List<Job> getRecommendJob(int userId) throws NotFoundException{
        List<Integer> userLabels = recommendMapper.getUserLabel(userId);
        if(userLabels.size() == 0){
            throw new NotFoundException(404,404,"User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendJob(userLabels);
        return jobService.get(ids);
    }

    @Override
    public List<BriefReview> getRecommendBriefReview(int userId) throws NotFoundException{
        List<Integer> userLabels = recommendMapper.getUserLabel(userId);
        if(userLabels.size() == 0){
            throw new NotFoundException(404,404,"User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendBriefReview(userLabels);
        return briefReviewService.get(ids);
    }

}
