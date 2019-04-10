package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Question;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {
    Question get(Integer id);
    void add(Question question);
    void delete(Integer id);
    void edit(Question question);
    Integer checkIfQuestionExist(Integer id);
    Integer count();
    List<Question> getListQuestions(@Param("relaType") Integer relaType, @Param("relaId") Integer relaId);
}
