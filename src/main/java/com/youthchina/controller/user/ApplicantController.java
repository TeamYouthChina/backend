package com.youthchina.controller.user;

import com.youthchina.domain.Qinghong.Student;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.ApplicantRequestDTO;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.StudentService;
import com.youthchina.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2018/12/30.
 */
@RestController
@RequestMapping("${web.url.prefix}/applicant/**")
public class ApplicantController {
    private StudentService studentService;
    private UserService userService;

    @Autowired
    public ApplicantController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Response> getApplicant(@PathVariable("id") Integer id) throws NotFoundException {
        Student student = studentService.get(id);
        ApplicantRequestDTO applicantRequestDTO = new ApplicantRequestDTO(student);
        return ResponseEntity.ok(new Response(applicantRequestDTO));
    }

}
