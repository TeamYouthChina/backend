package com.youthchina.controller.user;

import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.CompCollectResponseDTO;
import com.youthchina.dto.applicant.JobCollectResponseDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.jinhao.*;
import com.youthchina.service.qingyang.JobServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserService;
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

    @Autowired
    private StudentService studentService;
    @Autowired
    private AttentionServiceImpl attentionService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private JobServiceImpl jobService;
    @Autowired
    private BriefReviewService briefReviewService;

    @Autowired
    public UserController(UserService userService, @Value("${web.url.prefix}") String prefix) {
        this.userService = userService;
        this.url = prefix + "/users/";
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
        if (user.getId() != user_id) {
            throw new ForbiddenException();
        }
        switch (type) {
            case "job": {
                List<JobCollect> jobCollects = studentService.getJobCollect(user_id);
                List<JobCollectResponseDTO> jobCollectResponseDTOS = new ArrayList<>();
                if (jobCollects != null) {
                    for (JobCollect jobCollect : jobCollects) {
                        JobCollectResponseDTO jobCollectResponseDTO = new JobCollectResponseDTO(jobCollect);
                        jobCollectResponseDTOS.add(jobCollectResponseDTO);
                    }

                }
                List<JobCollectResponseDTO> result = jobCollectResponseDTOS.subList(pageRequest.getStart(), Math.min(pageRequest.getEnd() + 1, jobCollectResponseDTOS.size()));
                ListResponse listResponse = new ListResponse(pageRequest, jobCollects.size(), result);
                return ResponseEntity.ok(listResponse);

            }

            case "company": {
                List<CompCollect> compCollects = studentService.getCompCollect(user_id);
                List<CompCollectResponseDTO> compCollectResponseDTOS = new ArrayList<>();
                if (compCollects != null) {
                    for (CompCollect compCollect : compCollects) {
                        CompCollectResponseDTO compCollectResponseDTO = new CompCollectResponseDTO(compCollect);
                        compCollectResponseDTOS.add(compCollectResponseDTO);
                    }

                }
                List<CompCollectResponseDTO> result = compCollectResponseDTOS.subList(pageRequest.getStart(), Math.min(pageRequest.getEnd() + 1, compCollectResponseDTOS.size()));
                ListResponse listResponse = new ListResponse(pageRequest, compCollects.size(), result);
                return ResponseEntity.ok(listResponse);

            }
            case "article": {
                List<EssayResponseDTO> essayResponseDTOS = new ArrayList<>();
                List<Integer> result = attentionService.getAllIdsOfAttention(new ComEssay(), user_id);
                if (result != null) {
                    for (Integer id : result) {
                        ComEssay comEssay = essayService.getEssay(id);
                        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
                        essayResponseDTOS.add(essayResponseDTO);

                    }


                }
                List<EssayResponseDTO> results = essayResponseDTOS.subList(pageRequest.getStart(), Math.min(pageRequest.getEnd() + 1, result.size()));
                ListResponse listResponse = new ListResponse(pageRequest, result.size(), results);
                return ResponseEntity.ok(listResponse);

            }
            case "briefReview": {
                List<BriefReviewResponseDTO> briefReviewResponseDTOS = new ArrayList<>();
                List<Integer> result = attentionService.getAllIdsOfAttention(new BriefReview(), user_id);
                if (result != null) {
                    for (Integer id : result) {
                        BriefReview briefReview = briefReviewService.get(id);
                        BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO(briefReview);
                        briefReviewResponseDTOS.add(briefReviewResponseDTO);
                    }

                }
                List<BriefReviewResponseDTO> results = briefReviewResponseDTOS.subList(pageRequest.getStart(), Math.min(pageRequest.getEnd() + 1, result.size()));
                ListResponse listResponse = new ListResponse(pageRequest, result.size(), results);
                return ResponseEntity.ok(listResponse);

            }
            case "question": {
                List<QuestionResponseDTO> questionResponseDTOS = new ArrayList<>();
                List<Integer> result = attentionService.getAllIdsOfAttention(new Question(), user_id);
                if (result != null) {
                    for (Integer id : result) {
                        Question question = questionService.get(id);
                        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(question);
                        questionResponseDTOS.add(questionResponseDTO);
                    }

                }
                List<QuestionResponseDTO> results = questionResponseDTOS.subList(pageRequest.getStart(), Math.min(pageRequest.getEnd() + 1, result.size()));
                ListResponse listResponse = new ListResponse(pageRequest, result.size(), results);
                return ResponseEntity.ok(listResponse);
            }
            case "answer": {
                List<SimpleAnswerResponseDTO> answerResponseDTOS = new ArrayList<>();
                List<Integer> result = attentionService.getAllIdsOfAttention(new Answer(), user_id);
                if (result != null) {
                    for (Integer id : result) {
                        Answer answer = answerService.get(id);
                        SimpleAnswerResponseDTO answerResponseDTO = new SimpleAnswerResponseDTO(answer);
                        answerResponseDTOS.add(answerResponseDTO);
                    }
                }
                List<SimpleAnswerResponseDTO> results = answerResponseDTOS.subList(pageRequest.getStart(), Math.min(pageRequest.getEnd() + 1, result.size()));
                ListResponse listResponse = new ListResponse(pageRequest, result.size(), results);
                return ResponseEntity.ok(listResponse);

            }
            default:
                throw new NotFoundException(404, 404, "do not have this type");


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
