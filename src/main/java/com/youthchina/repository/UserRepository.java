package com.youthchina.repository;

import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by zhongyangwu on 5/21/19.
 */
@Component
public class UserRepository implements BaseRepository<User, Integer> {


    private final UserMapper userMapper;

    @Autowired
    public UserRepository(@Qualifier("userMapper") UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Integer get(User id) {
        return null;
    }

    @Override
    public void delete(Integer entity) {

    }
}
