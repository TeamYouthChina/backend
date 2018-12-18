package com.youthchina.controller.Tianjian;

import com.youthchina.service.Tianjian.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@RestController
@RequestMapping("/users/**")
public class UserController {
    @Resource
    UserServiceImpl internPageService;

}
