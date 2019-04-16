package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface QuestionService extends DomainCRUDService<Question, Integer> {
    Question getBasicQuestion(Integer id);
    Integer count();
    List<Question> get(Integer relaType, Integer relaId)throws NotFoundException;
    List<Question> getMyQuestion(Integer id);
}
