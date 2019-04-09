package com.youthchina.controller.Xiaoyi;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.discuss.DiscussDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.NotFoundException;;
import com.youthchina.service.jinhao.DiscussService;
import com.youthchina.service.jinhao.EvaluateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/discusses")
public class DiscussController {
    @Resource
    DiscussService discussService;

    @Resource
    EvaluateService evaluateService;

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

    @GetMapping("/{id}")
    public ResponseEntity getDiscuss(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException  {
        List<Discuss> discusses = discussService.getDiscusses(id,pageRequest.getStart(),pageRequest.getEnd());
        List<DiscussDTO> discussDTOS = new ArrayList<>();
        if (discusses != null) {
            for (Discuss discuss : discusses) {
                DiscussDTO discussDTO = new DiscussDTO(discuss);
                discussDTOS.add(discussDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, discussDTOS.size(),discussDTOS);
        return ResponseEntity.ok(listResponse);
    }
}
