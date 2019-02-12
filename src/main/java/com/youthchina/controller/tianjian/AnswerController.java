package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.SimpleAnswerDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CommunityQAServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${web.url.prefix}/answer/**")
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Integer id) throws NotFoundException {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setAnswer_id(id);
         communityQAServiceImplement.deleteAnswer(questionAnswer);
        SimpleAnswerDTO simpleAnswerDTO = new SimpleAnswerDTO(questionAnswer);
        if (questionAnswer!=null)
            return ResponseEntity.ok(new Response(simpleAnswerDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(simpleAnswerDTO, new StatusDTO(400,"fail")));
    }
}
