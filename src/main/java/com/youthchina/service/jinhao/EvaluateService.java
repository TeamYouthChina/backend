package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Evaluate;
import com.youthchina.domain.jinhao.property.Evaluatable;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

public interface EvaluateService extends DomainCRUDService<Evaluate, Integer> {
    void upvote(Evaluatable entity, Integer userId) throws NotFoundException;
    void downvote(Evaluatable entity, Integer userId) throws NotFoundException;
    void cancel(Evaluatable entity, Integer userId) throws NotFoundException;
    Integer countUpvote(Evaluatable entity) throws NotFoundException;
}
