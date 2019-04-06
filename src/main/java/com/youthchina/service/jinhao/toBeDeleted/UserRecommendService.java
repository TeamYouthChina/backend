package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface UserRecommendService extends DomainCRUDService<User, Integer> {
    List<User> getUserForYou();
}
