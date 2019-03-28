package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.Comment;

import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComEssayAttention;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.article.EssayRequestDTO;

import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.security.UserDTO;

import com.youthchina.exception.zhongyang.NotFoundException;

import com.youthchina.service.jinhao.AttentionServiceImpl;
import com.youthchina.service.jinhao.CommentServiceImpl;
import com.youthchina.service.qingyang.CompanyCURDServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.Iterator;


@RestController
@RequestMapping("${web.url.prefix}/articles")
public class EssayController {

    @Autowired
    EssayServiceImpl essayServiceimpl;

    @Autowired
    CompanyCURDServiceImpl companyCURDService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AttentionServiceImpl attentionService;

    @Autowired
    CommentServiceImpl commentService;

    @GetMapping("/{id}")
    public ResponseEntity getEssay(@PathVariable Integer id) throws NotFoundException {
        System.out.println(id);
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        if (comEssay == null) {
            throw new NotFoundException(404, 404, "没有找到这个文章"); //TODO
        }
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        if (comEssay.getRelaType()!=1){
            essayResponseDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(comEssay.getRelaId())));
        }
            return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));

    }

    @PutMapping("/{id}")
    public ResponseEntity updateEssay(@PathVariable Integer id, @RequestBody EssayRequestDTO essayRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayRequestDTO);
        comEssay.setId(id);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEditTime(time);
        if (essayRequestDTO.getCompany_id() != 1) {
         comEssay.setRelaType(2);
         comEssay.setRelaId(essayRequestDTO.getCompany_id());
        }
        essayServiceimpl.updateEssay(comEssay);

        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        essayResponseDTO.setModified_at(time);
        if (essayRequestDTO.getCompany_id() != 1)
            essayResponseDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(essayRequestDTO.getCompany_id())));
        essayResponseDTO.setAuthor(new UserDTO(user));

        return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEssay(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int i = essayServiceimpl.deleteEssay(id, time);
        if (i != 0)
            return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(403, "fail")));
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity updateAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay();
        comEssay.setId(id);
        attentionService.attention(comEssay,user.getId());
        ComEssayAttention comEssayAttention = new ComEssayAttention();
        comEssayAttention.setAtten_cancel(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssayAttention.setAtten_time(time);
        comEssayAttention.setUser_id(user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("/attentions/{id}")
    public ResponseEntity deleteAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
       ComEssay comEssay = new ComEssay();
       comEssay.setId(id);
        attentionService.cancel(comEssay,user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PostMapping
    public ResponseEntity addEssay(@RequestBody EssayRequestDTO essayRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayRequestDTO);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setPubTime(time);
        comEssay.setEditTime(time);
        comEssay.setRelaType(1);
        if (essayRequestDTO.getCompany_id() != null) {
            comEssay.setRelaType(2);
            comEssay.setRelaId(essayRequestDTO.getCompany_id());
        }
        essayServiceimpl.addEssay(comEssay);
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);

        if (essayRequestDTO.getCompany_id() != null) {
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(companyCURDService.get(essayRequestDTO.getCompany_id()));
            essayResponseDTO.setCompany(companyResponseDTO);
        }
        User useressay = userService.get(user.getId());
        essayResponseDTO.setAuthor(new UserDTO(useressay));

        return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addEssayComments(@PathVariable Integer id, @RequestBody CommentRequestDTO commentRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = new Comment(commentRequestDTO);
        comment.setTargetId(id);
        comment.setTargetType(1);
        comment.setUser(user);
        commentService.add(comment);
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));

    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getEssayComments(@PathVariable Integer id) {
        ComEssay comEssay = new ComEssay();
        comEssay.setCommentTargetType(1);
        comEssay.setId(id);
        commentService.getComments(comEssay);
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        if (comEssay.getComments() != null) {
            Iterator it = comEssay.getComments().iterator();
            while (it.hasNext()) {
                commentResponseDTO.getComments().add(new CommentDTO((Comment) it.next()));
            }
        }

        return ResponseEntity.ok(new Response(commentResponseDTO, new StatusDTO(200, "success")));

    }
}
