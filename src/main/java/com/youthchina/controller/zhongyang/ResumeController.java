package com.youthchina.controller.zhongyang;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.service.zhongyang.ResumeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhongyangwu on 11/18/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/resume/**")
public class ResumeController {

    ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@AuthenticationPrincipal User user, @PathVariable("id") String resumeId) throws NotBelongException {
        Resume resume = resumeService.get(user, resumeId);
        if (resume == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resume);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @PathVariable("id") String resumeId) throws NotFoundException {
        resumeService.delete(user, resumeId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@AuthenticationPrincipal User user, @RequestBody Resume resume) {
        resumeService.update(resume);
        return ResponseEntity.ok(resume);
    }

    @PostMapping("/")
    public ResponseEntity<?> add(@AuthenticationPrincipal User user, @ResponseStatus Resume resume) {
        resume = resumeService.add(resume);
        try {
            return ResponseEntity.created(new URI("")).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
