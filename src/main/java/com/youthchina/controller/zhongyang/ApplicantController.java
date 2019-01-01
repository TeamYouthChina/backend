package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ApplicantDTO;
import com.youthchina.dto.Response;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Qinghong.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhong on 2018/12/30.
 */
@RestController
@RequestMapping("${web.url.prefix}/applicant/**")
public class ApplicantController {
    private StudentService studentService;

    @Autowired
    public ApplicantController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Response> getApplicant(@PathVariable("id") Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Student student = studentService.get(id);
        ApplicantDTO applicantDTO = new ApplicantDTO(student, user);
        return ResponseEntity.ok(new Response(applicantDTO));
    }

}
