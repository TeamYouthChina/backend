package com.youthchina.controller.community;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.application.CompanyCURDServiceImpl;
import com.youthchina.service.community.AttentionServiceImpl;
import com.youthchina.service.community.CommentServiceImpl;
import com.youthchina.service.community.EssayServiceImpl;
import com.youthchina.service.community.EvaluateServiceImpl;
import com.youthchina.service.user.UserServiceImpl;
import com.youthchina.util.dictionary.RelaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Timestamp;
import java.util.List;


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

    @Autowired
    EvaluateServiceImpl evaluateService;

    @GetMapping("/{id}")
    public ResponseEntity getEssay(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        if (comEssay == null) {
            throw new NotFoundException(4040, 404, "没有找到这个文章");
        }
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        if (comEssay.getRelaType() == RelaType.COMPANY) {
            try {
                essayResponseDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(comEssay.getRelaId())));
            } catch (NotFoundException e) {

            }
        }
        return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));

    }

    @PutMapping("/{id}")
    public ResponseEntity updateEssay(@PathVariable Integer id, @RequestBody EssayRequestDTO essayRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayRequestDTO);
        comEssay.setId(id);
        comEssay.setAuthor(user);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEditTime(time);
        essayServiceimpl.updateEssay(comEssay);

        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        essayResponseDTO.setModified_at(time);
        if (essayRequestDTO.getRela_id() == RelaType.COMPANY) {
            try {
                essayResponseDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(essayRequestDTO.getRela_id())));
            } catch (NotFoundException e) {

            }
        }

        return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}")
    @IsOwner
    public ResponseEntity deleteEssay(@PathVariable @P("id") Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int i = essayServiceimpl.deleteEssay(id, time);
        if (i != 0)
            return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(403, "fail")));
    }

    @PostMapping
    public ResponseEntity addEssay(@RequestBodyDTO(EssayRequestDTO.class) ComEssay comEssay, @AuthenticationPrincipal User user) throws NotFoundException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setPubTime(time);
        comEssay.setEditTime(time);
        comEssay.setAuthor(user);
        essayServiceimpl.addEssay(comEssay);
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        if (comEssay.getRelaType() == RelaType.COMPANY) {
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(companyCURDService.get(comEssay.getRelaId()));
            essayResponseDTO.setCompany(companyResponseDTO);
        }

        return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));
    }

    @PostMapping("/{id}/comments")
    @ResponseBodyDTO(CommentResponseDTO.class)
    public ResponseEntity addEssayComments(@PathVariable Integer id, @RequestBodyDTO(CommentRequestDTO.class) Comment comment, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay essay = this.essayServiceimpl.getEssay(id);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comment.setUser(user);
        comment.setPubTime(time);
        comment.setEditTime(time);
        comment = commentService.add(comment, essay);
        return ResponseEntity.ok(new Response(comment, new StatusDTO(201, "success")));

    }

    @GetMapping("/{id}/comments")
    @ResponseBodyDTO(CommentResponseDTO.class)
    public ResponseEntity getEssayComments(@PathVariable Integer id, PageRequest pageRequest, @AuthenticationPrincipal User user) {
        ComEssay comEssay = new ComEssay();
        comEssay.setId(id);
        List<Comment> comments = commentService.getComments(comEssay);
        ListResponse listResponse = new ListResponse(pageRequest, comments);
        return ResponseEntity.ok(listResponse);
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        evaluateService.upvote(comEssay, user.getId());
        return ResponseEntity.ok(new Response(null, new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        evaluateService.downvote(comEssay, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @DeleteMapping("/{id}/vote")
    public ResponseEntity<?> cancelVote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        evaluateService.cancel(comEssay, user.getId());
        return ResponseEntity.ok(new Response(null, new StatusDTO(204, "success")));
    }
}


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("isOwner(#id, T(com.youthchina.util.dictionary.HasOwnerEntityType).ARTICLE)")
@interface IsOwner {

}