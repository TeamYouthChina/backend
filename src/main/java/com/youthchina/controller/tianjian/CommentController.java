package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.jinhao.Evaluate;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.CommentService;
import com.youthchina.service.jinhao.DiscussService;
import com.youthchina.service.jinhao.EvaluateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("${web.url.prefix}/comments")
public class CommentController {
    @Resource
    CommentService commentService;

    @Resource
    EvaluateService evaluateService;

    @Resource
    DiscussService discussService;



    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) throws NotFoundException {
        commentService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @GetMapping("/{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws  NotFoundException{
        Comment comment = commentService.get(id);
        evaluateService.upvote(comment, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @GetMapping("/{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws  NotFoundException{
        Comment comment = commentService.get(id);
        evaluateService.downvote(comment, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }


}
