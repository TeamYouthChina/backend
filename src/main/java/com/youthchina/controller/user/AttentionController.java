package com.youthchina.controller.user;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.dto.util.PageResponse;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.exception.zhongyang.exception.InternalStatusCode;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.application.CompanyCURDService;
import com.youthchina.service.application.JobService;
import com.youthchina.service.application.JobServiceImpl;
import com.youthchina.service.community.*;
import com.youthchina.service.user.StudentService;
import com.youthchina.service.user.UserService;
import com.youthchina.util.dictionary.AttentionTargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhongyangwu on 4/16/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/")
public class AttentionController {
    private UserService userService;

    private final StudentService studentService;
    private final AttentionService attentionService;
    private final EssayService essayService;
    private final QuestionService questionService;
    private final VideoService videoService;
    private final AnswerService answerService;
    private final JobService jobService;
    private final BriefReviewService briefReviewService;
    private final CompanyCURDService companyCURDService;

    @Autowired
    public AttentionController(UserService userService, StudentService studentService, AttentionServiceImpl attentionService, EssayServiceImpl essayService, QuestionServiceImpl questionService, VideoServiceImpl videoService, AnswerServiceImpl answerService, JobServiceImpl jobService, BriefReviewService briefReviewService, CompanyCURDService companyCURDService) {
        this.userService = userService;
        this.studentService = studentService;
        this.attentionService = attentionService;
        this.essayService = essayService;
        this.questionService = questionService;
        this.videoService = videoService;
        this.answerService = answerService;
        this.jobService = jobService;
        this.briefReviewService = briefReviewService;
        this.companyCURDService = companyCURDService;
    }


    /**
     * @Description: get all attentions of user
     * @Param: [user_id, type]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: Qinghong Wang
     * @Date: 2019/4/4
     */
    @GetMapping("users/{id}/attentions")
    public ResponseEntity<?> getAllCollections(@PathVariable("id") Integer user_id, @RequestParam(value = "type") String type, @AuthenticationPrincipal User user, PageRequest pageRequest) throws NotFoundException, ForbiddenException {
        if (!user.getId().equals(user_id)) {
            throw new ForbiddenException();
        }
        DomainCRUDService service = this.getServiceByType(type);
        List<Integer> ids = this.attentionService.getAllIdsOfAttention(AttentionTargetType.getTypeId(type), user_id);
        List res = service.get(ids);
        List<ResponseDTO> dtos = new ArrayList<>();
        if (res != null) {
            dtos = this.convertToDTO(res, type, pageRequest.getStart(), pageRequest.getEnd());
        } else {
            res = new ArrayList(); //prevent null pointer
        }
        HashMap<String, PageResponse> resultMap = new HashMap<>();
        resultMap.put(type, new PageResponse(pageRequest, res.size(), dtos));
        return ResponseEntity.ok(Response.content(resultMap));
    }


    @PutMapping("articles/{id}/attention")
    public ResponseEntity updateAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay();
        comEssay.setId(id);
        attentionService.attention(comEssay, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("articles/attentions/{id}")
    public ResponseEntity deleteAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay();
        comEssay.setId(id);
        attentionService.cancel(comEssay, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("questions/{id}/attention")
    public ResponseEntity<?> followUp(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Question question = new Question();
        question.setId(id);
        attentionService.attention(question, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("questions/attentions/{id}")
    public ResponseEntity<?> cancelFollowUp(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Question question = new Question();
        question.setId(id);
        attentionService.cancel(question, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @PutMapping("editorials/{id}/attention")
    public ResponseEntity updateEditorialAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        attentionService.attention(briefReview, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("editorials/attentions/{id}")
    public ResponseEntity cancelEditorialAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        attentionService.cancel(briefReview, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PutMapping("answers/{id}/attention")
    public ResponseEntity updateAnswerAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer = new Answer();
        answer.setId(id);
        attentionService.attention(answer,user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("answers/attentions/{id}")
    public ResponseEntity deleteAnswerAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer = new Answer();
        answer.setId(id);
        attentionService.cancel(answer,user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }


    private DomainCRUDService getServiceByType(String type) throws NotFoundException {
        switch (type) {
            case "job":
                return this.jobService;
            case "company":
                return this.companyCURDService;
            case "answer":
                return this.answerService;
            case "article":
                return this.essayService;
            case "question":
                return this.questionService;
            case "editorial":
                return this.briefReviewService;
            default:
                throw new NotFoundException(404, InternalStatusCode.NOT_FOUND.value(), "do not have this type");
        }
    }

    private List<ResponseDTO> convertToDTO(List domains, String type, int start, int end) throws NotFoundException {
        List<ResponseDTO> responseDTOs = new ArrayList<>();
        for (int i = start; i <= Math.min(domains.size() - 1, end); i++) {
            responseDTOs.add(this.convertToDTO(domains.get(i), type));
        }
        return responseDTOs;

    }

    private ResponseDTO convertToDTO(Object domains, String type) throws NotFoundException {
        switch (type) {
            case "job":
                return new JobResponseDTO((Job) domains);
            case "company":
                return new CompanyResponseDTO((Company) domains);
            case "answer":
                return new SimpleAnswerResponseDTO((Answer) domains);
            case "question":
                return new QuestionResponseDTO((Question) domains);
            case "editorial":
                return new BriefReviewResponseDTO((BriefReview) domains);
            default:
                throw new NotFoundException(404, InternalStatusCode.NOT_FOUND.value(), "do not have this type");
        }
    }
}
