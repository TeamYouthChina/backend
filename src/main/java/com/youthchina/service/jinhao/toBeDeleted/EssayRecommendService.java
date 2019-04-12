package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface EssayRecommendService extends DomainCRUDService<ComEssay, Integer> {
    List<ComEssay> getEssayForYou();
}
