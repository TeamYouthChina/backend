package com.youthchina.controller.user;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.domain.jinhao.Label;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;

import com.youthchina.dto.tag.TagRequestDTO;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.recommendation.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjian on 2019/5/22.
 */
@RestController
@RequestMapping("${web.url.prefix}/Tags/**")
public class TagController {
    @Autowired
    private RecommendServiceImpl recommendService;


    @GetMapping
    public ResponseEntity getTags(@AuthenticationPrincipal User user)throws NotFoundException{
        List<Label> labelList = recommendService.getUserLabels(user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }
    @PostMapping
    public ResponseEntity addTags(@RequestBody TagRequestDTO tagRequestDTO)throws NotFoundException{
        recommendService.addTag(tagRequestDTO.getLabelCode(),tagRequestDTO.getTargetType(),tagRequestDTO.getTargetId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping
    public ResponseEntity deleteTags( @RequestBody TagRequestDTO tagRequestDTO)throws NotFoundException{
        recommendService.deleteTag(tagRequestDTO.getLabelCode(),tagRequestDTO.getTargetType(),tagRequestDTO.getTargetId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }
}
