package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AnswerServiceImpl;
import com.youthchina.service.jinhao.CommentServiceImpl;
import com.youthchina.service.jinhao.EvaluateServiceImpl;
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
    private CommentServiceImpl commentService;
    private EvaluateServiceImpl evaluateService;

    @GetMapping("/{id}")
    public ResponseEntity getAnswer(@PathVariable Integer id) throws NotFoundException {
        Answer answer = answerService.get(id);

        SimpleAnswerResponseDTO simpleAnswerResponseDTO = new SimpleAnswerResponseDTO(answer);

        if (answer !=null)
            return ResponseEntity.ok(new Response(simpleAnswerResponseDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(simpleAnswerResponseDTO, new StatusDTO(400,"fail")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAnswer(@PathVariable Integer id, @RequestBody SimpleAnswerRequestDTO simpleAnswerRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer = new Answer(simpleAnswerRequestDTO);
        answer.setId(id);
        answer.setUser(user);
        Answer answer1 = answerService.update(answer);
        SimpleAnswerResponseDTO returnSimpleAnswer = new SimpleAnswerResponseDTO(answer1);
        if (returnSimpleAnswer!=null)
            return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(400,"fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Integer id) throws NotFoundException {
        answerService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addAnswerComment(@PathVariable Integer id, @RequestBody CommentRequestDTO commentRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = new Comment();
        comment.setContent(commentRequestDTO.getBody().toString());
        comment.setUser(user);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comment.setPubTime(time);
        if(commentRequestDTO.getIs_anonymous()==true)
            comment.setIsAnony(0);
        else
            comment.setIsAnony(1);
        Answer answer = answerService.get(id);
        commentService.add(comment,answer);
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getAnswerComments(@PathVariable Integer id) throws NotFoundException {
        Answer answer = answerService.get(id);
        List<Comment> comments = commentService.getComments(answer);

        List<CommentDTO> commentDTOS = new ArrayList<>();
        if(comments!=null){
            Iterator it = comments.iterator();
            while(it.hasNext()){
                CommentDTO commentDTO = new CommentDTO((Comment)it.next());
                commentDTOS.add(commentDTO);
            }
        }
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setComments(commentDTOS);

        return ResponseEntity.ok(new Response(commentResponseDTO, new StatusDTO(200,"success")));
    }

    @PutMapping ("/{id}/upvote")
    public ResponseEntity addUpvote(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer =answerService.get(id);
        evaluateService.upvote(answer, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
    }

}
