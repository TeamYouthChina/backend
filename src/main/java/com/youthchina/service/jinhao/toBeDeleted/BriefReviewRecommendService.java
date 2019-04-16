package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface BriefReviewRecommendService extends DomainCRUDService<BriefReview, Integer> {
    List<BriefReview> getBriefReviewForYou() throws NotFoundException;
}
