package com.youthchina.dao.jinhao;

import com.youthchina.domain.qingyang.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RecommendMapper {
    List<Integer> getRandomJob();

    List<Integer> getRandomIntern();

    List<Integer> getRandomPopCompany();

    List<Integer> getRandomNewCompany();

    List<Integer> getRandomEssay();

    List<Integer> getRandomQuestion();

    List<Integer> getRandomUser();

    List<Integer> getRandomVideo();

    List<Integer> getRandomBriefReview();

    List<Job> getList(List<Integer> ids);

    void add(Job jobRecommendation);

    Job get(Integer id);

    void update(Job jobRecommendation);

    void delete(Integer id);
}
