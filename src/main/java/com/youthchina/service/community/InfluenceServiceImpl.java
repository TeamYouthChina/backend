package com.youthchina.service.community;

import com.youthchina.dao.jinhao.AnswerMapper;
import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.dao.jinhao.EvaluateMapper;
import com.youthchina.dao.jinhao.QuestionMapper;
import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.tianjian.ComEssay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InfluenceServiceImpl implements InfluenceService {

    @Resource
    AnswerMapper answerMapper;

    @Resource
    QuestionMapper questionMapper;

    @Resource
    CommunityMapper communityMapper;

    @Resource
    BriefReviewMapper briefReviewMapper;

    @Resource
    EvaluateMapper evaluateMapper;

    @Resource
    FriendsService friendsService;

    @Override
    public Integer getUserInfluence(Integer userId) {
        int influence = 0;
        List<Answer> myAnswer = answerMapper.getMyAnswers(userId);
        List<BriefReview> myBriefReview = briefReviewMapper.getMyBriefReview(userId);
        List<ComEssay> myEssay = communityMapper.getAllEssayByUserId(userId);
        influence += myAnswer.size() * 150;
        influence += questionMapper.getMyQuestion(userId).size() * 50;
        influence += myEssay.size() * 150;
        influence += myBriefReview.size() * 50;
        for(Answer answer : myAnswer){
            influence += evaluateMapper.countUpvote(answer.getEvaluateTargetType(), answer.getId()) * 2;
        }
        for(BriefReview briefReview : myBriefReview){
            influence += evaluateMapper.countUpvote(briefReview.getEvaluateTargetType(), briefReview.getId()) * 2;
        }
        for(ComEssay comEssay : myEssay){
            influence += evaluateMapper.countUpvote(comEssay.getEvaluateTargetType(), comEssay.getId()) * 2;
        }
        influence += friendsService.getFriend(userId).size() * 10;
        return influence;
    }
}
