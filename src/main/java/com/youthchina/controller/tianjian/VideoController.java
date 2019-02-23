package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.BriefReviewDTO;
import com.youthchina.dto.community.RequestCommentDTO;
import com.youthchina.dto.community.RequestVideoDTO;
import com.youthchina.dto.community.VideoDTO;
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
import java.sql.Timestamp;

@RestController
@RequestMapping("${web.url.prefix}/videos")
public class VideoController {
    @Autowired
    CommunityQAServiceImplement communityQAServiceImplement;

    @Autowired
    UserServiceImpl userService;

    private StaticFileService fileService;

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

    @PostMapping("/{id}")
    public ResponseEntity addVideo(@RequestPart MultipartFile file, @RequestBody RequestVideoDTO requestVideoDTO, @AuthenticationPrincipal User user) throws BaseException {
        Long id;
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
        if(requestVideoDTO.getCompany_id()!=null)
         videoReturn =  communityQAServiceImplement.addVideo(video,user.getId(),2,requestVideoDTO.getCompany_id());
        else
         videoReturn = communityQAServiceImplement.addVideo(video,user.getId(),1,0);
        VideoDTO videoDTO = new VideoDTO(videoReturn);
        return ResponseEntity.ok(new Response(videoDTO,new StatusDTO(201,"success")));

    }
}
