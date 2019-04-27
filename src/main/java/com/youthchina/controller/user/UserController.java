package com.youthchina.controller.user;

import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private UserService userService;
    private String url;

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
    @GetMapping("/{id}/my")
    public ResponseEntity<?> getMy(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {

            Map<String, Object> result = new HashMap<>();

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
            result.put("companies", companyResponseDTOList);

            /**发布的职位*/
            List<Job> jobsOwnedByUserId = jobService.getJobByUserId(id);
            List<JobResponseDTO> jobResponseDTOList = new JobResponseDTO().convertToDTO(jobsOwnedByUserId);
            result.put("jobs", jobResponseDTOList);

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
            result.put("questions", questionResponseDTOList);

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
            result.put("answers", answerResponseDTOList);

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
            result.put("articles", essayResponseDTOList);

            return ResponseEntity.ok(new Response(result));

        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/{id}/influence")
    public ResponseEntity<?> getMyInfluence(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ForbiddenException {
        if (user.getId().equals(id)) {
            Integer influence = influenceService.getUserInfluence(id);
            return ResponseEntity.ok(new Response(influence, new StatusDTO(200, "success")));
        }else {
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
