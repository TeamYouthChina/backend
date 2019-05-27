package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.BriefReview;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BriefReviewMapper {
    BriefReview get(Integer id);
    void add(BriefReview briefReview);
    void delete(Integer id);
    void update(BriefReview briefReview);
    Integer checkIfBriefReviewExist(Integer id);
    Integer count();
    List<BriefReview> getMyBriefReview(Integer id);
}
