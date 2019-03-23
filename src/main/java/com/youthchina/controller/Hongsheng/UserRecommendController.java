package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.service.jinhao.communityQA.UserRecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/discovery")
public class UserRecommendController {
    private final UserRecommendServiceImplement userRecommendServiceImplement;

    @Autowired
    public UserRecommendController(UserRecommendServiceImplement userRecommendServiceImplement) {
        this.userRecommendServiceImplement = userRecommendServiceImplement;
    }

    @GetMapping("/users")
    public ResponseEntity getRecommendUser() {
        List<User> userList = userRecommendServiceImplement.getUserForYou();
        List<UserDTO> resultList = new ArrayList<>();
        for (User user : userList) {
            resultList.add(new UserDTO(user));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("users", resultList);

        if (resultList.size() != 0)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400, "fail")));
    }
}
