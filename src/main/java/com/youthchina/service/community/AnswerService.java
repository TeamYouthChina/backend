package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface AnswerService extends DomainCRUDService<Answer, Integer> {

    List<Answer> getAnswers(Integer id, int start, int end);

    List<Answer> getAnswers(Integer id);

    Integer countAnswersOfQuestion(Integer id);

    List<Answer> getMyAnswers(Integer id);
}
