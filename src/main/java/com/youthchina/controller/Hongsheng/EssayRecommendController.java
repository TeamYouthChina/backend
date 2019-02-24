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
import java.util.List;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/essay-for-you")
public class EssayRecommendController {
    @Autowired
    private EssayRecommendServiceImplement essayRecommendServiceImplement;

    @GetMapping("/")
    public ResponseEntity getRecommandEssay() throws NotFoundException {
        List<ComEssay> essayList = essayRecommendServiceImplement.getEssayForYou();
        List<EssayDTO> resultList = new ArrayList<>();
        for(ComEssay essay : essayList) {
            resultList.add(new EssayDTO(essay));
        }

        if (resultList!=null)
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(400,"fail")));
    }
}
