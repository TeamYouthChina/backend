package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface QuestionRecommendService extends DomainCRUDService<Question, Integer> {
    List<Question> getQuestionForYou();
}
