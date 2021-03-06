package com.youthchina.service.community;

import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.util.List;

public interface EvaluateService {
    void upvote(Evaluatable entity, Integer userId) throws NotFoundException;
    void downvote(Evaluatable entity, Integer userId) throws NotFoundException;
    void cancel(Evaluatable entity, Integer userId) throws NotFoundException;
    void cancel(Evaluatable entity);
    Integer countUpvote(Evaluatable entity) throws NotFoundException;
    Integer countDownvote(Evaluatable entity);
    Integer evaluateStatus(Evaluatable evaluatable, Integer userId);
    List<Integer> getMyUpvote(Evaluatable evaluatable, Integer userId);
    List<Integer> getMyDownvote(Evaluatable evaluatable, Integer userId);
}
