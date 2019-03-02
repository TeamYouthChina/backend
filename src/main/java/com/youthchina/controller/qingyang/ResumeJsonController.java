package com.youthchina.controller.qingyang;

import com.youthchina.domain.Qinghong.ResumeJson;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Applicant.ResumeRequestDTO;
import com.youthchina.dto.Applicant.ResumeResponseDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Qinghong.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-28
 **/
@RestController
@RequestMapping("${web.url.prefix}/resumes/**")
public class ResumeJsonController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getResumeJson(@PathVariable("id") Integer id, @AuthenticationPrincipal User user) throws NotFoundException{
        ResumeJson resumeJson = studentService.getResumeJson(id);
        return ResponseEntity.ok(new ResumeResponseDTO(resumeJson));
    }

    @PostMapping("/")
    public ResponseEntity<?> createResume(@AuthenticationPrincipal User user, @RequestBody ResumeRequestDTO requestDTO) throws NotFoundException {
        ResumeJson resumeJson = studentService.insertResumeJson(new ResumeJson(requestDTO));
        return ResponseEntity.ok(new ResumeResponseDTO(resumeJson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResume(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Integer result = studentService.deleteResumeJson(id);
        if(result > 0){
            return ResponseEntity.ok(new StatusDTO(204,"deleted"));
        }
//        else {
//            return ResponseEntity.ok(new Response(new StatusDTO(,"delete failed")));
//        }
        return null;
    }

}
