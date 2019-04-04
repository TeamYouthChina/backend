package com.youthchina.service.zhongyang;

import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMapper mapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Get User by id.
     *
     * @param id id of User
     * @return User with id equals to param.
     */
    @Override
    public User get(Integer id) {
        User user = mapper.findOne(id);
        if(user != null) {
            user.setRole(mapper.getRoles(user.getId()));
        }
        return user;
    }

    @Override
    public List<User> get(List<Integer> id) {
        List<User> result = new ArrayList<>();
        for (Integer i : id) {
            User user = get(i);
            if (user != null) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        mapper.delete(id);
    }

    @Override
    public User update(User user) {
        mapper.update(user);
        return mapper.findOne(user.getId());
    }

    @Override
    public User add(User user) {
        encryptPassword(user); //encryptPassword
        mapper.insert(user);
        mapper.setRole(user.getId(), user.getRole());
        return mapper.findOne(user.getId());
    }

    @Override
    public Boolean canRegister(User user) {
        return mapper.canRegister(user);
    }

    private void encryptPassword(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }

}
