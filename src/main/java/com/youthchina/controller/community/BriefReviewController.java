package com.youthchina.controller.community;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.briefreview.BriefReviewRequestDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.AttentionServiceImpl;
import com.youthchina.service.community.BriefReviewServiceImplement;
import com.youthchina.service.community.CommentServiceImpl;
import com.youthchina.service.community.EvaluateServiceImpl;
import com.youthchina.service.user.UserServiceImpl;
import com.youthchina.util.dictionary.CommentTargetType;
import com.youthchina.util.dictionary.RelaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("${web.url.prefix}/editorials")
public class BriefReviewController {

    @Autowired
    BriefReviewServiceImplement briefReviewServiceImplement;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    EvaluateServiceImpl evaluateService;

    @Autowired
    AttentionServiceImpl attentionService;

    @GetMapping("/{id}")
    public ResponseEntity getBriefReview(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = briefReviewServiceImplement.get(id);
        BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO(briefReview);
        if (briefReviewResponseDTO != null)
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(400, "fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBriefReview(@PathVariable Integer id) throws NotFoundException {
        briefReviewServiceImplement.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBriefReview(@PathVariable Integer id, @RequestBodyDTO(BriefReviewRequestDTO.class)  BriefReview briefReview, @AuthenticationPrincipal User user) throws NotFoundException {
        briefReview.setId(id);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        briefReview.setTime(time);
        briefReview.setUser(user);
        briefReview.setRelaType(0);
        BriefReview briefReviewReturn = briefReviewServiceImplement.update(briefReview);
        BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO(briefReviewReturn);

        return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(200, "success")));

    }

    @PostMapping
    public ResponseEntity addBriefReview(@RequestBodyDTO(BriefReviewRequestDTO.class)  BriefReview briefReview , @AuthenticationPrincipal User user) throws NotFoundException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        briefReview.setTime(time);
        briefReview.setUser(user);
        briefReview.setRelaType(RelaType.getTypeId(""));
        BriefReview briefReviewReturn = briefReviewServiceImplement.add(briefReview);
        BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO(briefReviewReturn);
        return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(201, "success")));

    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addBriefReviewComment(@PathVariable Integer id, @RequestBodyDTO(CommentRequestDTO.class)  Comment comment, @AuthenticationPrincipal User user) throws NotFoundException {
        comment.setUser(user);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comment.setPubTime(time);
        comment.setUser(user);
        comment.setTargetId(id);
        comment.setTargetType(CommentTargetType.BRIEFREVIEW);

        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        Comment commentreturn = commentService.add(comment,briefReview);

        if (commentreturn != null)
            return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400, "fail")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity updateBriefReviewUpvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
     BriefReview briefReview = new BriefReview();
     briefReview.setId(id);
     evaluateService.upvote(briefReview,user.getId());

            return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity updateBriefReviewDownvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        evaluateService.downvote(briefReview,user.getId());

        return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}/vote")
    public ResponseEntity cancelBriefReviewvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        evaluateService.cancel(briefReview,user.getId());

        return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }

    @GetMapping("/{id}/comments")
    @ResponseBodyDTO(CommentResponseDTO.class)
    public ResponseEntity getBriefReviewComments(@PathVariable Integer id, @AuthenticationPrincipal User user, PageRequest pageRequest) throws NotFoundException {
       BriefReview briefReview = new BriefReview();
       briefReview.setId(id);
       briefReview.setUser(user);
       List<Comment> comments =  commentService.getComments(briefReview);
        ListResponse listResponse = new ListResponse(pageRequest, comments);
        return ResponseEntity.ok(listResponse);
    }



}
