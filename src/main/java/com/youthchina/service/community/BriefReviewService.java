package com.youthchina.service.community;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.service.DomainCRUDService;

import java.util.List;


public interface BriefReviewService extends DomainCRUDService<BriefReview, Integer> {
    Integer count();
    List<BriefReview> getMyBriefReview(Integer id);
}
