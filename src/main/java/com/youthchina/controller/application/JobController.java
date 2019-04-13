package com.youthchina.controller.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.applicant.SendingEmailDTO;
import com.youthchina.dto.application.JobApplyDTO;
import com.youthchina.dto.job.JobRequestDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.job.JobSearchDTO;
import com.youthchina.dto.util.DurationDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.ClientException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.MessageSendService;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.qingyang.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by zhongyangwu on 12/2/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/jobs/**")
public class JobController extends DomainCRUDController<Job, Integer> {

    private String url;
    private JobService jobService;
    private StudentService studentService;
    private final MessageSendService messageSendService;

    @Autowired
    public JobController(JobService jobService, StudentService studentService, @Value("${web.url.prefix}") String prefix, MessageSendService messageSendService) {
        this.jobService = jobService;
        this.studentService = studentService;
        this.url = prefix + "/jobs/";
        this.messageSendService = messageSendService;
    }

    @Override
    protected DomainCRUDService<Job, Integer> getService() {
        return this.jobService;
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }


    @PostMapping("/**")
    @ResponseBodyDTO(JobResponseDTO.class)
    public ResponseEntity<?> createJobInfo(@AuthenticationPrincipal User user, @RequestBodyDTO(JobRequestDTO.class) Job job) throws NotFoundException {
        job.setUserId(user.getId());
        return add(job);
//        Job job = jobService.add(new Job(jobRequestDTO));
//        return ResponseEntity.ok(new Response(new JobResponseDTO(job)));

    }

    @PutMapping("/{id}/**")
    @ResponseBodyDTO(JobResponseDTO.class)
    public ResponseEntity<?> updateJobInfo(@PathVariable Integer id, @RequestBodyDTO(JobRequestDTO.class) Job job) throws BaseException {
        //Job job = jobService.update(new Job(jobRequestDTO));
        job.setJobId(id);
        return update(job);

    }

    @DeleteMapping("/{id}/**")
    public ResponseEntity<?> deleteJobInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }


    @GetMapping("/{id}/**")
    public ResponseEntity<?> getJobDetail(@PathVariable(name = "id") Integer jobId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, @AuthenticationPrincipal User user) throws BaseException {
        Job job = null;
        if(user != null){
            job = jobService.getJobWithCollected(jobId, user.getId());
        } else {
            job = jobService.get(jobId);
        }
        if (detailLevel == 1 && job != null) {
            return ResponseEntity.ok(new Response(new JobResponseDTO(job)));
        }
        throw new BaseException();
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody JobSearchDTO jobSearchDTO, PageRequest pageRequest) throws BaseException {
        Date startDate = null;
        Date endDate = null;
        DurationDTO durationDTO = jobSearchDTO.getDurationDTO();
        if (durationDTO != null) {
            if (durationDTO.getBegin() != null) {
                startDate = new Date(jobSearchDTO.getDurationDTO().getBegin().getTime());
            } else {
                startDate = new Date(1970, 1, 1);
            }
            if (durationDTO.getEnd() != null) {
                endDate = new Date(jobSearchDTO.getDurationDTO().getEnd().getTime());
            } else {
                endDate = new Date(Calendar.getInstance().getTimeInMillis());
            }
        }
        if (startDate == null) {
            startDate = new Date(0L);
        }
        if (endDate == null) {
            endDate = new Date(Calendar.getInstance().getTimeInMillis());
        }

        /* No companyId, location, jobReqList, industryList*/
        List<Job> searchResultJob = this.jobService.getJobByMore(null, jobSearchDTO.getJobName(),
                null, jobSearchDTO.getCompanyName(), startDate, endDate,
                jobSearchDTO.getJobType(), jobSearchDTO.getSalaryFloor(), jobSearchDTO.getSalaryCap(), 1,
                null, null, null);
        List<JobResponseDTO> searchResultJobDTO = new ArrayList<>();
        for (int i = pageRequest.getStart(); i < Math.min(pageRequest.getEnd() + 1, searchResultJob.size()); i++) {
            searchResultJobDTO.add(new JobResponseDTO(searchResultJob.get(i)));
        }

        return ResponseEntity.ok(new ListResponse(pageRequest, searchResultJob.size(), searchResultJobDTO));
    }

    /**
     * @Description: 完成申请职位的添加，通过job_id
     * @Param: [job_id, user]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: Qinghong Wang
     * @Date: 2019/2/18
     */

    @PostMapping("/{id}/apply")
    public ResponseEntity<?> addJobApply(@PathVariable("id") Integer job_id, @AuthenticationPrincipal User user) throws NotFoundException, ClientException {
        JobApplyDTO jobApplyDTO = new JobApplyDTO(studentService.jobApply(job_id, user.getId()));
        return ResponseEntity.ok(new Response(jobApplyDTO, new StatusDTO(0, "")));

    }

    @PostMapping("/{id}/apply/sendingemail")
    public ResponseEntity<?> sendingResume(@PathVariable("id") Integer job_id, @RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user) throws NotFoundException, IOException {
        Job job = jobService.get(job_id);
        String company_email = job.getCompany().getCompanyMail();
        SendingEmailDTO sendingEmailDTO = new SendingEmailDTO();
        sendingEmailDTO.setCompany_email(company_email);
        sendingEmailDTO.setUser_id(user.getId());
        InputStream input = file.getInputStream();
        byte[] bytesArray = new byte[(int) file.getSize()];

        input.read(bytesArray); //read file into bytes[]
        input.close();
        sendingEmailDTO.setBytes(bytesArray);
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(sendingEmailDTO);
        System.out.print(message);
        messageSendService.sendMessage(message);
        return ResponseEntity.ok(new Response());

    }


    /**
     * @Description: 完成职位收藏，通过job_id以及user_id实现
     * @Param: [job_id, user]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: Qinghong Wang
     * @Date: 2019/2/18
     */

    @PutMapping("/{id}/attention")
    public ResponseEntity<?> putJobCollection(@PathVariable("id") Integer job_id, @AuthenticationPrincipal User user) throws NotFoundException {
        Integer integer = studentService.addJobCollection(job_id, user.getId());
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400, "cannot collect this job,maybe the job has already delete")));

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
    public ResponseEntity<?> deleteJobCollection(@PathVariable("id") Integer job_id, @AuthenticationPrincipal User user) throws NotFoundException {
        Integer collectionId = studentService.getCollectionByJobId(job_id, user.getId());
        if (collectionId == null) {
            return ResponseEntity.status(400).body(new Response(new StatusDTO(400, "Collection doest not exist.")));
        }
        Integer integer = studentService.deleteJobCollect(collectionId);
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400, "cannot delete this company collection,maybe this collection has already delete")));

        }
    }


}