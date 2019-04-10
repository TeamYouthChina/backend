package com.youthchina.controller.tianjian;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.briefreview.BriefReviewRequestDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AttentionServiceImpl;
import com.youthchina.service.jinhao.BriefReviewServiceImplement;
import com.youthchina.service.jinhao.CommentServiceImpl;
import com.youthchina.service.jinhao.EvaluateServiceImpl;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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
        briefReviewResponseDTO.setAttentionCount(attentionService.countAttention(briefReview));
        briefReviewResponseDTO.setEvaluateStatus(evaluateService.evaluateStatus(briefReview,user.getId()));
        briefReviewResponseDTO.setUpvoteCount(evaluateService.countUpvote(briefReview));
        briefReviewResponseDTO.setDownvoteCount(evaluateService.countDownvote(briefReview));
        briefReviewResponseDTO.setAttention((attentionService.isEverAttention(briefReview,user.getId()))==0? false:true);
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

        briefReviewResponseDTO.setId(briefReviewReturn.getId());
        return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(200, "success")));

    }

    @PostMapping
    public ResponseEntity addBriefReview(@RequestBodyDTO(BriefReviewRequestDTO.class)  BriefReview briefReview , @AuthenticationPrincipal User user) throws NotFoundException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        briefReview.setTime(time);
        briefReview.setUser(user);
        briefReview.setRelaType(0);
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
        comment.setTargetType(2);

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

    @PutMapping("/{id}/cancel")
    public ResponseEntity cancelBriefReviewvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        evaluateService.cancel(briefReview,user.getId());

        return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getBriefReviewComments(@PathVariable Integer id, @AuthenticationPrincipal User user, PageRequest pageRequest) throws NotFoundException {
       BriefReview briefReview = new BriefReview();
       briefReview.setId(id);
       briefReview.setUser(user);
       List<Comment> comments =  commentService.getComments(briefReview);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        if (comments!= null) {
            Iterator it = comments.iterator();
            while (it.hasNext()) {
                CommentDTO commentDTO = new CommentDTO((Comment) it.next());
                commentDTO.setUpvoteCount(evaluateService.countUpvote(briefReview));
                commentDTO.setDownvoteCount(evaluateService.countDownvote(briefReview));
                commentDTO.setEvaluateStatus(evaluateService.evaluateStatus(briefReview,user.getId()));
                commentDTOS.add(commentDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, commentDTOS.size(), commentDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity updateAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        attentionService.attention(briefReview,user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("/attentions/{id}")
    public ResponseEntity deleteAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        attentionService.cancel(briefReview,user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

}
