package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface QuestionService extends DomainCRUDService<Question, Integer> {
    void isQuestionExist(Integer id) throws NotFoundException;

    /**
     * get Question without its answers
     * @param id id of question
     * @return Question entity
     * @throws NotFoundException
     */
    Question getBasicQuestion(Integer id);
    Integer count();
    List<Question> get(Integer relaType, Integer relaId)throws NotFoundException;
    List<Question> getMyQuestion(Integer id);
}