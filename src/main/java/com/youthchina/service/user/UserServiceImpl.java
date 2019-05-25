package com.youthchina.service.user;

import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.security.VerifyEmailDTO;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.service.util.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMapper mapper;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private MessageSendService messageSendService;

    @Autowired
    public UserServiceImpl(UserMapper mapper, PasswordEncoder passwordEncoder, JwtService jwtService, MessageSendService messageSendService) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.messageSendService = messageSendService;
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
        return this.setRole(user);
    }

    /**
     * @param email email for user
     * @return user object, or null if such user does not exist.
     */
    @Override
    public User getUserByEmail(String email) {
        User user = mapper.findByEmail(email);
        return this.setRole(user);
    }

    @Override
    public User register(User user) {
        if (user == null) {
            return null;
        }
        User existUser = this.getUserByEmail(user.getEmail());// find user with such email, which maybe haven't verify email
        if (existUser == null) {
            //if new register, first add
            user = this.add(user);
        } else {
            //if exist but not verify email
            //update information and send verify email again
            existUser.setFirstName(user.getFirstName());
            existUser.setLastName(user.getLastName());
            existUser.setRegisterTime(Timestamp.from(Calendar.getInstance().toInstant()));
            existUser.setGender(user.getGender());
            user = this.update(existUser);
        }
        VerifyEmailDTO verifyEmailDTO = new VerifyEmailDTO(user, jwtService.encodeRegisterToken(user));
        messageSendService.sendMessage(verifyEmailDTO);
        return user;
    }

    @Override
    public void verifyEmail(@Nonnull String token) throws ForbiddenException {
        Integer id = this.jwtService.decodeRegisterToken(token);
        User user = this.get(id);
        user.setMailVerified(true);
        this.update(user);
    }

    private User setRole(User user) {
        if (user != null) {
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
        user.setModifiedTime(Timestamp.from(Instant.now())); //set modified time
        mapper.update(user);
        mapper.setRole(user.getId(), user.getRole());
        return this.get(user.getId());
    }

    @Override
    public User add(User user) {
        encryptPassword(user); //encryptPassword
        mapper.insert(user);
        mapper.setRole(user.getId(), user.getRole());
        return this.get(user.getId());
    }

    @Override
    public Boolean canRegister(User user) {
        return mapper.canRegister(user);
    }

    private void encryptPassword(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }

}
