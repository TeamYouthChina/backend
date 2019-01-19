package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.StudentDTO;
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
public class StudentController extends DomainCRUDController<StudentDTO, Student, Integer> {
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
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer id) throws NotFoundException {
        return get(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createStudentInfo(@AuthenticationPrincipal User user, @RequestBody Student student) {
        student.setStu_id(user.getId());
        return add(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentInfo(@RequestBody Student student) throws NotFoundException {
        return update(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }
}
