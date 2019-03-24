package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.video.VideoResponseDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.VideoRecommendServiceImplement;
import com.youthchina.service.tianjian.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/discovery")
public class VideoRecommendController {
    @Autowired
    private VideoRecommendServiceImplement videoRecommendServiceImplement;
    @Autowired
    private StaticFileService staticFileService;

    @GetMapping("/videos")
    public ResponseEntity getRecommendVideos() throws NotFoundException {
        List<Video> videoList = videoRecommendServiceImplement.getVideoForYou();
        List<VideoResponseDTO> resultList = new ArrayList<>();

        for(Video video : videoList) {
            VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
            videoResponseDTO.setUrl(staticFileService.getFileUrl(videoResponseDTO.getUrl()).toString());
            resultList.add(videoResponseDTO);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("videos", resultList);

        return ResponseEntity.ok(new Response(map, new StatusDTO(200,"success")));
    }
}
