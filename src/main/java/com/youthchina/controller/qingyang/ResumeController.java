package com.youthchina.controller.qingyang;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.controller.zhongyang.DomainCRUDController;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.ResumeJsonRequestDTO;
import com.youthchina.dto.applicant.ResumeJsonResponseDTO;
import com.youthchina.dto.company.CompanyRequestDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.qingyang.CompanyCURDService;
import com.youthchina.service.qingyang.ResumeJsonServiceImpl;
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

    @Autowired
    public ResumeController(@Value("${web.url.prefix}") String prefix, ResumeJsonServiceImpl resumeJsonService
                            ) {
        this.url = prefix + "/resumes/";
        this.resumeJsonService = resumeJsonService;
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
        if(user.getId() == result.getUserId()){
            return update(resumeJson);
        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/online/{id}")
    @ResponseBodyDTO(ResumeJsonResponseDTO.class)
    public ResponseEntity<?> getResumeJson(@PathVariable Integer id, @AuthenticationPrincipal User user) throws BaseException {
        ResumeJson result = resumeJsonService.get(id);
        if(user.getId() == result.getUserId()){
            return get(id);
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/online/{id}")
    public ResponseEntity<?> deleteResumeJson(@PathVariable Integer id, @AuthenticationPrincipal User user) throws BaseException, ForbiddenException {
        ResumeJson result = resumeJsonService.get(id);
        if(user.getId() == result.getUserId()){
            return delete(id);
        } else {
            throw new ForbiddenException();
        }
    }

    @Override
    protected DomainCRUDService<ResumeJson, Integer> getService() {
        return this.resumeJsonService;
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }
}