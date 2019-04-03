package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.toBeDeleted.BriefReviewRecommendServiceImplement;
import com.youthchina.service.tianjian.StaticFileService;
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
    @Autowired
    private BriefReviewRecommendServiceImplement briefReviewRecommendServiceImplement;
    @Autowired
    private StaticFileService staticFileService;

    @GetMapping("/editorials")
    public ResponseEntity getRecommandBriefReviews() throws NotFoundException {
        List<BriefReview> briefReviewList = briefReviewRecommendServiceImplement.getBriefReviewForYou();
        List<BriefReviewResponseDTO> resultList = new ArrayList<>();

        for(BriefReview briefReview : briefReviewList) {
            resultList.add(new BriefReviewResponseDTO(briefReview));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("editorials", resultList);

        if (resultList!=null)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400,"fail")));
    }
}
