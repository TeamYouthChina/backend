package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.AnswerComment;
import com.youthchina.domain.jinhao.communityQA.AnswerEvaluate;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.SimpleAnswerDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CommunityQAServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${web.url.prefix}/answers/**")
public class AnswerController {
    @Autowired
    private CommunityQAServiceImplement communityQAServiceImplement;

    @GetMapping("/{id}")
    public ResponseEntity getAnswer(@PathVariable Integer id) throws NotFoundException {
        QuestionAnswer questionAnswer = communityQAServiceImplement.getAnswer(id);
        SimpleAnswerDTO simpleAnswerDTO = new SimpleAnswerDTO(questionAnswer);
         if (questionAnswer!=null)
            return ResponseEntity.ok(new Response(simpleAnswerDTO, new StatusDTO(200,"success")));
         else
             return ResponseEntity.ok(new Response(simpleAnswerDTO, new StatusDTO(400,"fail")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAnswer(@PathVariable Integer id,@RequestBody SimpleAnswerDTO simpleAnswerDTO,@AuthenticationPrincipal User user) throws NotFoundException {
           QuestionAnswer questionAnswer = new QuestionAnswer(simpleAnswerDTO);
           questionAnswer.setAnswer_id(id);
           questionAnswer.setUser_id(user.getId());
           SimpleAnswerDTO returnSimpleAnswer = new SimpleAnswerDTO(communityQAServiceImplement.editAnswer(questionAnswer));
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
    public ResponseEntity addAnswerComment(@PathVariable Integer id,@RequestBody SimpleAnswerDTO simpleAnswerDTO,@AuthenticationPrincipal User user) throws NotFoundException {
        AnswerComment answerComment = new AnswerComment();
        answerComment.setComment_content(simpleAnswerDTO.getBody());
        answerComment.setIs_delete(0);
        if(simpleAnswerDTO.getIsAnonymous()==true)
           answerComment.setUser_anony(0);
        else
           answerComment.setUser_anony(1);
        Integer i = communityQAServiceImplement.addCommentToAnswer(id,answerComment,1);
        if(i==1)
            return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
        else
            return  ResponseEntity.ok(new Response( new StatusDTO(400,"fail")));
    }

    @PostMapping("/{id}/upvote")
    public ResponseEntity addUpvote(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        AnswerEvaluate answerEvaluate = new AnswerEvaluate();
        answerEvaluate.setEvaluate_type(1);
        answerEvaluate.setUser_id(user.getId());
        Integer i = communityQAServiceImplement.evaluateAnswer(id,answerEvaluate);
        if(i==1)
            return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
        else
            return ResponseEntity.ok(new Response( new StatusDTO(400,"fail")));
    }

}
