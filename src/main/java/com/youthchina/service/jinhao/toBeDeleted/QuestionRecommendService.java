package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface QuestionRecommendService extends DomainCRUDService<Question, Integer> {
    List<Question> getQuestionForYou() throws NotFoundException;
}
