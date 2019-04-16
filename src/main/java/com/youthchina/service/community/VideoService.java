package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Video;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

public interface VideoService extends DomainCRUDService<Video, Integer> {
    void isVideoExist(Integer id) throws NotFoundException;
    Integer count();
}
