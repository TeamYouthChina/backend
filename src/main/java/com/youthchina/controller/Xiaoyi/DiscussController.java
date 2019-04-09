package com.youthchina.controller.Xiaoyi;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.NotFoundException;;
import com.youthchina.service.jinhao.DiscussService;
import com.youthchina.service.jinhao.EvaluateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("${web.url.prefix}/comments")
public class DiscussController {
    @Resource
    DiscussService discussService;

    @Resource
    EvaluateService evaluateService;

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> addDiscuss(@RequestBody DiscussDTO discussDTO) throws NotFoundException{
        Discuss discuss = new Discuss(discussDTO);
        Discuss retrunDiscuss = discussService.add(discuss);
        DiscussDTO returndiscussDTO = new DiscussDTO(retrunDiscuss);
        if(returndiscussDTO.getid() != null){
            return ResponseEntity.ok(new Response(returndiscussDTO));
        }else{
            return ResponseEntity.ok(new Response(new StatusDTO(403,"add failed")));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) throws NotFoundException {
        discussService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @GetMapping("/{id}/exist")
    public ResponseEntity<?> isDiscussExist(@PathVariable Integer id) throws NotFoundException {
        discussService.isDiscussExist(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @GetMapping("/{id}/count")
    public ResponseEntity<?> count(@PathVariable Integer id) {
        int count = discussService.count(id);
        return ResponseEntity.ok(new Response(count));
    }

    @GetMapping("/{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws  NotFoundException{
        Discuss discuss = discussService.get(id);
        evaluateService.upvote(discuss, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @GetMapping("/{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws  NotFoundException{
        Discuss discuss = discussService.get(id);
        evaluateService.downvote(discuss, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

}