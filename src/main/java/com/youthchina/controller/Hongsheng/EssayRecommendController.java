package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.EssayDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.EssayRecommendServiceImplement;
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
public class EssayRecommendController {
    @Autowired
    private EssayRecommendServiceImplement essayRecommendServiceImplement;

    @GetMapping("/articles")
    public ResponseEntity getRecommandEssay() throws NotFoundException {
        System.out.println("11111");
        List<ComEssay> essayList = essayRecommendServiceImplement.getEssayForYou();
        List<EssayDTO> resultList = new ArrayList<>();
        for(ComEssay essay : essayList) {
            resultList.add(new EssayDTO(essay));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("users", resultList);
        System.out.println("11111");

        if (resultList!=null)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400,"fail")));
    }
}
