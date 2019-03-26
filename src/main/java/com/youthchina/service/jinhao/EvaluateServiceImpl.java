package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.EvaluateMapper;
import com.youthchina.domain.jinhao.Evaluate;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
        Evaluate evaluate = evaluateMapper.isEverEvaluate(type,id,userId);
        if(evaluate == null){
            evaluateMapper.upvote(type,id,userId);
        }else{
            if(evaluate.getType() == 1){
                throw new NotFoundException(404,404,"You have already upvoted! You cannot upvote again!");
            }else{
                evaluateMapper.reUpvote(evaluate.getId());
            }
        }
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
        Evaluate evaluate = evaluateMapper.isEverEvaluate(type,id,userId);
        if(evaluate == null){
            evaluateMapper.downvote(type,id,userId);
        }else{
            if(evaluate.getType() == 2){
                throw new NotFoundException(404,404,"You have already downvoted! You cannot downvote again!");
            }else{
                evaluateMapper.reDownVote(evaluate.getId());
            }
        }
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
        Evaluate evaluate = evaluateMapper.isEverEvaluate(type,id,userId);
        if(evaluate == null || evaluate.getType() == 3){
            throw new NotFoundException(404,404,"You have not evaluated! You cannot cancel!");
        }else{
            evaluateMapper.cancel(evaluate.getId());
        }
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

    @Override
    public Evaluate get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Evaluate> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Evaluate update(Evaluate evaluate) throws NotFoundException {
        return null;
    }

    @Override
    public Evaluate add(Evaluate entity) throws NotFoundException {
        return null;
    }
}
