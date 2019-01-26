package com.youthchina.controller.zhongyang;

import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.JobSearchDTO;
import com.youthchina.dto.JobSearchResultDTO;
import com.youthchina.dto.SimpleJobDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.service.qingyang.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/job/**")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobDetail(@PathVariable Integer jobId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, Authentication authentication) throws BaseException {
        Job job = this.jobService.get(jobId);
        if (detailLevel == 1) {
            JobSearchResultDTO<SimpleJobDTO> resultDTO = new JobSearchResultDTO<>();
            return ResponseEntity.ok(resultDTO);
        }
        throw new BaseException();
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody JobSearchDTO jobSearchDTO) throws BaseException {
        //todo: continue
        throw new BaseException();
    }
}
