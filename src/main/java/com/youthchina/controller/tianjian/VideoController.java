package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.community.comment.VideoCommentRequestDTO;
import com.youthchina.dto.community.video.VideoResponseDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CommunityQAServiceImplement;
import com.youthchina.service.tianjian.StaticFileService;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/videos/**")
public class VideoController {
    @Autowired
    CommunityQAServiceImplement communityQaService;

    @Autowired
    private StaticFileService fileService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable Integer id) throws NotFoundException {
        Video video = communityQaService.getVideo(id);
        URL s = fileService.getFileUrl(video.getVideo_name(), "China");
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
        videoResponseDTO.setUrl(s.toString());
        return ResponseEntity.ok(new Response(videoResponseDTO, new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable Integer id) throws NotFoundException {
        communityQaService.deleteVideo(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PostMapping("/**")
    public ResponseEntity addVideo(@RequestPart MultipartFile file, @AuthenticationPrincipal User user) throws BaseException {
        Long id;
        try {
            id = fileService.saveFile(file.getResource(), user.getId());
        } catch (IOException e) {
            throw new BaseException(5000, 500, "Cannot upload file because server end error");
        }

        Video video = new Video();
        video.setIs_delete(0);
        video.setVideo_name(id.toString());
        video.setUser(user);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        video.setVideo_upload_time(time);
        video.setVideo_title(id.toString());

        video = communityQaService.addVideo(video, user.getId(), 1, 1);
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
        videoResponseDTO.setUrl(fileService.getFileUrl(id.toString()).toString());
        return ResponseEntity.ok(new Response(videoResponseDTO, new StatusDTO(201, "success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComments(@PathVariable Integer id, @RequestBody VideoCommentRequestDTO commentDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        VideoComment videocomment = new VideoComment(commentDTO);
        videocomment.setComment_pub_time(new Timestamp(System.currentTimeMillis()));
        videocomment.setComment_edit_time(new Timestamp(System.currentTimeMillis()));
        videocomment.setUser(user);
        videocomment.setUser_id(user.getId());
        VideoComment comment = communityQaService.commentVideo(videocomment, id, 1);
        if (comment.getComment_id() == null) {
            return ResponseEntity.ok(new Response(new StatusDTO(403, "failed")));
        } else
            return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getComments(@PathVariable Integer id) throws NotFoundException {
        List<VideoComment> videoCommentList = communityQaService.getVideo(id).getVideoComments();
        CommentResponseDTO commentResponseDTO = new  CommentResponseDTO();
        Iterator it = videoCommentList.iterator();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        while(it.hasNext()){
            VideoComment videoComment = (VideoComment) it.next();
            CommentDTO commentDTO = new CommentDTO(videoComment);
            commentDTOS.add(commentDTO);
        }
        commentResponseDTO.setComments(commentDTOS);
        HashMap<String, Object> comments = new HashMap<>();
        comments.put("comments", commentResponseDTO.getComments());
        return ResponseEntity.ok(new Response(comments, new StatusDTO(200, "success")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity updateVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        VideoEvaluate videoEvaluate = communityQaService.evaluateVideo(user.getId(), id);
        if (videoEvaluate.getEvaluate_id() == null) {
            return ResponseEntity.ok(new Response(new StatusDTO(403, "failed")));
        } else
            return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity attentionVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        VideoAttention videoAttention = communityQaService.attentionVideo(user.getId(), id);

        if (videoAttention.getAtten_id() == null) {
            return ResponseEntity.ok(new Response(new StatusDTO(403, "failed")));
        } else
            return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }


}
