package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.Evaluate;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;

import com.youthchina.dto.community.BriefReviewDTO;
import com.youthchina.dto.community.CommentDTO;
import com.youthchina.dto.community.RequestBriefReviewDTO;
import com.youthchina.dto.community.RequestCommentDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.BriefReviewServiceImplement;
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
@RequestMapping("${web.url.prefix}/editorials")
public class BriefReviewController {

    @Autowired
    BriefReviewServiceImplement briefReviewServiceImplement;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity getBriefReview(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = briefReviewServiceImplement.get(id);

        BriefReviewDTO briefReviewDTO = new BriefReviewDTO(briefReview);
        briefReviewDTO.setAuthor(userService.get(user.getId()));
        if (briefReviewDTO!=null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400,"fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBriefReview(@PathVariable Integer id) throws NotFoundException {
        briefReviewServiceImplement.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBriefReview(@PathVariable Integer id,@RequestBody RequestBriefReviewDTO requestBriefReviewDTO,@AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setReview_id(id);
        briefReview.setIs_delete(0);
        briefReview.setReview_content(requestBriefReviewDTO.getBody());
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        briefReview.setReview_time(time);
        briefReview.setUser(user);
        if(requestBriefReviewDTO.getCompany_id()!=null) {
            briefReview.setRela_type(2);
            briefReview.setRela_id(requestBriefReviewDTO.getCompany_id());
        }
        BriefReview briefReviewReturn =  briefReviewServiceImplement.update(briefReview);
        BriefReviewDTO briefReviewDTO = new BriefReviewDTO();
        briefReviewDTO.setBody(briefReviewReturn.getReview_content());
        briefReviewDTO.setAuthor(userService.get(user.getId()));
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        Iterator<CommentDTO> it = commentDTOList.iterator();
        while(it.hasNext()){
            CommentDTO commentDTOTest = it.next();
            commentDTOList.add(commentDTOTest);
        }
        briefReviewDTO.setComments(commentDTOList);
        briefReviewDTO.setId(briefReviewReturn.getReview_id());
        if (briefReviewDTO!=null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400,"fail")));
    }

    @PostMapping
    public ResponseEntity addBriefReview(@RequestBody RequestBriefReviewDTO requestBriefReviewDTO,@AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setIs_delete(0);
        briefReview.setReview_content(requestBriefReviewDTO.getBody());
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        briefReview.setReview_time(time);
        briefReview.setUser(user);
        if(requestBriefReviewDTO.getCompany_id()!=null) {
            briefReview.setRela_type(2);
            briefReview.setRela_id(requestBriefReviewDTO.getCompany_id());
        }
        BriefReview briefReviewReturn =  briefReviewServiceImplement.add(briefReview);
        BriefReviewDTO briefReviewDTO = new BriefReviewDTO();
        briefReviewDTO.setBody(briefReviewReturn.getReview_content());
        briefReviewDTO.setAuthor(userService.get(user.getId()));
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        Iterator<CommentDTO> it = commentDTOList.iterator();
        while(it.hasNext()){
            CommentDTO commentDTOTest = it.next();
            commentDTOList.add(commentDTOTest);
        }
        briefReviewDTO.setComments(commentDTOList);
        briefReviewDTO.setId(briefReviewReturn.getReview_id());
        if (briefReviewDTO!=null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(201,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400,"fail")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addBriefReviewComment(@PathVariable Integer id, @RequestBody RequestCommentDTO requestCommentDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setComment_content(requestCommentDTO.getBody());
        comment.setIs_delete(0);
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        comment.setComment_pub_time(time);
        comment.setUser_id(user.getId());
        comment.setUser_anony((requestCommentDTO.getIs_anonymous()==true)? 1:0);
        Comment commentreturn = briefReviewServiceImplement.addComment(comment,id);
        if ( commentreturn!=null)
            return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400,"fail")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity updateBriefReviewUpvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Evaluate evaluate = briefReviewServiceImplement.doEvaluate(user.getId(),id,1);
        if ( evaluate!=null)
            return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400,"fail")));
    }

}
