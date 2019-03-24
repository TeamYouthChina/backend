package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.briefreview.BriefReviewDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.BriefReviewRecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/discovery")
public class BriefReviewRecommendController {
    private final BriefReviewRecommendServiceImplement briefReviewRecommendServiceImplement;

    @Autowired
    public BriefReviewRecommendController(BriefReviewRecommendServiceImplement briefReviewRecommendServiceImplement) {
        this.briefReviewRecommendServiceImplement = briefReviewRecommendServiceImplement;
    }

    @GetMapping("/editorials")
    public ResponseEntity getRecommendBriefReviews() throws NotFoundException {
        List<BriefReview> briefReviewList = briefReviewRecommendServiceImplement.getBriefReviewForYou();
        List<BriefReviewDTO> resultList = new ArrayList<>();

        for (BriefReview briefReview : briefReviewList) {
            resultList.add(new BriefReviewDTO(briefReview));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("editorials", resultList);

        if (resultList.size() != 0)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400, "fail")));
    }
}
