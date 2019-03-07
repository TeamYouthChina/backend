package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Applicant.*;
import com.youthchina.dto.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
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
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student)));
    }

    @PostMapping("/**")
    public ResponseEntity<?> createStudentInfo(@AuthenticationPrincipal User user, @RequestBody ApplicantDTO applicant) {
        Student student=new Student(applicant);
        student.setId(user.getId());
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
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getContacts()));
    }

    @GetMapping("/{id}/educations")
    public ResponseEntity<?> getApplicantsEducations(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        ListResponse listResponse = new ListResponse(new ApplicantResponseDTO(student).getEducations(),"educations");
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<?> getApplicantsProjects(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getProjects()));
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity<?> getApplicantsExperiences(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        ListResponse listResponse = new ListResponse(new ApplicantResponseDTO(student).getExperiences(),"experiences");
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/certificates")
    public ResponseEntity<?> getApplicantsCertificates(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        ListResponse listResponse = new ListResponse(new ApplicantResponseDTO(student).getCertifications(),"certificates");
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/extracurriculars")
    public ResponseEntity<?> getApplicantsExtracurriculars(@PathVariable Integer id) throws NotFoundException {
        Student student=studentService.get(id);
        ListResponse listResponse = new ListResponse(new ApplicantResponseDTO(student).getExtracurriculars(),"extracurriculars");
        return ResponseEntity.ok(listResponse);
    }
    @GetMapping("/{id}/skills")
    public ResponseEntity<?> getApplicantsSkills(@PathVariable Integer id) throws NotFoundException{
        Student student=studentService.get(id);
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getSkills()));
    }

    @GetMapping("/skills/**")
    public ResponseEntity<?> getAllSkills() throws NotFoundException{
        List<LabelInfo> labelInfos=studentService.getAllSkills();
        List<SkillsResponseDTO> labelInfos1=new ArrayList<>();
        for(LabelInfo labelInfo:labelInfos){
            SkillsResponseDTO skillsResponseDTO=new SkillsResponseDTO(labelInfo);
            labelInfos1.add(skillsResponseDTO);
        }

        return ResponseEntity.ok(new Response(labelInfos1));
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

//    @PostMapping("/{id}/educations")
//    public ResponseEntity<?> getApplicantsEducation(@RequestBody EducationDTO educationDTO,@AuthenticationPrincipal User user,@PathVariable Integer id) throws NotFoundException{
//        EducationInfo educationInfo=new EducationInfo(educationDTO);
//        List<EducationResponseDTO> educationResponseDTOS=new ArrayList<>();
//        List<EducationInfo> educationInfos=studentService.insertEducation(educationInfo,user.getId());
//        for(EducationInfo educationInfo1:educationInfos){
//            EducationResponseDTO educationResponseDTO=new EducationResponseDTO(educationInfo1);
//            educationResponseDTOS.add(educationResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(educationResponseDTOS));
//    }
//
//    @PostMapping("/{id}/experiences")
//    public ResponseEntity<?> getApplicantsWork(@RequestBody WorkDTO workDTO,@AuthenticationPrincipal User user,@PathVariable Integer id)throws NotFoundException{
//        Work work=new Work(workDTO);
//        List<WorkResponseDTO> workResponseDTOS=new ArrayList<>();
//        List<Work> works=studentService.insertWork(work,user.getId());
//        for(Work work1:works){
//            WorkResponseDTO workResponseDTO=new WorkResponseDTO(work1);
//            workResponseDTOS.add(workResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(workResponseDTOS));
//    }
//
//    @PostMapping("/{id}/projects")
//    public ResponseEntity<?> getApplicantsProject(@RequestBody ProjectDTO projectDTO,@AuthenticationPrincipal User user,@PathVariable Integer id) throws NotFoundException{
//        Project project=new Project(projectDTO);
//        List<ProjectResponseDTO> projectResponseDTOS=new ArrayList<>();
//        List<Project> projects=studentService.insertProject(project,user.getId());
//        for(Project project1:projects){
//            ProjectResponseDTO projectResponseDTO=new ProjectResponseDTO(project1);
//            projectResponseDTOS.add(projectResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(projectResponseDTOS));
//    }
//
//    @PostMapping("/{id}/extracurriculars")
//    public ResponseEntity<?> getApplicantsExtracurriculars(@RequestBody ExtracurricularDTO extracurricularDTO,@AuthenticationPrincipal User user,@PathVariable Integer id)throws NotFoundException{
//        Activity activity=new Activity(extracurricularDTO);
//        List<ExtracurricularResponseDTO> extracurricularResponseDTOS=new ArrayList<>();
//        List<Activity> activities=studentService.insertActivity(activity,user.getId());
//        for(Activity activity1:activities){
//            ExtracurricularResponseDTO extracurricularResponseDTO=new ExtracurricularResponseDTO(activity1);
//            extracurricularResponseDTOS.add(extracurricularResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(extracurricularResponseDTOS));
//
//    }
//
//    @PostMapping("/{id}/certificates")
//    public ResponseEntity<?> getApplicantsCertificates(@RequestBody CertificateDTO certificateDTO,@AuthenticationPrincipal User user,@PathVariable Integer id)throws NotFoundException{
//        Certificate certificate=new Certificate(certificateDTO);
//        List<CertificateResponseDTO> certificateResponseDTOS=new ArrayList<>();
//        List<Certificate> certificates=studentService.insertCertificate(certificate,user.getId());
//        for(Certificate certificate1:certificates){
//            CertificateResponseDTO certificateResponseDTO=new CertificateResponseDTO(certificate1);
//            certificateResponseDTOS.add(certificateResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(certificateResponseDTOS));
//    }
    /**
    * @Description: 实现education的保存操作
    * @Param: [educationDTOS, user]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/25
    */

    @RequestMapping(value = "/{id}/educations",method ={RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<?> saveApplicantsEducations(@RequestBody List<EducationDTO> educationDTOS,@AuthenticationPrincipal User user,@PathVariable ("id") Integer id) throws NotFoundException{
        List<EducationInfo> educationInfos=new ArrayList<>();
        for(EducationDTO educationDTO:educationDTOS){
            EducationInfo educationInfo=new EducationInfo(educationDTO);
            educationInfos.add(educationInfo);
        }
        List<EducationInfo> educationInfos1=studentService.insertEducations(educationInfos,user.getId());
        List<EducationResponseDTO> educationResponseDTOS=new ArrayList<>();
        for(EducationInfo educationInfo:educationInfos1){
            EducationResponseDTO educationResponseDTO=new EducationResponseDTO(educationInfo);
            educationResponseDTOS.add(educationResponseDTO);
        }
        return ResponseEntity.ok(new Response(educationResponseDTOS));
    }
    /**
    * @Description: 实现work的保存操作
    * @Param: [workDTOS, user]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/25
    */

    @RequestMapping(value = "/{id}/experiences",method ={RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<?> saveApplicantsWorks(@RequestBody List<WorkDTO> workDTOS,@AuthenticationPrincipal User user,@PathVariable ("id") Integer id)throws NotFoundException{
        List<Work> works=new ArrayList<>();
        for(WorkDTO workDTO:workDTOS){
            Work work=new Work(workDTO);
            works.add(work);
        }
        List<Work> works1=studentService.insertWorks(works,user.getId());
        List<WorkResponseDTO> workResponseDTOS=new ArrayList<>();
        for(Work work:works1){
            WorkResponseDTO workResponseDTO=new WorkResponseDTO(work);
            workResponseDTOS.add(workResponseDTO);
        }
        ListResponse listResponse = new ListResponse(workResponseDTOS,"experiences");
        return ResponseEntity.ok(listResponse);
    }
    /** 
    * @Description: 实现项目的保存操作 
    * @Param: [projectDTOS, user] 
    * @return: org.springframework.http.ResponseEntity<?> 
    * @Author: Qinghong Wang 
    * @Date: 2019/2/26 
    */
    @RequestMapping(value = "/{id}/projects",method ={RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<?> saveApplicantsProjects(@RequestBody List<ProjectDTO> projectDTOS,@AuthenticationPrincipal User user,@PathVariable ("id") Integer id) throws  NotFoundException{
        List<Project> projects=new ArrayList<>();
        for(ProjectDTO projectDTO:projectDTOS){
            Project project=new Project(projectDTO);
            projects.add(project);
        }
        List<Project> projects1=studentService.insertProjects(projects,user.getId());
        List<ProjectResponseDTO> projectResponseDTOS=new ArrayList<>();
        for(Project project:projects1){
            ProjectResponseDTO projectResponseDTO=new ProjectResponseDTO(project);
            projectResponseDTOS.add(projectResponseDTO);
        }
        return ResponseEntity.ok(new Response(projectResponseDTOS));
    }
    /** 
    * @Description: 实现课外经历的保存 
    * @Param: [extracurricularDTOS, user] 
    * @return: org.springframework.http.ResponseEntity<?> 
    * @Author: Qinghong Wang 
    * @Date: 2019/2/26 
    */
    @RequestMapping(value = "/{id}/extracurriculars",method ={RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<?> saveApplicantsExtracurriculars(@RequestBody List<ExtracurricularDTO> extracurricularDTOS,@AuthenticationPrincipal User user,@PathVariable ("id") Integer id) throws NotFoundException{
        List<Activity> activities=new ArrayList<>();
        for(ExtracurricularDTO extracurricularDTO:extracurricularDTOS){
            Activity activity=new Activity(extracurricularDTO);
            activities.add(activity);
        }
        List<Activity> activities1=studentService.insertActivities(activities,user.getId());
        List<ExtracurricularResponseDTO> extracurricularResponseDTOS=new ArrayList<>();
        for(Activity activity:activities1){
            ExtracurricularResponseDTO extracurricularResponseDTO=new ExtracurricularResponseDTO(activity);
            extracurricularResponseDTOS.add(extracurricularResponseDTO);
        }
        return ResponseEntity.ok(new Response(extracurricularResponseDTOS));
    }
    /** 
    * @Description: 实现证书信息的保存 
    * @Param: [certificateDTOS, user] 
    * @return: org.springframework.http.ResponseEntity<?> 
    * @Author: Qinghong Wang 
    * @Date: 2019/2/26 
    */

    @RequestMapping(value = "/{id}/certificates",method ={RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<?> saveApplicantsCertificates(@RequestBody List<CertificateDTO> certificateDTOS,@AuthenticationPrincipal User user,@PathVariable ("id") Integer id)throws NotFoundException{
        List<Certificate> certificates1=new ArrayList<>();
        for(CertificateDTO certificateDTO:certificateDTOS){
            Certificate certificate=new Certificate(certificateDTO);
            certificates1.add(certificate);
        }
        List<Certificate> certificates=studentService.insertCertificates(certificates1,user.getId());
        List<CertificateResponseDTO> certificateResponseDTOS=new ArrayList<>();
        for(Certificate certificate:certificates){
            CertificateResponseDTO certificateResponseDTO=new CertificateResponseDTO(certificate);
            certificateResponseDTOS.add(certificateResponseDTO);
        }
        ListResponse listResponse = new ListResponse(certificateResponseDTOS,"certificates");
        return ResponseEntity.ok(listResponse);
    }

    @RequestMapping(value = "/{id}/skills",method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<?> saveApplicantsSkills(@RequestBody List<String> skills,@AuthenticationPrincipal User user,@PathVariable ("id") Integer id) throws NotFoundException{
        List<LabelInfo> labelInfos=studentService.insertLabels(skills,id);
        List<SkillsResponseDTO> skillsResponseDTOS=new ArrayList<>();
        for(LabelInfo labelInfo:labelInfos){
            SkillsResponseDTO skillsResponseDTO=new SkillsResponseDTO(labelInfo);
            skillsResponseDTOS.add(skillsResponseDTO);
        }
        return ResponseEntity.ok(new Response(skillsResponseDTOS));

    }

//    @RequestMapping(value = "/{id}/contacts",method ={RequestMethod.POST,RequestMethod.PUT})
//    public ResponseEntity<?> saveApplicantsContacts(@RequestBody List<ContactDTO> contactDTOS,@AuthenticationPrincipal User user,@PathVariable ("id") Integer id)throws NotFoundException{
//        List<>
//    }



    @GetMapping("/{id}/resumes")
    public ResponseEntity<?> getApplicantsResumes(@PathVariable Integer id) throws NotFoundException{
        List<ResumeJson> resumeJsonList = studentService.selectResumeJsonByStuId(id);
        if(resumeJsonList == null || resumeJsonList.size() == 0){
            throw new NotFoundException( 404, 404, "No Resume available.");
        }
        List<ResumeResponseDTO> responseDTOList = new ArrayList<>();
        for(ResumeJson resumeJson : resumeJsonList){
            responseDTOList.add(new ResumeResponseDTO(resumeJson));
        }
        ListResponse listResponse = new ListResponse(responseDTOList,"resumes");
        return  ResponseEntity.ok(listResponse);
    }










}
