package com.youthchina.service.community;

import com.youthchina.dao.jinhao.AttentionMapper;
import com.youthchina.domain.jinhao.Attention;
import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {
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

    @Resource
    EssayService essayService;

    @Override
    @Transactional
    public void attention(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
        Integer id = entity.getId();
        switch (type) {
            case 1:
                questionService.isQuestionExist(id);
                break;
            case 2:
                essayService.get(id);
                break;
            case 3:
                briefReviewService.isBriefReviewExist(id);
                break;
            case 4:
                videoService.isVideoExist(id);
                break;
            case 5:
                commentService.isCommentExist(id);
                break;
            case 6:
                discussService.isDiscussExist(id);
                break;
            case 7:
                answerService.isAnswerExist(id);
                break;
            default:
                throw new NotFoundException(4040, 404, "No such type");//todo
        }
        Attention attention = attentionMapper.isEverAttention(type, id, userId);
        if (attention == null) {
            attentionMapper.follow(type, id, userId);
        } else {
            if (attention.getIsCancel() == 0) {
                throw new NotFoundException(4040, 404, "You have already followed!");//todo
            }
            attentionMapper.reFollow(attention.getId());
        }

    }

    @Override
    @Transactional
    public void cancel(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
        Integer id = entity.getId();
        switch (type) {
            case 1:
                questionService.isQuestionExist(id);
                break;
            case 2:
                essayService.get(id);
                break;
            case 3:
                briefReviewService.isBriefReviewExist(id);
                break;
            case 4:
                videoService.isVideoExist(id);
                break;
            case 5:
                commentService.isCommentExist(id);
                break;
            case 6:
                discussService.isDiscussExist(id);
                break;
            case 7:
                answerService.isAnswerExist(id);
                break;
            default:
                throw new NotFoundException(4040, 404, "No such type");//todo
        }
        Attention attention = attentionMapper.isEverAttention(type, id, userId);
        if (attention == null || attention.getIsCancel() == 1) {
            throw new NotFoundException(4040, 404, "You have not followed yet, you cannot cancel!");//todo
        } else {
            attentionMapper.cancel(attention.getId());
        }
    }

    @Override
    @Transactional
    public List<Integer> getAllIdsOfAttention(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
        return attentionMapper.getAllfollows(type, userId);
    }

    @Override
    public List<Integer> getAllIdsOfAttention(Integer targetType, Integer userId) {
        return attentionMapper.getAllfollows(targetType, userId);
    }

    @Override
    public Integer isEverAttention(Attentionable entity, Integer userId) {
        Attention attention = attentionMapper.isEverAttention(entity.getAttentionTargetType(), entity.getId(), userId);
        if (attention == null) {
            return 0;
        } else {
            return attention.getIsCancel();
        }
    }

    @Override
    public Integer countAttention(Attentionable entity) {
        return attentionMapper.countAttention(entity.getAttentionTargetType(), entity.getId());
    }

    @Override
    public void cancel(Attentionable attentionable) {
        this.attentionMapper.cancelAttentionByEntity(attentionable.getId(), attentionable.getAttentionTargetType());
    }
}
