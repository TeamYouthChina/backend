package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface VideoRecommendService extends DomainCRUDService<Video, Integer> {
    List<Video> getVideoForYou();
}
