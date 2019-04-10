package com.youthchina.controller.Xiaoyi;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.DiscussService;
import com.youthchina.service.jinhao.EvaluateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

;

@RestController
@RequestMapping("${web.url.prefix}/replies")
public class DiscussController {

    @Resource
    DiscussService discussService;

    @Resource
    EvaluateService evaluateService;

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) throws NotFoundException {
        discussService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @GetMapping("/{id}/count")
    public ResponseEntity<?> count(@PathVariable Integer id) {
        int count = discussService.count(id);
        return ResponseEntity.ok(new Response(count));
    }

    @GetMapping("/{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Discuss discuss = discussService.get(id);
        evaluateService.upvote(discuss, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @GetMapping("/{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Discuss discuss = discussService.get(id);
        evaluateService.downvote(discuss, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(2040, "success")));
    }

    @DeleteMapping({"/{id}/upvote", "/{id}/downvote"})
    public ResponseEntity<?> cancelVote(@PathVariable Integer id) throws NotFoundException {
        Discuss discuss = discussService.get(id);
        evaluateService.cancel(discuss, id);
        return ResponseEntity.ok(new Response(new StatusDTO(2040, "success")));
    }

    @GetMapping("/{id}")
    public ResponseEntity getDiscuss(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(new Response(this.discussService.get(id)));
    }
}
