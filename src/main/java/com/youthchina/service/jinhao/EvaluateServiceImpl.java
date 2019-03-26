package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.EvaluateMapper;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class EvaluateServiceImpl implements EvaluateService{

    @Resource
    EvaluateMapper evaluateMapper;

    @Resource
    QuestionService questionService;

    @Resource
    AnswerService answerService;

    @Resource
    BriefReviewService briefReviewService;

    @Resource
    CommentService commentService;

    @Resource
    VideoService videoService;

    @Resource
    DiscussService discussService;

    @Override
    @Transactional
    public void upvote(Evaluatable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        switch (type){
            case 1: questionService.isQuestionExist(id); break;
            case 3: briefReviewService.isBriefReviewExist(id); break;
            case 4: videoService.isVideoExist(id); break;
            case 5: commentService.isCommentExist(id); break;
            case 6: discussService.isDiscussExist(id); break;
            case 7: answerService.isAnswerExist(id); break;
            default:
                throw new NotFoundException(404,404,"No such type");
        }
        evaluateMapper.upvote(type,id,userId);
    }

    @Override
    @Transactional
    public void downvote(Evaluatable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        switch (type){
            case 1: questionService.isQuestionExist(id); break;
            case 3: briefReviewService.isBriefReviewExist(id); break;
            case 4: videoService.isVideoExist(id); break;
            case 5: commentService.isCommentExist(id); break;
            case 6: discussService.isDiscussExist(id); break;
            case 7: answerService.isAnswerExist(id); break;
            default:
                throw new NotFoundException(404,404,"No such type");
        }
        evaluateMapper.downvote(type,id,userId);
    }

    @Override
    @Transactional
    public void cancel(Evaluatable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        switch (type){
            case 1: questionService.isQuestionExist(id); break;
            case 3: briefReviewService.isBriefReviewExist(id); break;
            case 4: videoService.isVideoExist(id); break;
            case 5: commentService.isCommentExist(id); break;
            case 6: discussService.isDiscussExist(id); break;
            case 7: answerService.isAnswerExist(id); break;
            default:
                throw new NotFoundException(404,404,"No such type");
        }
        evaluateMapper.cancel(type,id,userId);
    }

    @Override
    @Transactional
    public Integer countUpvote(Evaluatable entity) throws NotFoundException{
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        switch (type){
            case 1: questionService.isQuestionExist(id); break;
            case 3: briefReviewService.isBriefReviewExist(id); break;
            case 4: videoService.isVideoExist(id); break;
            case 5: commentService.isCommentExist(id); break;
            case 6: discussService.isDiscussExist(id); break;
            case 7: answerService.isAnswerExist(id); break;
            default:
                throw new NotFoundException(404,404,"No such type");
        }
        return evaluateMapper.countUpvote(type,id);
    }
}
