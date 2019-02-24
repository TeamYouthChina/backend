package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.UserDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.UserRecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/user-for-you")
public class UserRecommendController {
    @Autowired
    public UserRecommendServiceImplement userRecommendServiceImplement;

    @GetMapping("/")
    public ResponseEntity getRecommandUser() throws NotFoundException {
        List<User> userList = userRecommendServiceImplement.getUserForYou();
        List<UserDTO> resultList = new ArrayList<>();
        for(User user : userList) {
            resultList.add(new UserDTO(user));
        }

        if (resultList!=null)
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(400,"fail")));
    }
}
