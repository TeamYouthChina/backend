package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.*;
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
import java.util.HashMap;

@RestController
@RequestMapping("${web.url.prefix}/videos/**")
public class VideoController {
    @Autowired
    CommunityQAServiceImplement communityQAServiceImplement;

    @Autowired
    private StaticFileService fileService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable Integer id) throws NotFoundException {
          Video video = communityQAServiceImplement.getVideo(id);
          URL s = fileService.getFileUrl(video.getVideo_name(),"China");
          VideoDTO videoDTO = new VideoDTO(video);
          videoDTO.setUrl(s.toString());
          return ResponseEntity.ok(new Response(videoDTO, new StatusDTO(200,"success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable Integer id) throws NotFoundException {
        communityQAServiceImplement.deleteVideo(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

    @PostMapping("/**")
    public ResponseEntity addVideo(@RequestPart MultipartFile file, @AuthenticationPrincipal User user) throws BaseException {
        Long id;
        System.out.println("start here!!!!!!!!!");
        try {
            id = fileService.saveFile(file.getResource().getFile(), user.getId());
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
        Video videoReturn;
         videoReturn =  communityQAServiceImplement.addVideo(video,user.getId(),1,0);

        VideoDTO videoDTO = new VideoDTO(videoReturn);
        return ResponseEntity.ok(new Response(videoDTO,new StatusDTO(201,"success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComments(@PathVariable Integer id, @RequestBody VideoCommentDTO commentDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("add answers");
        VideoComment videocomment = new VideoComment(commentDTO);
        videocomment.setComment_pub_time(new Timestamp(System.currentTimeMillis()));
        videocomment.setComment_edit_time(new Timestamp(System.currentTimeMillis()));
        videocomment.setUser(user);
        videocomment.setUser_id(user.getId());
        VideoComment comment = communityQAServiceImplement.commentVideo(videocomment, id, 1);
        if(comment.getComment_id() == null){
            return ResponseEntity.ok(new Response( new StatusDTO(403,"failed")));
        }else
            return ResponseEntity.ok(new Response( new StatusDTO(201,"success")));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getComments(@PathVariable Integer id) throws NotFoundException {
        Video video = communityQAServiceImplement.getVideo(id);
        VideoDTO videoDTO = new VideoDTO(video);
        HashMap<String, Object> comments = new HashMap<>();
        comments.put("comments", videoDTO.getComments());
        return ResponseEntity.ok(new Response(comments, new StatusDTO(200,"success")));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity updateVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        VideoEvaluate videoEvaluate = communityQAServiceImplement.evaluateVideo(user.getId(), id);
        if(videoEvaluate.getEvaluate_id() == null){
            return ResponseEntity.ok(new Response( new StatusDTO(403,"failed")));
        }else
            return ResponseEntity.ok(new Response( new StatusDTO(201,"success")));
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity attentionVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        VideoAttention videoAttention = communityQAServiceImplement.attentionVideo(user.getId(),id);

        if(videoAttention.getAtten_id() == null){
            return ResponseEntity.ok(new Response( new StatusDTO(403,"failed")));
        }else
            return ResponseEntity.ok(new Response( new StatusDTO(201,"success")));
    }












}
