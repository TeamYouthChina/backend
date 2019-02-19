package com.youthchina.controller.zhongyang;

import com.youthchina.exception.zhongyang.ClientException;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RegisterUserDTO;
import com.youthchina.dto.Response;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongyangwu on 2/10/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/register/**")
public class RegisterController {

    UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity register(@RequestBody RegisterUserDTO registerUser) throws ClientException {
        User user = new User(registerUser);
        if (userService.canRegister(user)) {
            user = userService.add(user);
            return ResponseEntity.ok(new Response(user));
        }
        throw new ClientException("cannot register because there are already user registered with same email or username");
    }
}
