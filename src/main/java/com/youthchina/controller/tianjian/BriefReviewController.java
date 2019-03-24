package com.youthchina.controller.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Evaluate;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.briefreview.BriefReviewRequestDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.BriefReviewServiceImplement;
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
        BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO(briefReview);
        briefReviewResponseDTO.setAuthor(new UserDTO(userService.get(user.getId())));
        if (briefReviewResponseDTO !=null)
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(400,"fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBriefReview(@PathVariable Integer id) throws NotFoundException {
        briefReviewServiceImplement.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBriefReview(@PathVariable Integer id, @RequestBody BriefReviewRequestDTO briefReviewRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(briefReviewRequestDTO.getBody());
            ComRichText comRichText = new ComRichText();
            comRichText.setRelaId(id);
            comRichText.setJsonContent(requestJson);
            comRichText.setTextContent(requestJson);
            briefReview.setRichText(comRichText);
        }catch (Exception e){
            System.out.println("Exception");
        }

        briefReview.setUser(user);
        if(briefReviewRequestDTO.getCompany_id()!=null) {
            briefReview.setRelaType(2);
            briefReview.setRelaId(briefReviewRequestDTO.getCompany_id());
        }
        BriefReview briefReviewReturn =  briefReviewServiceImplement.update(briefReview);
        BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO();

        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(briefReviewReturn.getRichText().getJsonContent(), RichTextDTO.class);
            briefReviewResponseDTO.setBody(richt);
        }catch (Exception e){
            System.out.println("Exception");
        }

        briefReviewResponseDTO.setAuthor(new UserDTO(userService.get(user.getId())));
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        Iterator<CommentDTO> it = commentDTOList.iterator();
        while(it.hasNext()){
            CommentDTO commentDTOTest = it.next();
            commentDTOList.add(commentDTOTest);
        }
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setComments(commentDTOList);
        briefReviewResponseDTO.setComments(commentResponseDTO);
        briefReviewResponseDTO.setId(briefReviewReturn.getId());
        if (briefReviewResponseDTO !=null)
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(400,"fail")));
    }

    @PostMapping
    public ResponseEntity addBriefReview(@RequestBody BriefReviewRequestDTO briefReviewRequestDTO, @AuthenticationPrincipal User user) {
        BriefReview briefReview = new BriefReview();
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(briefReviewRequestDTO.getBody());
            ComRichText comRichText = new ComRichText();
            comRichText.setJsonContent(requestJson);
            comRichText.setTextContent(requestJson);
            briefReview.setRichText(comRichText);
        }catch (Exception e){
            System.out.println("Exception");
        }

        briefReview.setUser(user);
        if(briefReviewRequestDTO.getCompany_id()!=null) {
            briefReview.setRelaType(2);
            briefReview.setRelaId(briefReviewRequestDTO.getCompany_id());
        }
        BriefReview briefReviewReturn =  briefReviewServiceImplement.add(briefReview);
        BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO();

        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(briefReviewReturn.getRichText().getJsonContent(), RichTextDTO.class);
            briefReviewResponseDTO.setBody(richt);
        }catch (Exception e){
            System.out.println("Exception");
        }

        briefReviewResponseDTO.setAuthor(new UserDTO(userService.get(user.getId())));
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        Iterator<CommentDTO> it = commentDTOList.iterator();
        while(it.hasNext()){
            CommentDTO commentDTOTest = it.next();
            commentDTOList.add(commentDTOTest);
        }
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setComments(commentDTOList);
        briefReviewResponseDTO.setComments(commentResponseDTO);
        briefReviewResponseDTO.setId(briefReviewReturn.getId());
        if (briefReviewResponseDTO !=null)
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(201,"success")));
        else
            return ResponseEntity.ok(new Response(briefReviewResponseDTO, new StatusDTO(400,"fail")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addBriefReviewComment(@PathVariable Integer id, @RequestBody CommentRequestDTO commentRequestDTO, @AuthenticationPrincipal User user){
        Comment comment = new Comment();
        comment.setUser(user);
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(commentRequestDTO.getBody());
            comment.setContent(requestJson);
        }catch (Exception e){
            System.out.println("Exception");
        }

        Timestamp time =  new Timestamp(System.currentTimeMillis());
        comment.setPubTime(time);
        comment.setUser(user);

        comment.setIsAnony((commentRequestDTO.getIs_anonymous()==true)? 1:0);
        Comment commentreturn = briefReviewServiceImplement.(comment,id);
        if ( commentreturn!=null)
            return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400,"fail")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity updateBriefReviewUpvote(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        if ( evaluate!=null)
            return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400,"fail")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getBriefReviewComments(@PathVariable Integer id) throws NotFoundException {
        List<Comment> comments = briefReviewServiceImplement.getAllCommentsOfReview(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        if(comments!=null){
            Iterator it = comments.iterator();
            while(it.hasNext()){
                CommentDTO commentDTO = new CommentDTO((Comment) it.next());
                commentDTOS.add(commentDTO);
            }
        }
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setComments(commentDTOS);
        if ( commentResponseDTO !=null)
            return ResponseEntity.ok(new Response(commentResponseDTO,new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(commentResponseDTO,new StatusDTO(400,"fail")));
    }

}
