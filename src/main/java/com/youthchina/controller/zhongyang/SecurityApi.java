package com.youthchina.controller.zhongyang;

import com.youthchina.domain.zhongyang.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Documentation class for API managed by Spring Security.
 * <p>
 * Created by zhongyangwu on 11/11/18.
 */
@RestController
@RequestMapping("/login")
public class SecurityApi {

    @ApiOperation(value = "Login.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = User.class, responseHeaders = {
                    @ResponseHeader(name = "X-AUTHENTICATION", response = String.class, description = "JWT to verify identity")
            }),
            @ApiResponse(code = 401, message = "authentication fail")
    })
    @PostMapping("/")
    public void login(@ApiParam("username") @RequestParam String username, @ApiParam("Password") @RequestParam String password) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
