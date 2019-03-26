package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.AttentionMapper;
import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.NotFoundException;

import javax.annotation.Resource;
import java.util.List;

public class AttentionServiceImpl implements AttentionService{
    @Resource
    AttentionMapper attentionMapper;

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
    public void attention(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
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
        attentionMapper.follow(type,id,userId);

    }

    @Override
    public void cancel(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
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
        attentionMapper.cancel(type,id,userId);
    }

    @Override
    public List<Integer> getAllIdsOfAttention(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
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
        return attentionMapper.getAllfollows(type,userId);
    }
}
