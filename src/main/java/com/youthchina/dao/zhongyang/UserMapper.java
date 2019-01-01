package com.youthchina.dao.zhongyang;

import com.youthchina.domain.zhongyang.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface UserMapper {
    User findOne(Integer id);

    void insert(User user);

    void delete(Integer id);
}
