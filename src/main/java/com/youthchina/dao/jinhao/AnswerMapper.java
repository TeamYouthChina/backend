package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Answer;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerMapper {
    // get all answers of a question by id of question
    List<Answer> getAnswers(@Param("id") Integer id, @Param("start") Integer start, @Param("rows") Integer rows);
    Answer get(Integer id);
    void add(Answer answer);
    void update(Answer answer);
    void delete(Integer id);
    Integer checkIfAnswerExist(Integer id);
    Integer countAnswers(Integer id);
}
