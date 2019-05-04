package com.youthchina.service.community;

import com.youthchina.dao.jinhao.EvaluateMapper;
import com.youthchina.domain.jinhao.Evaluate;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Resource
    EvaluateMapper evaluateMapper;

    @Resource
    IsExistService isExistService;

    @Override
    @Transactional
    public void upvote(Evaluatable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        isExistService.isExist(entity);
        Evaluate evaluate = evaluateMapper.isEverEvaluate(type, id, userId);
        if (evaluate == null) {
            evaluateMapper.upvote(type, id, userId);
        } else {
            if (evaluate.getType() == 1) {
                throw new NotFoundException(4040, 404, "You have already upvoted! You cannot upvote again!");//todo
            } else {
                evaluateMapper.reUpvote(evaluate.getId());
            }
        }
    }

    @Override
    @Transactional
    public void downvote(Evaluatable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        isExistService.isExist(entity);
        Evaluate evaluate = evaluateMapper.isEverEvaluate(type, id, userId);
        if (evaluate == null) {
            evaluateMapper.downvote(type, id, userId);
        } else {
            if (evaluate.getType() == 2) {
                throw new NotFoundException(4040, 404, "You have already downvoted! You cannot downvote again!");//todo
            } else {
                evaluateMapper.reDownVote(evaluate.getId());
            }
        }
    }

    @Override
    @Transactional
    public void cancel(Evaluatable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        isExistService.isExist(entity);
        Evaluate evaluate = evaluateMapper.isEverEvaluate(type, id, userId);
        if (evaluate == null || evaluate.getType() == 3) {
            throw new NotFoundException(4040, 404, "You have not evaluated! You cannot cancel!");//todo
        } else {
            evaluateMapper.cancel(evaluate.getId());
        }
    }

    @Override
    public void cancel(Evaluatable entity) {
        evaluateMapper.cancelAllEvaluate(entity.getEvaluateTargetType(),entity.getId());
    }

    @Override
    @Transactional
    public Integer countUpvote(Evaluatable entity) {
        Integer type = entity.getEvaluateTargetType();
        Integer id = entity.getId();
        return evaluateMapper.countUpvote(type, id);
    }

    @Override
    public Integer countDownvote(Evaluatable entity){
        return evaluateMapper.countDownvote(entity.getEvaluateTargetType(), entity.getId());
    }

    @Override
    public Integer evaluateStatus(Evaluatable evaluatable, Integer userId) {
        Evaluate evaluate = evaluateMapper.isEverEvaluate(evaluatable.getEvaluateTargetType(),evaluatable.getId(),userId);
        if(evaluate == null){
            return 3;
        }else{
            return evaluate.getType();
        }
    }

    @Override
    public List<Integer> getMyUpvote(Evaluatable evaluatable, Integer userId) {
        return evaluateMapper.getMyUpvote(evaluatable.getEvaluateTargetType(), userId);
    }

    @Override
    public List<Integer> getMyDownvote(Evaluatable evaluatable, Integer userId) {
        return evaluateMapper.getMyDownVote(evaluatable.getEvaluateTargetType(), userId);
    }
}
