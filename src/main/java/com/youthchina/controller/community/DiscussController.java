package com.youthchina.controller.community;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.DiscussService;
import com.youthchina.service.community.EvaluateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("${web.url.prefix}/replies")
public class DiscussController {

    @Resource
    DiscussService discussService;

    @Resource
    EvaluateService evaluateService;

    @GetMapping("/{id}")
    public ResponseEntity getDiscuss(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(new Response(this.discussService.get(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDiscuss(@PathVariable Integer id) throws NotFoundException {
        discussService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

   /* @GetMapping("/{id}/count")
    public ResponseEntity<?> count(@PathVariable Integer id) {
        int count = discussService.count(id);
        return ResponseEntity.ok(new Response(count));
    }*/

    @PutMapping("/{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Discuss discuss = discussService.get(id);
        evaluateService.upvote(discuss, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Discuss discuss = discussService.get(id);
        evaluateService.downvote(discuss, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @DeleteMapping({"/{id}/vote"})
    public ResponseEntity<?> cancelVote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Discuss discuss = discussService.get(id);
        evaluateService.cancel(discuss, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }
}
