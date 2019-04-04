package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Question;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface QuestionMapper {
    Question get(Integer id);
    void add(Question question);
    void delete(Integer id);
    void edit(Question question);
    Integer checkIfQuestionExist(Integer id);
    Integer count();
}
