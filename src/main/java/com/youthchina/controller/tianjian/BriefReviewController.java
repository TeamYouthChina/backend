package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.tianjian.ComAuthorEssayMap;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;

import com.youthchina.dto.community.BriefReviewDTO;
import com.youthchina.dto.community.EssayDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.BriefReviewServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${web.url.prefix}/editorials/**")
public class BriefReviewController {

    @Autowired
    BriefReviewServiceImplement briefReviewServiceImplement;


    @GetMapping("/{id}")
    public ResponseEntity getBriefReview(@PathVariable Integer id) throws NotFoundException {
        BriefReview briefReview = briefReviewServiceImplement.get(id);
        BriefReviewDTO briefReviewDTO = new BriefReviewDTO(briefReview);
        if (briefReviewDTO!=null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400,"fail")));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity updateBriefReview(@PathVariable Integer id, @RequestBody BriefReviewDTO briefReviewDTO, @AuthenticationPrincipal User user) throws NotFoundException {
//        BriefReview briefReview = briefReviewServiceImplement.update(new BriefReview(briefReviewDTO));
//        briefReview.setReview_id(id);
//
//        if (i!=0)
//            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(200,"success")));
//        else
//            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400,"fail")));
//    }

}
