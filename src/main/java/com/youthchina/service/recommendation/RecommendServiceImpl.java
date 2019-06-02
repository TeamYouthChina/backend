package com.youthchina.service.recommendation;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Label;
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
import com.youthchina.util.dictionary.TagTargetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
    public void addTag(String labelCode, int targetType, int targetId) throws NotFoundException {
        //check whether targetId exist
        switch (targetType) {
            case TagTargetType.QUESTION: {
                questionService.get(targetId);
                break;
            }
            case TagTargetType.ARTICLE: {
                essayService.get(targetId);
                break;
            }
            case TagTargetType.EDITORIAL: {
                briefReviewService.get(targetId);
                break;
            }
            case TagTargetType.COMPANY: {
                companyCURDService.get(targetId);
                break;
            }
            case TagTargetType.JOB: {
                jobService.get(targetId);
                break;
            }
            case TagTargetType.USER: {
                userService.exist(targetId);
                break;

            }
            default:
                throw new NotFoundException(4040, 404, "The target type does not exist!");
        }
        recommendMapper.addTag(labelCode, targetType, targetId);
    }

    @Override
    @Transactional
    public void deleteTag(String labelCode, int targetType, int targetId) throws NotFoundException {
        String id = recommendMapper.isTagExist(labelCode, targetType, targetId);
        if (id == null) throw new NotFoundException(404, 404, "This label does not exist and cannot be deleted");
        recommendMapper.deleteTag(labelCode, targetType, targetId);
    }

    public boolean isTagExist(String labelCode, int targetType, int targetId) {
        return recommendMapper.isTagExist(labelCode, targetType, targetId) != null;
    }

    @Override
    public List<Label> getLabels(int targetType, int targetId) {
        List<String> labelCodes = recommendMapper.getLabelCode(targetType, targetId);
        List<Label> labels = new ArrayList<>();
        if (labelCodes.size() == 0) return labels;
        labels = recommendMapper.getLabel(labelCodes);
        return labels;
    }

    @Override
    @Transactional
    public List<User> getRecommendUser(int userId) throws NotFoundException {
        List<String> userLabels = recommendMapper.getUserLabel(userId);
        if (userLabels.size() == 0) {
            throw new NotFoundException(404, 404, "User do not have any labels");
        }
        List<Integer> userIds = recommendMapper.getRecommendUser(userLabels);
        Set<Integer> nonRepeatIds = new HashSet<>(userIds);
        List<User> users = new LinkedList<>();
        for (Integer id : nonRepeatIds) {
            if (id == userId) continue;
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
    public List<Company> getRecommendCompany(int userId) throws NotFoundException {
        List<String> userLabels = recommendMapper.getUserLabel(userId);
        if (userLabels.size() == 0) {
            throw new NotFoundException(404, 404, "User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendCompany(userLabels);
        Set<Integer> reduceRepeat = new HashSet<>(ids);
        ids = new ArrayList<>(reduceRepeat);
        return companyCURDService.get(ids);
    }

    @Override
    @Transactional
    public List<ComEssay> getRecommendEssay(int userId) throws NotFoundException {
        List<String> userLabels = recommendMapper.getUserLabel(userId);
        if (userLabels.size() == 0) {
            throw new NotFoundException(404, 404, "User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendEassy(userLabels);
        Set<Integer> reduceRepeat = new HashSet<>(ids);
        ids = new ArrayList<>(reduceRepeat);
        return essayService.get(ids);
    }

    @Override
    public List<Question> getRecommendQuestion(int userId) throws NotFoundException {
        List<String> userLabels = recommendMapper.getUserLabel(userId);
        if (userLabels.size() == 0) {
            throw new NotFoundException(404, 404, "User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendQuestion(userLabels);
        Set<Integer> reduceRepeat = new HashSet<>(ids);
        ids = new ArrayList<>(reduceRepeat);
        return questionService.get(ids);
    }

    @Override
    public List<Job> getRecommendJob(int userId) throws NotFoundException {
        List<String> userLabels = recommendMapper.getUserLabel(userId);
        if (userLabels.size() == 0) {
            throw new NotFoundException(404, 404, "User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendJob(userLabels);
        Set<Integer> reduceRepeat = new HashSet<>(ids);
        ids = new ArrayList<>(reduceRepeat);
        return jobService.get(ids);
    }

    @Override
    public List<BriefReview> getRecommendBriefReview(int userId) throws NotFoundException {
        List<String> userLabels = recommendMapper.getUserLabel(userId);
        if (userLabels.size() == 0) {
            throw new NotFoundException(404, 404, "User do not have any labels");
        }
        List<Integer> ids = recommendMapper.getRecommendBriefReview(userLabels);
        Set<Integer> reduceRepeat = new HashSet<>(ids);
        ids = new ArrayList<>(reduceRepeat);
        return briefReviewService.get(ids);
    }

}
