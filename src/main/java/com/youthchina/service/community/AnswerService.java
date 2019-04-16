package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface AnswerService extends DomainCRUDService<Answer, Integer> {

    List<Answer> getAnswers(Integer id, int start, int end);
    List<Answer> getAnswers(Integer id);
    /**
     * to judge if one answer exists or not
     * @param id id of answer
     * @throws NotFoundException
     */
    void isAnswerExist(Integer id) throws NotFoundException;

    Integer countAnswersOfQuestion(Integer id);

    List<Answer> getMyAnswers(Integer id);
}
