package com.youthchina.controller.user;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.domain.zhongyang.Role;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.security.RegisterUserDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.exception.zhongyang.exception.ClientException;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by zhongyangwu on 2/10/19.
 */
@RestController
public class RegisterController {

    UserService userService;

    @Value("${security.register.redirect}")
    private String REDIRECT_AFTER_VERIFY;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("${web.url.prefix}/applicants/register")
    @ResponseBodyDTO(UserDTO.class)
    public ResponseEntity register(@RequestBody @Valid RegisterUserDTO registerUser) throws ClientException, NotFoundException {
        User user = new User(registerUser);
        user.setRole(Role.APPLICANT);
        if (userService.canRegister(user)) {
            user = userService.register(user);
            return ResponseEntity.ok(new Response(user));
        }
        throw new ClientException("cannot register because there are already user registered with same email or username");
    }

    @GetMapping("${web.url.prefix}/applicants/register/verify/email")
    public ResponseEntity verifyEmail(@RequestParam String token) throws ForbiddenException {
        this.userService.verifyEmail(token);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(REDIRECT_AFTER_VERIFY));
        return ResponseEntity.status(HttpStatus.SEE_OTHER).headers(headers).build();
    }
}
