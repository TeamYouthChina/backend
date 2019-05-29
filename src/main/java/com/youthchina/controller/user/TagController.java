package com.youthchina.controller.user;

import com.youthchina.domain.jinhao.Label;

import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;

import com.youthchina.dto.tag.TagRequestDTO;
import com.youthchina.dto.tag.TagResponseDTO;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.recommendation.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianjian on 2019/5/22.
 */
@RestController
@RequestMapping("${web.url.prefix}/labels/**")
public class TagController {
    @Autowired
    private RecommendServiceImpl recommendService;


    @GetMapping("/{type}/{id}")
    public ResponseEntity getTags(@PathVariable Integer id, @PathVariable Integer type){
        List<Label> labelList = recommendService.getLabels(type,id);
        List<TagResponseDTO> tagResponseDTOS = new ArrayList<>();
        Iterator iterator = labelList.iterator();
        while (iterator.hasNext()){
            Label label = (Label)iterator.next();
            TagResponseDTO tagResponseDTO = new TagResponseDTO(label);
            tagResponseDTOS.add(tagResponseDTO);
        }
        return ResponseEntity.ok(new Response(tagResponseDTOS,new StatusDTO(201, "success")));
    }
    @PostMapping
    public ResponseEntity addTags(@RequestBody TagRequestDTO tagRequestDTO)throws NotFoundException{
        recommendService.addTag(tagRequestDTO.getLabelCode(),tagRequestDTO.getTargetType(),tagRequestDTO.getTargetId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("/{labelCode}/{targetType}/{targetId}")
    public ResponseEntity deleteTags(@PathVariable String labelCode,@PathVariable Integer targetType,@PathVariable Integer targetId)throws NotFoundException{
        recommendService.deleteTag(labelCode,targetType,targetId);
        return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }
}
