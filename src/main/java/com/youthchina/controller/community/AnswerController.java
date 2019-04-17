package com.youthchina.controller.community;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.AnswerServiceImpl;
import com.youthchina.service.community.AttentionServiceImpl;
import com.youthchina.service.community.CommentServiceImpl;
import com.youthchina.service.community.EvaluateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/answers")
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private EvaluateServiceImpl evaluateService;
    @Autowired
    private AttentionServiceImpl attentionService;

    @GetMapping("/{id}")
    public ResponseEntity getAnswer(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException{
        Answer answer = answerService.get(id);
        SimpleAnswerResponseDTO simpleAnswerResponseDTO = new SimpleAnswerResponseDTO(answer);
        simpleAnswerResponseDTO.setAttentionCount(attentionService.countAttention(answer));
        simpleAnswerResponseDTO.setEvaluateStatus(evaluateService.evaluateStatus(answer,user.getId()));
        simpleAnswerResponseDTO.setUpvoteCount(evaluateService.countUpvote(answer));
        simpleAnswerResponseDTO.setDownvoteCount(evaluateService.countDownvote(answer));
        simpleAnswerResponseDTO.setAttention((attentionService.isEverAttention(answer, user.getId())) != 0);
        return ResponseEntity.ok(new Response(simpleAnswerResponseDTO, new StatusDTO(200,"success")));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateAnswer(@PathVariable Integer id, @RequestBodyDTO(SimpleAnswerRequestDTO.class) Answer answer, @AuthenticationPrincipal User user) throws NotFoundException {
           answer.setId(id);
           answer.setUser(user);
           Answer answer1 = answerService.update(answer);
           SimpleAnswerResponseDTO returnSimpleAnswer = new SimpleAnswerResponseDTO(answer1);
           return ResponseEntity.ok(new Response(returnSimpleAnswer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Integer id) throws NotFoundException {
            answerService.delete(id);
            return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addAnswerComment(@PathVariable Integer id, @RequestBodyDTO(CommentRequestDTO.class) Comment comment, @AuthenticationPrincipal User user) throws NotFoundException {
        comment.setUser(user);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comment.setPubTime(time);
        Answer answer = new Answer();
        answer.setId(id);
        commentService.add(comment,answer);
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getAnswerComments(@PathVariable Integer id, PageRequest pageRequest,@AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer = answerService.get(id);
        List<Comment> comments = commentService.getComments(answer);
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        if(comments!=null) {
            Iterator it = comments.iterator();
            while (it.hasNext()) {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO((Comment) it.next());
                commentResponseDTO.setUpvoteCount(evaluateService.countUpvote(answer));
                commentResponseDTO.setDownvoteCount(evaluateService.countDownvote(answer));
                commentResponseDTO.setEvaluateStatus(evaluateService.evaluateStatus(answer,user.getId()));
                commentResponseDTOS.add(commentResponseDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, commentResponseDTOS.size(), commentResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @PutMapping ("/{id}/upvote")
    public ResponseEntity addUpvote(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer =answerService.get(id);
        evaluateService.upvote(answer, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
    }

    @PutMapping ("/{id}/downvote")
    public ResponseEntity addDownvote(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer =answerService.get(id);
        evaluateService.downvote(answer, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
    }

    @DeleteMapping("/{id}/vote")
    public ResponseEntity<?> cancelVote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer =answerService.get(id);
        evaluateService.cancel(answer, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

}
