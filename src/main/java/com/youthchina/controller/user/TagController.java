package com.youthchina.controller.user;

import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.tag.TagRequestDTO;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.recommendation.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tianjian on 2019/5/22.
 */
@RestController
@RequestMapping("${web.url.prefix}/Tags/**")
public class TagController {
    @Autowired
    private RecommendServiceImpl recommendService;


    @PostMapping
    public ResponseEntity addTags(@RequestBody TagRequestDTO tagRequestDTO)throws NotFoundException{
        recommendService.addTag(tagRequestDTO.getLabel_ode(),tagRequestDTO.getTarget_type(),tagRequestDTO.getTarget_id());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping
    public ResponseEntity deleteTags( @RequestBody TagRequestDTO tagRequestDTO)throws NotFoundException{
        recommendService.deleteTag(tagRequestDTO.getLabel_ode(),tagRequestDTO.getTarget_type(),tagRequestDTO.getTarget_id());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }
}
