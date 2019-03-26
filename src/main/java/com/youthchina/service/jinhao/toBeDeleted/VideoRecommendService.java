package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.domain.jinhao.Video;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface VideoRecommendService extends DomainCRUDService<Video, Integer> {
    List<Video> getVideoForYou() throws NotFoundException;
}
