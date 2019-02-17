package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.tianjian.ComAuthorEssayMap;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;

import com.youthchina.dto.community.BriefReviewDTO;
import com.youthchina.dto.community.RequestBriefReviewDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.BriefReviewServiceImplement;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@RestController
@RequestMapping("${web.url.prefix}/editorials/**")
public class BriefReviewController {

    @Autowired
    BriefReviewServiceImplement briefReviewServiceImplement;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity getBriefReview(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = briefReviewServiceImplement.get(id);
        BriefReviewDTO briefReviewDTO = new BriefReviewDTO(briefReview);
        briefReviewDTO.setAuthor(userService.get(user.getId()));
        if (briefReviewDTO!=null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400,"fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBriefReview(@PathVariable Integer id) throws NotFoundException {
        briefReviewServiceImplement.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

//    @PostMapping
//    public ResponseEntity addBriefReview(@RequestBody RequestBriefReviewDTO requestBriefReviewDTO,@AuthenticationPrincipal User user) throws NotFoundException {
//        BriefReview briefReview = new BriefReview();
//        briefReview.setIs_delete(0);
//        briefReview.setReview_content(requestBriefReviewDTO.getBody());
//        Timestamp time =  new Timestamp(System.currentTimeMillis());
//        briefReview.setReview_time(time);
//        BriefReview briefReviewReturn =  briefReviewServiceImplement.add(briefReview);
//        BriefReviewDTO briefReviewDTO = new BriefReviewDTO();
//        briefReviewDTO.setBody(briefReviewReturn.getReview_content());
//        briefReviewDTO.setAuthor(userService.get(user.getId()));
//        briefReviewDTO.setComments();
//    }
//
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
