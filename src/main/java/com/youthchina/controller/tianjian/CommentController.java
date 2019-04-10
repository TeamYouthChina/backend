package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.discuss.DiscussDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.CommentService;
import com.youthchina.service.jinhao.DiscussService;
import com.youthchina.service.jinhao.EvaluateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/comments")
public class CommentController {
    @Resource
    CommentService commentService;

    @Resource
    EvaluateService evaluateService;

    @Resource
    DiscussService discussService;

    @PostMapping("/{id}/replies")
    public ResponseEntity<?> addDiscuss(@PathVariable Integer id, @RequestBody DiscussDTO discussDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Discuss discuss = new Discuss(discussDTO);
        discuss.setCommentId(id);
        discuss.setUser(user);
        Discuss retrunDiscuss = discussService.add(discuss);
        DiscussDTO returndiscussDTO = new DiscussDTO(retrunDiscuss);
        if (returndiscussDTO.getId() != null) {
            return ResponseEntity.ok(new Response(returndiscussDTO));
        } else {
            return ResponseEntity.ok(new Response(new StatusDTO(403, "add failed")));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) throws NotFoundException {
        commentService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = commentService.get(id);
        evaluateService.upvote(comment, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = commentService.get(id);
        evaluateService.downvote(comment, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @GetMapping("/{id}/replies")
    public ResponseEntity<?> getAllReplies(@PathVariable Integer id, PageRequest pageRequest) {
        List<Discuss> discusses = discussService.getDiscussesByCommentId(id, pageRequest.getStart(), pageRequest.getEnd());
        List<DiscussDTO> discussDTOS = new ArrayList<>();
        if (discusses != null) {
            for (Discuss discuss : discusses) {
                DiscussDTO discussDTO = new DiscussDTO(discuss);
                discussDTOS.add(discussDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, discussDTOS.size(), discussDTOS);
        return ResponseEntity.ok(listResponse);
    }

}
