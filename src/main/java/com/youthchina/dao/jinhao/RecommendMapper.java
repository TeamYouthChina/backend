package com.youthchina.dao.jinhao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RecommendMapper {
    void addTag(@Param("labelCode") Integer labelCode, @Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
    Integer isTagExist(@Param("labelCode") Integer labelCode, @Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
    void deleteTag(@Param("labelCode") Integer labelCode, @Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
    List<Integer> getUserLabel(Integer userId);
    List<Integer> getRecommendQuestion(List<Integer> userLabels);
    List<Integer> getRecommendEassy(List<Integer> userLabels);
    List<Integer> getRecommendBriefReview(List<Integer> userLabels);
    List<Integer> getRecommendJob(List<Integer> userLabels);
    List<Integer> getRecommendCompany(List<Integer> userLabels);
    List<Integer> getRecommendUser(List<Integer> userLabels);
}
