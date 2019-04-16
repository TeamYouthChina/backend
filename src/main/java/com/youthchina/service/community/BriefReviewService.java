package com.youthchina.service.community;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;


public interface BriefReviewService extends DomainCRUDService<BriefReview, Integer> {
    void isBriefReviewExist(Integer id) throws NotFoundException;
    Integer count();
    List<BriefReview> getMyBriefReview(Integer id);
}
