package com.youthchina.controller.zhongyang;

import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.*;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.qingyang.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhongyangwu on 12/2/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/jobs/**")
public class JobController extends DomainCRUDController<SimpleJobDTO, Job, Integer>{

    private String url;
    private JobService jobService;
    private StudentService studentService;

    @Autowired
    public JobController(JobService jobService,StudentService studentService, @Value("${web.url.prefix}") String prefix) {
        this.jobService = jobService;
        this.studentService=studentService;
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

    @PostMapping("/**")
    public ResponseEntity<?> createJobInfo(@RequestBody SimpleJobDTO simpleJobDTO) {
        return add(simpleJobDTO);
    }

    @PutMapping("/{id}/**")
    public ResponseEntity<?> updateJobInfo(@RequestBody SimpleJobDTO simpleJobDTO) throws NotFoundException {
        return update(simpleJobDTO);
    }

    @DeleteMapping("/{id}/**")
    public ResponseEntity<?> deleteJobInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }


    @GetMapping("/{id}/**")
    public ResponseEntity<?> getJobDetail(@PathVariable(name = "id") Integer jobId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, Authentication authentication) throws BaseException {
        Job job = this.jobService.get(jobId);
        if (detailLevel == 1) {
            return ResponseEntity.ok(new Response(new JobResponseDTO(job)));
        }
        throw new BaseException();
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody JobSearchDTO jobSearchDTO, Authentication authentication) throws BaseException {
        Date startDate = null;
        Date endDate = null;
        DurationDTO durationDTO = jobSearchDTO.getDurationDTO();
        if(durationDTO != null){
            if(durationDTO.getBegin()!=null){
                startDate = new Date(jobSearchDTO.getDurationDTO().getBegin().getTime());
            }
            if(durationDTO.getEnd()!=null){
                endDate = new Date(jobSearchDTO.getDurationDTO().getEnd().getTime());
            }
        }

        /**No companyId, location, jobReqList, industryList*/
        List <Job> searchResultJob = this.jobService.getJobByMore(jobSearchDTO.getId(),jobSearchDTO.getJobName(),
                null, jobSearchDTO.getCompanyName(),startDate,endDate,
                jobSearchDTO.getJobType(), jobSearchDTO.getSalaryFloor(),jobSearchDTO.getSalaryCap(), (jobSearchDTO.getActivate()?1:0),
                null, null,null);
        List <JobResponseDTO> searchResultJobDTO = new ArrayList<>();
        for (Job job : searchResultJob){
            searchResultJobDTO.add(new JobResponseDTO(job));
        }
        JobSearchResultDTO jobSearchResultDTO = new JobSearchResultDTO();
        jobSearchResultDTO.setSearchResult(searchResultJobDTO);

        return ResponseEntity.ok(new Response(jobSearchResultDTO));
    }

    /**
    * @Description: 完成申请职位的添加，通过job_id
    * @Param: [job_id, user]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/18
    */

    @PostMapping("/{id}/apply")
    public ResponseEntity<?> addJobApply(@PathVariable("id") Integer job_id,@AuthenticationPrincipal User user) throws NotFoundException {
        JobApplyDTO jobApplyDTO=new JobApplyDTO(studentService.jobApply(job_id,user.getId()));
            return  ResponseEntity.ok(new Response(jobApplyDTO,new StatusDTO(0,"")));

    }

    /**
    * @Description: 完成职位收藏，通过job_id以及user_id实现
    * @Param: [job_id, user]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/18
    */
//添加职位还有一些情况没有判断，在api缺少
    @PutMapping("/{id}/attention")
    public ResponseEntity<?> putJobCollection(@PathVariable("id") Integer job_id,@AuthenticationPrincipal User user) throws NotFoundException {
        Integer integer = studentService.addJobCollection(job_id, user.getId());
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400,"cannot collect this job,maybe the job has already delete")));

        }


    }

    /**
    * @Description: 通过collect_id删除职位收藏
    * @Param: [collect_id, user]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/19
    */
    @DeleteMapping("/attentions/{id}")
    public ResponseEntity<?> deleteJobCollection(@PathVariable("id") Integer collect_id,@AuthenticationPrincipal User user)throws NotFoundException{
        Integer integer=studentService.deleteJobCollect(collect_id);
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400,"cannot delete this company collection,maybe this collection has already delete")));

        }
    }



}
