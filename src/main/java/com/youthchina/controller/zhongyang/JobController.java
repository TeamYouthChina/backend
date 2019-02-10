package com.youthchina.controller.zhongyang;

import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.JobSearchDTO;
import com.youthchina.dto.JobSearchResultDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.SimpleJobDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.qingyang.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


/**
 * Created by zhongyangwu on 12/2/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/jobs/**")
public class JobController extends DomainCRUDController<SimpleJobDTO, Job, Integer>{

    private String url;
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService, @Value("${web.url.prefix}") String prefix) {
        this.jobService = jobService;
        this.url = prefix + "/jobs/";
    }

    @Override
    protected DomainCRUDService<Job, Integer> getService() {
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

    /*
    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable Integer id) throws NotFoundException {
        return get(id);
    }
    */

    @PostMapping("/")
    public ResponseEntity<?> createJobInfo(@RequestBody SimpleJobDTO simpleJobDTO) {
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


    @GetMapping("/{id}")
    public ResponseEntity<?> getJobDetail(@PathVariable(name = "id") Integer jobId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, Authentication authentication) throws BaseException {
        Job job = this.jobService.get(jobId);
        if (detailLevel == 1) {
            return ResponseEntity.ok(new Response(job));
        }
        throw new BaseException();
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody JobSearchDTO jobSearchDTO, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, Authentication authentication) throws BaseException {
        //todo: continue
        List <Job> searchResult = this.jobService.getJobByMore(jobSearchDTO.getJobId(),jobSearchDTO.getJobName(),
                jobSearchDTO.getComId(), jobSearchDTO.getComName(),jobSearchDTO.getStartTime(),jobSearchDTO.getEndTime(),
                jobSearchDTO.getType(), jobSearchDTO.getSalaryFloor(),jobSearchDTO.getSalaryCap(), jobSearchDTO.getActive(),
                jobSearchDTO.getLocation(), jobSearchDTO.getJobReqList(),jobSearchDTO.getIndustryList());
        JobSearchResultDTO jobSearchResultDTO = new JobSearchResultDTO();
        jobSearchResultDTO.setSearchResult(searchResult);

        if (detailLevel == 1) {
            return ResponseEntity.ok(new Response(jobSearchResultDTO));
        }
        throw new BaseException();
    }


}
