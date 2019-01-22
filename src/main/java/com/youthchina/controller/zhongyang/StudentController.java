package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ApplicantDTO;
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

/**
 * Created by zhongyangwu on 11/21/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/students/**")
public class StudentController extends DomainCRUDController<ApplicantDTO, Student, Integer> {
    private String url;
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService, @Value("${web.url.prefix}") String prefix) {
        this.studentService = studentService;
        this.url = prefix + "/student/";
    }

    @Override
    protected DomainCRUDService<Student, Integer> getService() {
        return this.studentService;
    }

    @Override
    protected ApplicantDTO convertDomainToDto(Student domain) {
        return new ApplicantDTO(domain);
    }

    @Override
    protected Student convertDtoToDomain(ApplicantDTO applicantDTO) {
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
}
