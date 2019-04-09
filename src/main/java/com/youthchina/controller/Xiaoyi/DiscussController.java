package com.youthchina.controller.Xiaoyi;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.community.discuss.DiscussDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/reply")
public class DiscussController {
    @Autowired
    DiscussService discussService;

    @GetMapping("/{id}")
    public ResponseEntity getDiscuss(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException  {
        List<Discuss> discusses = discussService.getDiscusses(id,pageRequest.getStart(),pageRequest.getEnd());
        List<DiscussDTO> discussDTOS = new ArrayList<>();
        if (discusses != null) {
            for (Discuss discuss : discusses) {
                DiscussDTO discussDTO = new DiscussDTO(discuss);
                discussDTOS.add(discussDTO);
            }
        }
        ListResponse listResponse = new ListResponse(pageRequest, discussDTOS.size(),discussDTOS);
        return ResponseEntity.ok(listResponse);
    }



}
