package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.recommend.JobRecommendation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface JobRecommendMapper {
    List<JobRecommendation> getList(List<Integer> ids);
    void add(JobRecommendation jobRecommendation);
    JobRecommendation get(Integer id);
    void update(JobRecommendation jobRecommendation);
    void delete(Integer id);
}
