package com.youthchina.controller.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.Evaluate;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.briefreview.BriefReviewDTO;
import com.youthchina.dto.community.briefreview.RequestBriefReviewDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.RequestCommentDTO;
import com.youthchina.dto.community.comment.ResponseCommentDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTO;
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
    public ResponseEntity getBriefReview(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = briefReviewServiceImplement.get(id);

        BriefReviewDTO briefReviewDTO = new BriefReviewDTO(briefReview);
        briefReviewDTO.setAuthor(new UserDTO(userService.get(user.getId())));
        if (briefReviewDTO != null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400, "fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBriefReview(@PathVariable Integer id) throws NotFoundException {
        briefReviewServiceImplement.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBriefReview(@PathVariable Integer id, @RequestBody RequestBriefReviewDTO requestBriefReviewDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setReview_id(id);
        briefReview.setIs_delete(0);
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(requestBriefReviewDTO.getBody());
            briefReview.setReview_content(requestJson);
        } catch (Exception e) {
            System.out.println("Exception");
        }

        Timestamp time = new Timestamp(System.currentTimeMillis());
        briefReview.setReview_time(time);
        briefReview.setUser(user);
        if (requestBriefReviewDTO.getCompany_id() != null) {
            briefReview.setRela_type(2);
            briefReview.setRela_id(requestBriefReviewDTO.getCompany_id());
        }
        BriefReview briefReviewReturn = briefReviewServiceImplement.update(briefReview);
        BriefReviewDTO briefReviewDTO = new BriefReviewDTO();

        try {
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(briefReviewReturn.getReview_content(), RichTextDTO.class);
            briefReviewDTO.setBody(richt);
        } catch (Exception e) {
            System.out.println("Exception");
        }

        briefReviewDTO.setAuthor(new UserDTO(userService.get(user.getId())));
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        Iterator<CommentDTO> it = commentDTOList.iterator();
        while (it.hasNext()) {
            CommentDTO commentDTOTest = it.next();
            commentDTOList.add(commentDTOTest);
        }
        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();
        responseCommentDTO.setComments(commentDTOList);
        briefReviewDTO.setComments(responseCommentDTO);
        briefReviewDTO.setId(briefReviewReturn.getReview_id());
        if (briefReviewDTO != null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400, "fail")));
    }

    @PostMapping
    public ResponseEntity addBriefReview(@RequestBody RequestBriefReviewDTO requestBriefReviewDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setIs_delete(0);
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(requestBriefReviewDTO.getBody());
            briefReview.setReview_content(requestJson);
        } catch (Exception e) {
            System.out.println("Exception");
        }

        Timestamp time = new Timestamp(System.currentTimeMillis());
        briefReview.setReview_time(time);
        briefReview.setUser(user);
        if (requestBriefReviewDTO.getCompany_id() != null) {
            briefReview.setRela_type(2);
            briefReview.setRela_id(requestBriefReviewDTO.getCompany_id());
        }
        BriefReview briefReviewReturn = briefReviewServiceImplement.add(briefReview);
        BriefReviewDTO briefReviewDTO = new BriefReviewDTO();

        try {
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(briefReviewReturn.getReview_content(), RichTextDTO.class);
            briefReviewDTO.setBody(richt);
        } catch (Exception e) {
            System.out.println("Exception");
        }

        briefReviewDTO.setAuthor(new UserDTO(userService.get(user.getId())));
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        Iterator<CommentDTO> it = commentDTOList.iterator();
        while (it.hasNext()) {
            CommentDTO commentDTOTest = it.next();
            commentDTOList.add(commentDTOTest);
        }
        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();
        responseCommentDTO.setComments(commentDTOList);
        briefReviewDTO.setComments(responseCommentDTO);
        briefReviewDTO.setId(briefReviewReturn.getReview_id());
        if (briefReviewDTO != null)
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(201, "success")));
        else
            return ResponseEntity.ok(new Response(briefReviewDTO, new StatusDTO(400, "fail")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addBriefReviewComment(@PathVariable Integer id, @RequestBody RequestCommentDTO requestCommentDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = new Comment();
        comment.setUser(user);
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(requestCommentDTO.getBody());
            comment.setComment_content(requestJson);
        } catch (Exception e) {
            System.out.println("Exception");
        }

        comment.setIs_delete(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comment.setComment_pub_time(time);
        comment.setUser_id(user.getId());
        comment.setUser_anony((requestCommentDTO.getIs_anonymous() == true) ? 1 : 0);
        Comment commentreturn = briefReviewServiceImplement.addComment(comment, id);
        if (commentreturn != null)
            return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400, "fail")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity updateBriefReviewUpvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Evaluate evaluate = briefReviewServiceImplement.doEvaluate(user.getId(), id, 1);
        if (evaluate != null)
            return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400, "fail")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getBriefReviewComments(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        List<Comment> comments = briefReviewServiceImplement.getAllCommentsOfReview(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        if (comments != null) {
            Iterator it = comments.iterator();
            while (it.hasNext()) {
                CommentDTO commentDTO = new CommentDTO((Comment) it.next());
                commentDTOS.add(commentDTO);
            }
        }
        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();
        responseCommentDTO.setComments(commentDTOS);
        if (responseCommentDTO != null)
            return ResponseEntity.ok(new Response(responseCommentDTO, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(responseCommentDTO, new StatusDTO(400, "fail")));
    }

}
