package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRecommendServiceImplement implements UserRecommendService{
    @Resource
    RecommendMapper recommendMapper;
    @Resource
    UserMapper userMapper;
     @Override
    public List<User> getUserForYou() {
        List<Integer> userIds = recommendMapper.getRandomUser();
        List<User> users = new ArrayList<>();
        for(Integer id : userIds){
            users.add(userMapper.findOne(id));
        }
        return users;
    }

    @Override
    public User get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<User> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public User update(User user) throws NotFoundException {
        return null;
    }

    @Override
    public User add(User entity) {
        return null;
    }
}
