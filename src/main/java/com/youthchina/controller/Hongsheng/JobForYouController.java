package com.youthchina.controller.Hongsheng;


import com.youthchina.domain.qingyang.Job;

import com.youthchina.dto.Response;
import com.youthchina.dto.SimpleJobDTO;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.RecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongshengzhang on 2/23/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/job-for-you")
public class JobForYouController {

    @Autowired
    private RecommendServiceImplement recommendServiceImplement;

    @GetMapping("/intern")
    public ResponseEntity getRecommandIntern() throws NotFoundException {
        List<Job> jobList = recommendServiceImplement.getInternForYou();
        List<SimpleJobDTO> resultList = new ArrayList<>();
        for(Job job : jobList) {
            resultList.add(new SimpleJobDTO(job));
        }

        if (resultList!=null)
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(400,"fail")));
    }


    @GetMapping("/general")
    public ResponseEntity getRecommandJob() throws NotFoundException {
        List<Job> jobList = recommendServiceImplement.getJobForYou();
        List<SimpleJobDTO> resultList = new ArrayList<>();
        for(Job job : jobList) {
            resultList.add(new SimpleJobDTO(job));
        }

        if (resultList!=null)
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(resultList, new StatusDTO(400,"fail")));
    }
}
