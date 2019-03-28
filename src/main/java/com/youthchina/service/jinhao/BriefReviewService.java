package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;


public interface BriefReviewService extends DomainCRUDService<BriefReview, Integer> {
    void isBriefReviewExist(Integer id) throws NotFoundException;
}
