package com.youthchina.dao.jinhao;


import com.youthchina.domain.jinhao.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RecommendMapper {
    void addTag(@Param("labelCode") String labelCode, @Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
    String isTagExist(@Param("labelCode") String labelCode, @Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
    void deleteTag(@Param("labelCode") String labelCode, @Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
    List<Label> getLabel(List<String> userLabels);
    List<String> getUserLabel(Integer userId);
    List<Integer> getRecommendQuestion(List<String> userLabels);
    List<Integer> getRecommendEassy(List<String> userLabels);
    List<Integer> getRecommendBriefReview(List<String> userLabels);
    List<Integer> getRecommendJob(List<String> userLabels);
    List<Integer> getRecommendCompany(List<String> userLabels);
    List<Integer> getRecommendUser(List<String> userLabels);
}
