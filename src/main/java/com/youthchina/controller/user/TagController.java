package com.youthchina.controller.user;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Label;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.Role;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.tag.TagRequestDTO;
import com.youthchina.dto.tag.TagResponseDTO;
import com.youthchina.exception.zhongyang.exception.ClientException;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.exception.zhongyang.exception.InternalStatusCode;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.application.CompanyCURDService;
import com.youthchina.service.application.JobService;
import com.youthchina.service.community.BriefReviewService;
import com.youthchina.service.community.EssayService;
import com.youthchina.service.community.QuestionService;
import com.youthchina.service.recommendation.RecommendServiceImpl;
import com.youthchina.service.user.UserService;
import com.youthchina.util.dictionary.TagTargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianjian on 2019/5/22.
 */
@RestController
@RequestMapping("${web.url.prefix}/labels/**")
public class TagController {
    @Autowired
    private RecommendServiceImpl recommendService;

    @Resource
    BriefReviewService briefReviewService;

    @Resource
    EssayService essayService;

    @Resource
    QuestionService questionService;

    @Resource
    JobService jobService;

    @Resource
    CompanyCURDService companyCURDService;

    @Resource
    UserService userService;


    @GetMapping("/{type}/{id}")
    public ResponseEntity getTags(@PathVariable Integer id, @PathVariable Integer type) {
        List<Label> labelList = recommendService.getLabels(type, id);
        List<TagResponseDTO> tagResponseDTOS = new ArrayList<>();
        Iterator iterator = labelList.iterator();
        while (iterator.hasNext()) {
            Label label = (Label) iterator.next();
            TagResponseDTO tagResponseDTO = new TagResponseDTO(label);
            tagResponseDTOS.add(tagResponseDTO);
        }
        return ResponseEntity.ok(new Response(tagResponseDTOS, new StatusDTO(201, "success")));
    }

    @PostMapping
    public ResponseEntity addTags(@RequestBody TagRequestDTO tagRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException, ForbiddenException, ClientException {
        //role control
        if (this.allowAccess(tagRequestDTO, user)) {
            throw new ForbiddenException(InternalStatusCode.ACCESS_DENY);
        }
        if (!recommendService.isTagExist(tagRequestDTO.getLabel_code(), tagRequestDTO.getTarget_type(), tagRequestDTO.getTarget_id())) {
            recommendService.addTag(tagRequestDTO.getLabel_code(), tagRequestDTO.getTarget_type(), tagRequestDTO.getTarget_id());
            return ResponseEntity.ok(new Response(new StatusDTO(2010, "success")));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response(new StatusDTO(4090, "the tag is already exist")));
        }
    }

    private boolean allowAccess(@Nullable TagRequestDTO tagRequestDTO, @Nonnull User user) throws NotFoundException, ClientException {
        if (tagRequestDTO == null || tagRequestDTO.getTarget_id() == null) {
            throw new ClientException("no payload is specific, payload cannot interpreted or no valid target id is provided");
        }
        //ADMIN and ROOT can operate on any resource
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(Role.ROOT.getAuthority()) || authority.getAuthority().equals(Role.ADMIN.getAuthority())) {
                return true;
            }
        }
        //If not ADMIN or ROOT
        switch (tagRequestDTO.getTarget_type()) {
            case TagTargetType.QUESTION: {
                Question question = questionService.get(tagRequestDTO.getTarget_id());
                return question != null && question.getUser().equals(user);
            }
            case TagTargetType.ARTICLE: {
                ComEssay comEssay = essayService.get(tagRequestDTO.getTarget_id());
                return comEssay != null && comEssay.getAuthor().equals(user);
            }
            case TagTargetType.EDITORIAL: {
                BriefReview briefReview = briefReviewService.get(tagRequestDTO.getTarget_id());
                return briefReview != null && briefReview.getUser().equals(user);
            }
            case TagTargetType.COMPANY: {
                //Only ADMIN or ROOT can operate on it.
                return false;
            }
            case TagTargetType.JOB: {
                Job job = jobService.get(tagRequestDTO.getTarget_id());
                return job != null && job.getUserId().equals(user.getId());
            }
            case TagTargetType.USER: {
                return tagRequestDTO.getTarget_id().equals(user.getId());
            }
            default:
                throw new ClientException("invalid label type");
        }
    }

    @DeleteMapping("/{labelCode}/{targetType}/{targetId}")
    public ResponseEntity deleteTags(@PathVariable String labelCode, @PathVariable Integer targetType, @PathVariable Integer targetId) throws NotFoundException {
        recommendService.deleteTag(labelCode, targetType, targetId);
        return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }
}
