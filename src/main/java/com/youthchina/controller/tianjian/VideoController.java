package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.community.comment.VideoCommentRequestDTO;
import com.youthchina.dto.community.video.VideoDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AttentionService;
import com.youthchina.service.jinhao.CommentService;
import com.youthchina.service.jinhao.CommunityQAServiceImplement;
import com.youthchina.service.jinhao.EvaluateService;
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
import java.util.HashMap;
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

    @Autowired
    CommentService commentService;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    AttentionService attentionService;

    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable Integer id) throws NotFoundException {
        Video video = communityQaService.getVideo(id);
        URL s = fileService.getFileUrl(video.getName(), "China");
        VideoDTO videoDTO = new VideoDTO(video);
        videoDTO.setUrl(s.toString());
        return ResponseEntity.ok(new Response(videoDTO, new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable Integer id) throws NotFoundException {
        communityQaService.deleteVideo(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PostMapping("/**")
    public ResponseEntity addVideo(@RequestPart MultipartFile file, @AuthenticationPrincipal User user) throws BaseException {
        Long id;
        System.out.println("start here!!!!!!!!!");
        try {
            id = fileService.saveFile(file.getResource(), user.getId());
        } catch (IOException e) {
            throw new BaseException(5000, 500, "Cannot upload file because server end error");
        }

        Video video = new Video();
        video.setName(id.toString());
        video.setUser(user);
        video.setTitle(id.toString());

        video = communityQaService.addVideo(video, user.getId(), 1, 1);
        VideoDTO videoDTO = new VideoDTO(video);
        videoDTO.setUrl(fileService.getFileUrl(id.toString()).toString());
        return ResponseEntity.ok(new Response(videoDTO, new StatusDTO(201, "success")));
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
        VideoDTO videoDTO = new VideoDTO(video);
        HashMap<String, Object> comments = new HashMap<>();
        comments.put("comments", videoDTO.getComments());
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
