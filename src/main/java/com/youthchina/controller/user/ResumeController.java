package com.youthchina.controller.user;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.domain.qingyang.ResumePDF;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.ResumeJsonRequestDTO;
import com.youthchina.dto.applicant.ResumeJsonResponseDTO;
import com.youthchina.dto.applicant.ResumePDFDTO;
import com.youthchina.exception.zhongyang.exception.BaseException;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.application.ResumeJsonServiceImpl;
import com.youthchina.service.application.ResumePDFServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: Qingyang Zhao
 * @create: 2019-04-11
 **/
@RestController
@RequestMapping("${web.url.prefix}/resumes/**")
public class ResumeController extends DomainCRUDController<ResumeJson, Integer> {


    private String url;
    private ResumeJsonServiceImpl resumeJsonService;
    private ResumePDFServiceImpl resumePDFService;

    @Autowired
    public ResumeController(@Value("${web.url.prefix}") String prefix,
                            ResumeJsonServiceImpl resumeJsonService,
                            ResumePDFServiceImpl resumePDFService
    ) {
        this.url = prefix + "/resumes/";
        this.resumeJsonService = resumeJsonService;
        this.resumePDFService = resumePDFService;
    }

    @PostMapping("/online/**")
    @ResponseBodyDTO(ResumeJsonResponseDTO.class)
    public ResponseEntity<?> postResumeJson(@AuthenticationPrincipal User user, @RequestBodyDTO(ResumeJsonRequestDTO.class) ResumeJson resumeJson) throws NotFoundException {
        resumeJson.setUserId(user.getId());
        return add(resumeJson);
    }

    @PutMapping("/online/{id}")
    @ResponseBodyDTO(ResumeJsonResponseDTO.class)
    public ResponseEntity<?> updateResumeJson(@PathVariable Integer id, @AuthenticationPrincipal User user, @RequestBodyDTO(ResumeJsonRequestDTO.class) ResumeJson resumeJson) throws NotFoundException, ForbiddenException {
        ResumeJson result = resumeJsonService.get(id);
        if (user.getId() == result.getUserId()) {
            return update(resumeJson);
        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/online/{id}")
    @ResponseBodyDTO(ResumeJsonResponseDTO.class)
    public ResponseEntity<?> getResumeJson(@PathVariable Integer id, @AuthenticationPrincipal User user) throws BaseException {
        ResumeJson result = resumeJsonService.get(id);
        if (user.getId() == result.getUserId()) {
            return get(id);
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/online/{id}")
    public ResponseEntity<?> deleteResumeJson(@PathVariable Integer id, @AuthenticationPrincipal User user) throws BaseException {
        ResumeJson result = resumeJsonService.get(id);
        if (user.getId() == result.getUserId()) {
            return delete(id);
        } else {
            throw new ForbiddenException();
        }
    }

    @Override
    protected DomainCRUDService<ResumeJson, Integer> getService() {
        return this.resumeJsonService;
    }


    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> getResumePDF(@PathVariable Integer id, @AuthenticationPrincipal User user) throws BaseException {
        ResumePDF result = resumePDFService.get(id);
        if (user.getId() == result.getStuId()) {
            return ResponseEntity.ok(new Response(new ResumePDFDTO(result)));
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/pdf/**")
    public ResponseEntity<?> postResumePDF(@AuthenticationPrincipal User user, @RequestBodyDTO(ResumePDFDTO.class) ResumePDF resumePDF) throws NotFoundException {
        resumePDF.setStuId(user.getId());
        resumePDF.setGenerateMethod(ResumePDF.GENERATED);
        resumePDF.setStuId(user.getId());
        resumePDF = resumePDFService.add(resumePDF);
        return ResponseEntity.ok(new Response(new ResumePDFDTO(resumePDF)));
    }

    @PatchMapping("/pdf/{id}")
    public ResponseEntity<?> updateResumePDFName(@PathVariable Integer id, @AuthenticationPrincipal User user, @RequestBodyDTO(ResumePDFDTO.class) ResumePDF resumePDF) throws BaseException {
        ResumePDF origin = resumePDFService.get(id);
        if (user.getId().equals(origin.getStuId())) {
            origin.setResumeName(resumePDF.getResumeName());
            resumePDF = resumePDFService.update(origin);
            return ResponseEntity.ok(new Response(new ResumePDFDTO(resumePDF)));
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/pdf/{id}")
    public ResponseEntity<?> deleteResumePDF(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException, ForbiddenException {
        ResumePDF result = resumePDFService.get(id);
        if (user.getId().equals(result.getStuId())) {
            resumePDFService.delete(id);
            return ResponseEntity.ok(new Response());
        } else {
            throw new ForbiddenException();
        }
    }


    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }
}