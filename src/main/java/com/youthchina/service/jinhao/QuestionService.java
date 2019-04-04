package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

public interface QuestionService extends DomainCRUDService<Question, Integer> {
    void isQuestionExist(Integer id) throws NotFoundException;

    /**
     * get Question without its answers
     * @param id id of question
     * @return Question entity
     * @throws NotFoundException
     */
    Question getBasicQuestion(Integer id) throws NotFoundException;
    Integer count();
}
