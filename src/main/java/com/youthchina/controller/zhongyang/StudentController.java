package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ApplicantDTO;
import com.youthchina.dto.Response;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer id) throws NotFoundException {
        return get(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createStudentInfo(@AuthenticationPrincipal User user, @RequestBody ApplicantDTO applicant) {
        applicant.setId(user.getId());
        return add(applicant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentInfo(@RequestBody ApplicantDTO applicant) throws NotFoundException {
        return update(applicant);
    }

    @DeleteMapping("/{id}")
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
}
