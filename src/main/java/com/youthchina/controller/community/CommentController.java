package com.youthchina.controller.community;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.community.discuss.DiscussRequestDTO;
import com.youthchina.dto.community.discuss.DiscussResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.CommentService;
import com.youthchina.service.community.DiscussService;
import com.youthchina.service.community.EvaluateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
    public ResponseEntity<?> addDiscuss(@PathVariable Integer id, @RequestBody DiscussRequestDTO discussRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Discuss discuss = new Discuss(discussRequestDTO);
        discuss.setCommentId(id);
        discuss.setUser(user);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        discuss.setEditTime(time);
        discuss.setPubTime(time);
        Discuss retrunDiscuss = discussService.add(discuss);
        DiscussResponseDTO returndiscussResponseDTO = new DiscussResponseDTO(retrunDiscuss);
        if (returndiscussResponseDTO.getId() != null) {
            return ResponseEntity.ok(new Response(returndiscussResponseDTO));
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
        List<DiscussResponseDTO> discussResponseDTOS = new ArrayList<>();
        if (discusses != null) {
            for (Discuss discuss : discusses) {
                DiscussResponseDTO discussResponseDTO = new DiscussResponseDTO(discuss);
                discussResponseDTOS.add(discussResponseDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, discussResponseDTOS.size(), discussResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable Integer id, @AuthenticationPrincipal User user) throws Exception{
        Comment comment = commentService.get(id);
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment);
        commentResponseDTO.setEvaluateStatus(evaluateService.evaluateStatus(comment,user.getId()));
        commentResponseDTO.setUpvoteCount(evaluateService.countUpvote(comment));
        commentResponseDTO.setDownvoteCount(evaluateService.countDownvote(comment));

        if (commentResponseDTO != null)
            return ResponseEntity.ok(new Response(commentResponseDTO, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(commentResponseDTO, new StatusDTO(400, "fail")));
    }

    @DeleteMapping({"/{id}/vote"})
    public ResponseEntity<?> cancelVote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = commentService.get(id);
        evaluateService.cancel(comment, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }
}
