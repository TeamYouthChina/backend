package com.youthchina.controller.tianjian;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComEssayAttention;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AttentionServiceImpl;
import com.youthchina.service.jinhao.CommentServiceImpl;
import com.youthchina.service.jinhao.EvaluateServiceImpl;
import com.youthchina.service.qingyang.CompanyCURDServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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
    public ResponseEntity getEssay(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        if (comEssay == null) {
            throw new NotFoundException(4040, 404, "没有找到这个文章");
        }
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        essayResponseDTO.setAttentionCount(attentionService.countAttention(comEssay));
        essayResponseDTO.setEvaluateStatus(evaluateService.evaluateStatus(comEssay,user.getId()));
        essayResponseDTO.setUpvoteCount(evaluateService.countUpvote(comEssay));
        essayResponseDTO.setDownvoteCount(evaluateService.countDownvote(comEssay));
        essayResponseDTO.setAttention((attentionService.isEverAttention(comEssay,user.getId()))==0? false:true);
        if (comEssay.getRelaType()==1){
            essayResponseDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(comEssay.getRelaId())));
        }
            return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));

    }

    @PutMapping("/{id}")
    public ResponseEntity updateEssay(@PathVariable Integer id, @RequestBody EssayRequestDTO essayRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayRequestDTO);
        comEssay.setId(id);
        comEssay.setUser(user);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEditTime(time);
        if (essayRequestDTO.getCompany_id() != null) {
         comEssay.setRelaType(1);
         comEssay.setRelaId(essayRequestDTO.getCompany_id());
        }
        essayServiceimpl.updateEssay(comEssay);

        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        essayResponseDTO.setModified_at(time);
        if (essayRequestDTO.getCompany_id() != null)
            essayResponseDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(essayRequestDTO.getCompany_id())));

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
    public ResponseEntity addEssay(@RequestBodyDTO(EssayRequestDTO.class)  ComEssay comEssay , @AuthenticationPrincipal User user) throws NotFoundException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setPubTime(time);
        comEssay.setEditTime(time);
        comEssay.setUser(user);
        essayServiceimpl.addEssay(comEssay);
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);

        if (comEssay.getRelaId() != null&&comEssay.getRelaType()==1) {
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(companyCURDService.get(comEssay.getRelaId()));
            essayResponseDTO.setCompany(companyResponseDTO);
        }

        return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200, "success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addEssayComments(@PathVariable Integer id, @RequestBodyDTO(CommentRequestDTO.class)  Comment comment, @AuthenticationPrincipal User user) {
        comment.setTargetId(id);
        comment.setTargetType(1);
        comment.setUser(user);
        commentService.add(comment);
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));

    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getEssayComments(@PathVariable Integer id, PageRequest pageRequest,@AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay();
        comEssay.setId(id);
        List<Comment> comments = commentService.getComments(comEssay);

        List<CommentDTO> commentDTOS = new ArrayList<>();
        if(comments!=null) {
            Iterator it = comments.iterator();
            while (it.hasNext()) {
                CommentDTO commentDTO = new CommentDTO((Comment) it.next());
                commentDTO.setUpvoteCount(evaluateService.countUpvote(comEssay));
                commentDTO.setDownvoteCount(evaluateService.countDownvote(comEssay));
                commentDTO.setEvaluateStatus(evaluateService.evaluateStatus(comEssay,user.getId()));
                commentDTOS.add(commentDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, commentDTOS.size(), commentDTOS);
        return ResponseEntity.ok(new Response(listResponse, new StatusDTO(200, "success")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.get(id);
        evaluateService.upvote(comEssay, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.get(id);
        evaluateService.downvote(comEssay, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelVote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.get(id);
        evaluateService.cancel(comEssay, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }
}
