package com.youthchina.controller.zhongyang;

import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.JobSearchDTO;
import com.youthchina.dto.JobSearchResultDTO;
import com.youthchina.dto.SimpleJobDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.qingyang.JobService;
import com.youthchina.exception.zhongyang.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/job/**")
public class JobController extends DomainCRUDController<SimpleJobDTO, Job, Integer> {

    private String url;
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService, @Value("${web.url.prefix}") String prefix) {
        this.jobService = jobService;
        this.url = prefix + "/job/";
    }

    @Override
    protected DomainCRUDService <Job, Integer> getService() {
        return this.jobService;
    }

    @Override
    protected SimpleJobDTO DomainToDto(Job domain) {
        return new SimpleJobDTO(domain);
    }

    @Override
    protected Job DtoToDomain(SimpleJobDTO simpleJobDTO) {
        return new Job(simpleJobDTO);
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
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
        return ResponseEntity.ok(jobSearchDTO);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getJob(@PathVariable Integer id) throws NotFoundException {
//        return get(id);
//    }

    @PostMapping("/")
    public ResponseEntity<?> createStudentInfo(@RequestBody SimpleJobDTO simpleJobDTO) {
        return add(simpleJobDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJobInfo(@RequestBody SimpleJobDTO simpleJobDTO) throws NotFoundException {
        return update(simpleJobDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }


}
