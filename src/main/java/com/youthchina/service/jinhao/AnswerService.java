package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface AnswerService extends DomainCRUDService<Answer, Integer> {
    /**
     * get all the answers of a question
     * @param id id of question
     * @return a list of Answer
     */
    List<Answer> getAnswers(Integer id) throws NotFoundException;

    /**
     * add answer
     * @param answer entity of Answer
     * @return entity of Answer
     * @throws NotFoundException if the target question does not exist, throw exception
     */
    Answer addAnswer(Answer answer) throws NotFoundException;

    /**
     * to judge if one answer exists or not
     * @param id
     * @throws NotFoundException
     */
    void isAnswerExist(Integer id) throws NotFoundException;
}
