package com.youthchina.controller.user;

import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.ClientException;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.application.CompanyCURDService;
import com.youthchina.service.application.JobService;
import com.youthchina.service.application.JobServiceImpl;
import com.youthchina.service.community.*;
import com.youthchina.service.user.StudentService;
import com.youthchina.service.user.UserService;
import com.youthchina.util.dictionary.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * 返回我的 收藏公司 发布职位
     *
     * @param id
     * @param user
     * @return
     * @throws ForbiddenException
     * @throws NotFoundException
     */
    @GetMapping("/:{id}/my")
    public ResponseEntity<?> getMy(@RequestParam(value = "type", defaultValue = "all") String type, @PathVariable Integer id, @AuthenticationPrincipal User user, PageRequest pageRequest) throws ForbiddenException, NotFoundException, ClientException {
        if (user.getId().equals(id)) {
            Map<String, Object> result = new HashMap<>();
            switch (type) {
                case SearchType.ARTICLE: {
                    /**我的Essay*/
                    List<ComEssay> comEssayList = essayService.getAllEssayByUserId(id);
                    List<EssayResponseDTO> essayResponseDTOList = new ArrayList<>();
                    if (comEssayList != null && comEssayList.size() > 0) {
                        for (ComEssay comEssay : comEssayList) {
                            EssayResponseDTO essayResponseDTO = new EssayResponseDTO();
                            essayResponseDTO.convertToDTO(comEssay);
                            essayResponseDTOList.add(essayResponseDTO);
                        }
                    }
                    return ResponseEntity.ok(new ListResponse(pageRequest, essayResponseDTOList));
                    //result.put("articles", new ListResponse(pageRequest, essayResponseDTOList));
                }
                case SearchType.QUESTION: {
                    /**我的问题*/
                    List<Question> questionList = questionService.getMyQuestion(id);
                    List<QuestionResponseDTO> questionResponseDTOList = new ArrayList<>();
                    //TestTest
                    //System.out.println("questionList.size() : " + questionList.size());//49
                    if (questionList != null && questionList.size() > 0) {
                        for (Question question : questionList) {
                            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
                            questionResponseDTO.convertToDTO(question);
                            questionResponseDTOList.add(questionResponseDTO);
                        }
                    }
                    return ResponseEntity.ok(new ListResponse(pageRequest, questionResponseDTOList));
                    //result.put("questions", new ListResponse(pageRequest, questionResponseDTOList));
                    //break;
                }
                case SearchType.ANSWER: {
                    /**我的回答*/
                    List<Answer> answerList = answerService.getMyAnswers(id);
                    List<SimpleAnswerResponseDTO> answerResponseDTOList = new ArrayList<>();
                    if (answerList != null && answerList.size() > 0) {
                        for (Answer answer : answerList) {
                            SimpleAnswerResponseDTO answerResponseDTO = new SimpleAnswerResponseDTO();
                            answerResponseDTO.convertToDTO(answer);
                            answerResponseDTOList.add(answerResponseDTO);
                        }
                    }
                    return ResponseEntity.ok(new ListResponse(pageRequest, answerResponseDTOList));
                }
                case SearchType.JOB: {
                    /**发布的职位*/
                    List<Job> jobsOwnedByUserId = jobService.getJobByUserId(id);
                    List<JobResponseDTO> jobResponseDTOList = new JobResponseDTO().convertToDTO(jobsOwnedByUserId);
                    return ResponseEntity.ok(new ListResponse(pageRequest, jobResponseDTOList));
                    //break;
                }
                case SearchType.COMPANY: {
                    /**收藏的公司*/
                    List<CompCollect> compCollects = studentService.getCompCollect(id);
                    List<CompanyResponseDTO> companyResponseDTOList = new ArrayList<>();
                    if (compCollects != null && compCollects.size() > 0) {
                        for (CompCollect compCollect : compCollects) {
                            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
                            Company company = compCollect.getCompany();
                            company.setCollected(companyCURDService.isCollected(company.getId(), user.getId()));
                            companyResponseDTO.convertToDTO(company);
                            companyResponseDTOList.add(companyResponseDTO);
                        }
                    }
                    return ResponseEntity.ok(new ListResponse(pageRequest, companyResponseDTOList));
                    //break;
                }
                default:
                    throw new ClientException("cannot get target type");
            }
            //return ResponseEntity.ok(new Response(result));

        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/{id}/influence")
    public ResponseEntity<?> getMyInfluence(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ForbiddenException {
        if (user.getId().equals(id)) {
            Integer influence = influenceService.getUserInfluence(id);
            return ResponseEntity.ok(new Response(influence, new StatusDTO(200, "success")));
        } else {
            throw new ForbiddenException();
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
