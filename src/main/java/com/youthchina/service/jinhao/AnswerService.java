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
    List<Answer> getAnswers(Integer id);

    /**
     * to judge if one answer exists or not
     * @param id
     * @throws NotFoundException
     */
    void isAnswerExist(Integer id) throws NotFoundException;
}
