package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.BriefReviewDTO;
import com.youthchina.dto.community.RequestCommentDTO;
import com.youthchina.dto.community.VideoDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CommunityQAServiceImplement;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@RestController
@RequestMapping("${web.url.prefix}/videos")
public class VideoController {
    @Autowired
    CommunityQAServiceImplement communityQAServiceImplement;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable Integer id) throws NotFoundException {
          Video video = communityQAServiceImplement.getVideo(id);
          VideoDTO videoDTO = new VideoDTO(video);
          return ResponseEntity.ok(new Response(videoDTO, new StatusDTO(200,"success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable Integer id) throws NotFoundException {
        communityQAServiceImplement.deleteVideo(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
    }

  /*  @PostMapping("/{id}")
    public ResponseEntity addVideo(@RequestPart MultipartFile file,  @AuthenticationPrincipal User user) throws NotFoundException {
        String f = file.getName();
        Video video = new Video();
        communityQAServiceImplement.addVideo()
        if ( commentreturn!=null)
            return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(400,"fail")));
    }*/
}
