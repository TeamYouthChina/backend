package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.*;
import com.youthchina.dto.Applicant.*;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
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
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student)));
    }

    @PostMapping("/**")
    public ResponseEntity<?> createStudentInfo(@AuthenticationPrincipal User user, @RequestBody ApplicantDTO applicant) {
        applicant.setId(user.getId());
        Student student=new Student(applicant);
        Student student1=studentService.add(student);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student1)));
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
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getContact()));
    }

    @GetMapping("/{id}/educations")
    public ResponseEntity<?> getApplicantsEducations(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getEducations()));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<?> getApplicantsProjects(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getProjects()));
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity<?> getApplicantsExperiences(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getExperiences()));
    }

    @GetMapping("/{id}/certificates")
    public ResponseEntity<?> getApplicantsCertificates(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getCertificates()));
    }

    @GetMapping("/{id}/extracurriculars")
    public ResponseEntity<?> getApplicantsExtracurriculars(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getExtracurriculars()));
    }

    private ApplicantDTO getDto(Integer id) throws NotFoundException {
        return this.DomainToDto(this.getService().get(id));
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

    @PostMapping("/{id}/educations")
    public ResponseEntity<?> getApplicantsEducation(@RequestBody EducationDTO educationDTO,@AuthenticationPrincipal User user,@PathVariable Integer id) throws NotFoundException{
        EducationInfo educationInfo=new EducationInfo(educationDTO);
        List<EducationResponseDTO> educationResponseDTOS=new ArrayList<>();
        List<EducationInfo> educationInfos=studentService.insertEducation(educationInfo,user.getId());
        for(EducationInfo educationInfo1:educationInfos){
            EducationResponseDTO educationResponseDTO=new EducationResponseDTO(educationInfo1);
            educationResponseDTOS.add(educationResponseDTO);
        }
        return ResponseEntity.ok(new Response(educationResponseDTOS));
    }

    @PostMapping("/{id}/works")
    public ResponseEntity<?> getApplicantsWork(@RequestBody WorkDTO workDTO,@AuthenticationPrincipal User user,@PathVariable Integer id)throws NotFoundException{
        Work work=new Work(workDTO);
        List<WorkResponseDTO> workResponseDTOS=new ArrayList<>();
        List<Work> works=studentService.insertWork(work,user.getId());
        for(Work work1:works){
            WorkResponseDTO workResponseDTO=new WorkResponseDTO(work1);
            workResponseDTOS.add(workResponseDTO);
        }
        return ResponseEntity.ok(new Response(workResponseDTOS));
    }

    @PostMapping("/{id}/projects")
    public ResponseEntity<?> getApplicantsProject(@RequestBody ProjectDTO projectDTO,@AuthenticationPrincipal User user,@PathVariable Integer id) throws NotFoundException{
        Project project=new Project(projectDTO);
        List<ProjectResponseDTO> projectResponseDTOS=new ArrayList<>();
        List<Project> projects=studentService.insertProject(project,user.getId());
        for(Project project1:projects){
            ProjectResponseDTO projectResponseDTO=new ProjectResponseDTO(project1);
            projectResponseDTOS.add(projectResponseDTO);
        }
        return ResponseEntity.ok(new Response(projectResponseDTOS));
    }

    @PostMapping("/{id}/extracurriculars")
    public ResponseEntity<?> getApplicantsExtracurriculars(@RequestBody ExtracurricularDTO extracurricularDTO,@AuthenticationPrincipal User user,@PathVariable Integer id)throws NotFoundException{
        Activity activity=new Activity(extracurricularDTO);
        List<ExtracurricularResponseDTO> extracurricularResponseDTOS=new ArrayList<>();
        List<Activity> activities=studentService.insertActivity(activity,user.getId());
        for(Activity activity1:activities){
            ExtracurricularResponseDTO extracurricularResponseDTO=new ExtracurricularResponseDTO(activity1);
            extracurricularResponseDTOS.add(extracurricularResponseDTO);
        }
        return ResponseEntity.ok(new Response(extracurricularResponseDTOS));

    }

    @PostMapping("/{id}/certificates")
    public ResponseEntity<?> getApplicantsCertificates(@RequestBody CertificateDTO certificateDTO,@AuthenticationPrincipal User user,@PathVariable Integer id)throws NotFoundException{
        Certificate certificate=new Certificate(certificateDTO);
        List<CertificateResponseDTO> certificateResponseDTOS=new ArrayList<>();
        List<Certificate> certificates=studentService.insertCertificate(certificate,user.getId());
        for(Certificate certificate1:certificates){
            CertificateResponseDTO certificateResponseDTO=new CertificateResponseDTO(certificate1);
            certificateResponseDTOS.add(certificateResponseDTO);
        }
        return ResponseEntity.ok(new Response(certificateResponseDTOS));
    }











}
