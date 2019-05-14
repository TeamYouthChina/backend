package com.youthchina.service.user;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.service.DomainCRUDService;

/**
 * Created by zhongyangwu on 11/8/18.
 */
public interface UserService extends DomainCRUDService<User, Integer> {

    /**
     * @param user user that going to register
     * @return true if for all user that is <b>not delete</b> and <b>have verified email</b>, no user has the same email as the parameter's user, else false
     */
    Boolean canRegister(User user);

    User getUserByEmail(String email);

    User register(User user);

    void verifyEmail(String token) throws ForbiddenException;
}
