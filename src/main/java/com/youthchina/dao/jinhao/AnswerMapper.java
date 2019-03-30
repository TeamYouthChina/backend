package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Answer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerMapper {
    // get all answers of a question by id of question
    List<Answer> getAnswers(Integer id);
    Answer get(Integer id);
    void add(Answer answer);
    void update(Answer answer);
    void delete(Integer id);
    Integer checkIfAnswerExist(Integer id);
    Integer countAnswers(Integer id);
}
