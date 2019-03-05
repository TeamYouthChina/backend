package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface BriefReviewRecommendService extends DomainCRUDService<BriefReview, Integer> {
    List<BriefReview> getBriefReviewForYou() throws NotFoundException;
}
