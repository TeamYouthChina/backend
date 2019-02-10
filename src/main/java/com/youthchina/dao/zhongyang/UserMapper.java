package com.youthchina.dao.zhongyang;

import com.youthchina.domain.zhongyang.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface UserMapper {
    User findOne(Integer id);

    List<User> findAll(List<Integer> ids);

    int insert(User user);

    void delete(Integer id);

    void update(User user);

    boolean canRegister(User user);
}
