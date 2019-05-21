package com.youthchina.controller.user;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.zhongyang.Gender;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.security.ModifiedUserDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.InfluenceDTO;
import com.youthchina.exception.zhongyang.exception.ClientException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/users/**")
public class UserController extends DomainCRUDController<User, Integer> {

    private final StudentService studentService;
    private final AttentionService attentionService;
    private final EssayService essayService;
    private final QuestionService questionService;
    private final VideoService videoService;
    private final AnswerService answerService;
    private final JobService jobService;
    private final BriefReviewService briefReviewService;
    private final CompanyCURDService companyCURDService;
    private final InfluenceService influenceService;
    private UserService userService;
    private String url;

    @Autowired
    public UserController(UserService userService, @Value("${web.url.prefix}") String prefix, StudentService studentService, AttentionServiceImpl attentionService, EssayServiceImpl essayService, QuestionServiceImpl questionService, VideoServiceImpl videoService, AnswerServiceImpl answerService, JobServiceImpl jobService, BriefReviewService briefReviewService, CompanyCURDService companyCURDService
            , InfluenceService influenceService) {
        this.userService = userService;
        this.url = prefix + "/users/";
        this.studentService = studentService;
        this.attentionService = attentionService;
        this.essayService = essayService;
        this.questionService = questionService;
        this.videoService = videoService;
        this.answerService = answerService;
        this.jobService = jobService;
        this.briefReviewService = briefReviewService;
        this.companyCURDService = companyCURDService;
        this.influenceService = influenceService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            return get(id);
        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/{id}/influence")
    public ResponseEntity<?> getMyInfluence(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ForbiddenException {
        if (user.getId().equals(id)) {
            Integer influence = influenceService.getUserInfluence(id);
            return ResponseEntity.ok(new Response(new InfluenceDTO(influence), new StatusDTO(200, "success")));
        } else {
            throw new ForbiddenException();
        }
    }

    @PatchMapping("/{id}")
    @ResponseBodyDTO(UserDTO.class)
    public ResponseEntity<?> modifyUser(@PathVariable Integer id, @AuthenticationPrincipal User user, @RequestBody @Valid ModifiedUserDTO body) throws ClientException, ForbiddenException, NotFoundException {
        // Role control
        if (id.equals(user.getId())) {
            if (body.getGender() != null) {
                try {
                    Gender gender = Gender.valueOf(body.getGender());
                    user.setGender(gender);
                } catch (IllegalArgumentException ex) {
                    throw new ClientException("gender must be MALE, FEMALE or OTHER");
                }
            }
            user.setFirstName(body.getFirstName() == null ? user.getFirstName() : body.getFirstName());
            user.setLastName(body.getLastName() == null ? user.getLastName() : body.getLastName());
            user.setAvatarUrl(body.getAvatarUrl() == null ? user.getAvatarUrl() : body.getAvatarUrl());
            User resultUser = userService.update(user);
            return ResponseEntity.ok(new Response(resultUser));
        } else {
            throw new ForbiddenException(InternalStatusCode.ACCESS_DENY);
        }
    }

    @Override
    protected DomainCRUDService<User, Integer> getService() {
        return this.userService;
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id);
    }

}
