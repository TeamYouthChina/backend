package com.youthchina.controller.qingyang;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.UserDTO;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: For /me
 * @author: Qingyang Zhao
 * @create: 2019-02-27
 **/
@RestController
@RequestMapping("${web.url.prefix}/me")
public class MeController {

    @GetMapping("/")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user!=null) {
            return ResponseEntity.ok(new Response(new UserDTO(user)));
        } else {
            throw new ForbiddenException();
        }
    }

}

