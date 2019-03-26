package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.RequestSimpleAnswerDTO;
import com.youthchina.dto.community.answer.SimpleAnswerDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.RequestCommentDTO;
import com.youthchina.dto.community.comment.ResponseCommentDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
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
    private CommunityQAServiceImplement communityQAServiceImplement;

    @GetMapping("/{id}")
    public ResponseEntity getAnswer(@PathVariable Integer id) throws NotFoundException {
        Answer answer = communityQAServiceImplement.getAnswer(id);

        SimpleAnswerDTO simpleAnswerDTO = new SimpleAnswerDTO(answer);

         if (answer !=null)
            return ResponseEntity.ok(new Response(simpleAnswerDTO, new StatusDTO(200,"success")));
         else
             return ResponseEntity.ok(new Response(simpleAnswerDTO, new StatusDTO(400,"fail")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAnswer(@PathVariable Integer id, @RequestBody RequestSimpleAnswerDTO requestSimpleAnswerDTO, @AuthenticationPrincipal User user) throws NotFoundException {
           Answer answer = new Answer(requestSimpleAnswerDTO);
           answer.setAnswer_id(id);
           answer.setUser_id(user.getId());
           Answer answer1 = communityQAServiceImplement.editAnswer(answer);
           SimpleAnswerDTO returnSimpleAnswer = new SimpleAnswerDTO(answer1);
          if (returnSimpleAnswer!=null)
           return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(200,"success")));
          else
              return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(400,"fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Integer id) throws NotFoundException {
            communityQAServiceImplement.deleteAnswer(id);
            return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addAnswerComment(@PathVariable Integer id, @RequestBody RequestCommentDTO requestCommentDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = new Comment();
        comment.setComment_content(requestCommentDTO.getBody().toString());
        comment.setIs_delete(0);
        comment.setUser_id(user.getId());
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comment.setComment_pub_time(time);
        if(requestCommentDTO.getIs_anonymous()==true)
           comment.setUser_anony(0);
        else
           comment.setUser_anony(1);
        communityQAServiceImplement.addCommentToAnswer(id, comment,1);
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getAnswerComments(@PathVariable Integer id) throws NotFoundException {
        List<Comment> comments = communityQAServiceImplement.getAllAnswerComments(id);

        List<CommentDTO> commentDTOS = new ArrayList<>();
        if(comments!=null){
            Iterator it = comments.iterator();
            while(it.hasNext()){
                CommentDTO commentDTO = new CommentDTO((Comment)it.next());
                commentDTOS.add(commentDTO);
            }
        }
        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();
        responseCommentDTO.setComments(commentDTOS);

            return ResponseEntity.ok(new Response(responseCommentDTO, new StatusDTO(200,"success")));
    }

    @PutMapping ("/{id}/upvote")
    public ResponseEntity addUpvote(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        communityQAServiceImplement.evaluateAnswer(id, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
    }

}
