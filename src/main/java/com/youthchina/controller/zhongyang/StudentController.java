package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.Qinghong.JobApply;
import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ApplicantDTO;
import com.youthchina.dto.JobApplyDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 11/21/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/applicants/**")
public class StudentController extends DomainCRUDController<ApplicantDTO, Student, Integer> {
    private String url;
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService, @Value("${web.url.prefix}") String prefix) {
        this.studentService = studentService;
        this.url = prefix + "/applicants/";
    }

    @Override
    protected DomainCRUDService<Student, Integer> getService() {
        return this.studentService;
    }

    @Override
    protected ApplicantDTO DomainToDto(Student domain) {
        return new ApplicantDTO(domain);
    }

    @Override
    protected Student DtoToDomain(ApplicantDTO applicantDTO) {
        return new Student(applicantDTO);
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    @GetMapping("/{id}/**")
    public ResponseEntity<?> getStudent(@PathVariable Integer id) throws NotFoundException {
        return get(id);
    }

    @PostMapping("/**")
    public ResponseEntity<?> createStudentInfo(@AuthenticationPrincipal User user, @RequestBody ApplicantDTO applicant) {
        applicant.setId(user.getId());
        return add(applicant);
    }

    @PutMapping("/{id}/**")
    public ResponseEntity<?> updateStudentInfo(@RequestBody ApplicantDTO applicant) throws NotFoundException {
        return update(applicant);
    }

    @DeleteMapping("/{id}/**")
    public ResponseEntity<?> deleteStudentInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity<?> getApplicantsContacts(@PathVariable Integer id) throws NotFoundException {
        Student student= getService().get(id);
        ApplicantDTO applicantDTO = this.DomainToDto(student);
        return ResponseEntity.ok(new Response(applicantDTO.getContact()));
    }

    @GetMapping("/{id}/educations")
    public ResponseEntity<?> getApplicantsEducations(@PathVariable Integer id) throws NotFoundException {
        ApplicantDTO applicantDTO = this.getDto(id);
        return ResponseEntity.ok(new Response(applicantDTO.getEducations()));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<?> getApplicantsProjects(@PathVariable Integer id) throws NotFoundException {
        ApplicantDTO applicantDTO = this.getDto(id);
        return ResponseEntity.ok(new Response(applicantDTO.getProjects()));
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity<?> getApplicantsExperiences(@PathVariable Integer id) throws NotFoundException {
        ApplicantDTO applicantDTO = this.getDto(id);
        return ResponseEntity.ok(new Response(applicantDTO.getExperiences()));
    }

    @GetMapping("/{id}/certificates")
    public ResponseEntity<?> getApplicantsCertificates(@PathVariable Integer id) throws NotFoundException {
        ApplicantDTO applicantDTO = this.getDto(id);
        return ResponseEntity.ok(new Response(applicantDTO.getCertificates()));
    }

    @GetMapping("/{id}/extracurriculars")
    public ResponseEntity<?> getApplicantsExtracurriculars(@PathVariable Integer id) throws NotFoundException {
        ApplicantDTO applicantDTO = this.getDto(id);
        return ResponseEntity.ok(new Response(applicantDTO.getExtracurriculars()));
    }

    private ApplicantDTO getDto(Integer id) throws NotFoundException {
        return this.DomainToDto(this.getService().get(id));
    }

    @GetMapping("/{id}/jobCollects")
    public ResponseEntity<?> getApplicantsJobCollects(@PathVariable Integer id) throws NotFoundException{
        List<JobCollect> jobCollects=studentService.getJobCollect(id);
        return  ResponseEntity.ok(new Response(jobCollects));
    }

    @GetMapping("/{id}/companyCollects")
    public ResponseEntity<?> getApplicantsCompanyCollects(@PathVariable Integer id) throws NotFoundException{
        List<CompCollect> compCollects=studentService.getCompCollect(id);
        return  ResponseEntity.ok(new Response(compCollects));
    }

    /**
    * @Description: 通过user_id获得该用户所有的职位申请的信息
    * @Param: [id]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/18
    */
    @GetMapping("/{id}/applications")
    public ResponseEntity<?> getApplicantsJobApplies(@PathVariable Integer id) throws NotFoundException{
        List<JobApply> jobApplies=studentService.getJobApplies(id);
        List<JobApplyDTO> jobApplyDTOS=new ArrayList<>();
        for(JobApply jobApply:jobApplies){
            JobApplyDTO jobApplyDTO=new JobApplyDTO(jobApply);
            jobApplyDTOS.add(jobApplyDTO);
        }
        return  ResponseEntity.ok(new Response(jobApplyDTOS,new StatusDTO(0,"")));
    }

    @PostMapping("/{id}/jobApply/{job_id}")
    public ResponseEntity<?> addApplicantsJobApply(@PathVariable("job_id") Integer job_id,@PathVariable("id") Integer id,@AuthenticationPrincipal User user) throws NotFoundException,ForbiddenException{
        if(user.getId().equals(id))
        return  ResponseEntity.ok(new Response(studentService.jobApply(job_id,id)));
        else {
            throw new ForbiddenException();
        }
    }
    @PostMapping("/{id}/jobCollect/{job_id}")
    public ResponseEntity<?> addApplicantsJobCollect(@PathVariable("id") Integer id,@PathVariable("job_id") Integer job_id,@AuthenticationPrincipal User user) throws NotFoundException,ForbiddenException{
        if(user.getId().equals(id))
            return  ResponseEntity.ok(new Response
                    (studentService.addJobCollection(job_id,id)));
        else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/{id}/compCollect/{company_id}")
    public ResponseEntity<?> addApplicantsCompCollect(@PathVariable("id") Integer id,@PathVariable("company_id") Integer company_id,@AuthenticationPrincipal User user) throws NotFoundException,ForbiddenException{
        if(user.getId().equals(id))
            return  ResponseEntity.ok(new Response
                    (studentService.addCompCollect(company_id,id)));
        else {
            throw new ForbiddenException();
        }
    }







}
