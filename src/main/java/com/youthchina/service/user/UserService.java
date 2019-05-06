package com.youthchina.service.user;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.DomainCRUDService;

/**
 * Created by zhongyangwu on 11/8/18.
 */
public interface UserService extends DomainCRUDService<User, Integer> {

    Boolean canRegister(User user);

    User getUserByEmail(String email);
}
