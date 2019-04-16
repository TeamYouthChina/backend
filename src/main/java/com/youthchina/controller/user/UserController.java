package com.youthchina.controller.user;

import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
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

    @Autowired
    public UserController(UserService userService, @Value("${web.url.prefix}") String prefix, StudentService studentService, AttentionServiceImpl attentionService, EssayServiceImpl essayService, QuestionServiceImpl questionService, VideoServiceImpl videoService, AnswerServiceImpl answerService, JobServiceImpl jobService, BriefReviewService briefReviewService, CompanyCURDService companyCURDService) {
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
     * @Description: 需要添加分页
     * @Param: [user_id, type]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: Qinghong Wang
     * @Date: 2019/4/4
     */

    @GetMapping("/{id}/attentions")
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


    private DomainCRUDService getServiceByType(String type) throws NotFoundException {
        switch (type) {
            case "job":
                return this.jobService;
            case "company":
                return this.companyCURDService;
            case "answer":
                return this.answerService;
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
                    companyResponseDTO.convertToDTO(compCollect.getCompany());
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
            //TestTest
            System.out.println("comEssayList.size() : " + comEssayList.size());
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


    @Override
    protected DomainCRUDService<User, Integer> getService() {
        return this.userService;
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id);
    }

}
