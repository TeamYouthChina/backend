package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.video.VideoResponseDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AttentionService;
import com.youthchina.service.jinhao.CommentService;
import com.youthchina.service.jinhao.EvaluateService;
import com.youthchina.service.jinhao.VideoServiceImpl;
import com.youthchina.service.tianjian.StaticFileService;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

@RestController
@RequestMapping("${web.url.prefix}/videos/**")
public class VideoController {
    @Autowired
    VideoServiceImpl videoService;

    @Autowired
    private StaticFileService fileService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CommentService commentService;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    AttentionService attentionService;

    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable Integer id) throws NotFoundException {
        Video video = videoService.get(id);
        URL s = fileService.getFileUrl(video.getName(), "China");
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
        videoResponseDTO.setUrl(s.toString());
        return ResponseEntity.ok(new Response(videoResponseDTO, new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable Integer id) throws NotFoundException {
        videoService.delete(id);
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
        video.setName(id.toString());
        video.setUser(user);
        video.setTitle(id.toString());
        video.setUserId(user.getId());
        video.setRelaType(1);
        video = videoService.add(video);
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
        videoResponseDTO.setUrl(fileService.getFileUrl(id.toString()).toString());
        return ResponseEntity.ok(new Response(videoResponseDTO, new StatusDTO(201, "success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComments(@PathVariable Integer id, @RequestBody CommentRequestDTO commentDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment videocomment = new Comment(commentDTO);
        Video video = new Video();
        video.setId(id);
        video.setUserId(user.getId());
        Comment comment = commentService.add(videocomment,video);
        if (comment.getId() == null) {
            return ResponseEntity.ok(new Response(new StatusDTO(403, "failed")));
        } else
            return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getComments(@PathVariable Integer id){
        Video video = new Video();
        video.setId(id);
        commentService.getComments(video);
        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
        HashMap<String, Object> comments = new HashMap<>();
        comments.put("comments", videoResponseDTO.getComments());
        return ResponseEntity.ok(new Response(comments, new StatusDTO(200, "success")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity updateVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Video video = new Video();
        video.setId(id);
        evaluateService.upvote(video,user.getId());
            return ResponseEntity.ok(new Response(new StatusDTO(403, "failed")));
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity attentionVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Video video = new Video();
        video.setId(id);
        attentionService.attention(video,user.getId());
            return ResponseEntity.ok(new Response(new StatusDTO(403, "failed")));
    }


}
