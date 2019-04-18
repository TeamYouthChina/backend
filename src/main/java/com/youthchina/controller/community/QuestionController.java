package com.youthchina.controller.community;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.AnswerBasicDTO;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.question.QuestionRequestDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.application.CompanyCURDServiceImpl;
import com.youthchina.service.application.JobServiceImpl;
import com.youthchina.service.community.AnswerService;
import com.youthchina.service.community.AttentionServiceImpl;
import com.youthchina.service.community.EvaluateService;
import com.youthchina.service.community.QuestionService;
import com.youthchina.util.dictionary.AttentionTargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhongyangwu on 1/2/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/questions/**")
public class QuestionController {
    private String url;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private CompanyCURDServiceImpl companyCURDService;

    @Autowired
    private AttentionServiceImpl attentionService;

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    public QuestionController(QuestionService questionService, @Value("${web.url.prefix}") String prefix) {
        this.questionService = questionService;
        this.url = prefix + "/questions/";
    }

    protected QuestionResponseDTO DomainToDto(Question domain) {
        return new QuestionResponseDTO(domain);
    }

    protected Question DtoToDomain(QuestionRequestDTO questionDTO) {
        return new Question(questionDTO);
    }

    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    @GetMapping("/**")
    public ResponseEntity<?> getQuestions(@RequestParam(value = "Company") String company, @RequestParam(value = "Job") String job, PageRequest pageRequest, @AuthenticationPrincipal User user) throws NotFoundException {
        List<QuestionResponseDTO> questionResponseDTOArrayList = new ArrayList<>();
        if (!job.equals("")) {
            List<Job> jobs = jobService.getJobByMore(null, job, null, null, null, null, null, null, null, null, null, null, null);
            List<Question> questionList1 = questionService.get(2, jobs.get(0).getJobId());
            Iterator it = questionList1.iterator();
            while (it.hasNext()) {
                Question question = (Question) it.next();
                QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(question);
                Iterator iterator = questionResponseDTO.getAnswers().iterator();
                while (iterator.hasNext()) {
                    AnswerBasicDTO answerBasicDTO = (AnswerBasicDTO) iterator.next();
                    Answer answer = new Answer();
                    answer.setId(answerBasicDTO.getId());
                }
                questionResponseDTOArrayList.add(questionResponseDTO);
            }
            if (questionResponseDTOArrayList.size() != 0) {
                ListResponse listResponse = new ListResponse(pageRequest, questionResponseDTOArrayList);
                return ResponseEntity.ok(listResponse);
            }
        } else if (!company.equals("")) {
            List<Company> companies = companyCURDService.getByName(company);
            List<Question> questionList = questionService.get(1, companies.get(0).getCompanyId());
            Iterator it = questionList.iterator();
            while (it.hasNext()) {
                Question question = (Question) it.next();
                QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(question);
                Iterator iterator = questionResponseDTO.getAnswers().iterator();
                while (iterator.hasNext()) {
                    AnswerBasicDTO answerBasicDTO = (AnswerBasicDTO) iterator.next();
                    Answer answer = new Answer();
                    answer.setId(answerBasicDTO.getId());

                }
                questionResponseDTOArrayList.add(questionResponseDTO);
            }
            if (questionResponseDTOArrayList.size() != 0) {
                ListResponse listResponse = new ListResponse(pageRequest, questionResponseDTOArrayList);
                return ResponseEntity.ok(listResponse);
            }
        }
        throw new NotFoundException(4000, 404, "not found questions");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Question question = questionService.get(id);
        QuestionResponseDTO responseDTO = DomainToDto(question);
        responseDTO.setAttention(attentionService.isAttention(AttentionTargetType.QUESTION, question.getId(), user.getId()));
        Iterator iterator = responseDTO.getAnswers().iterator();
        while (iterator.hasNext()) {
            AnswerBasicDTO answerBasicDTO = (AnswerBasicDTO) iterator.next();
            Answer answer = new Answer();
            answer.setId(answerBasicDTO.getId());
        }
        return ResponseEntity.ok(new Response(responseDTO));
    }

    @PostMapping("/**")
    public ResponseEntity<?> createQuestionInfo(@RequestBody QuestionRequestDTO questionRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Question question = new Question(questionRequestDTO);
        question.setUser(user);
        Question questionReturn = questionService.add(question);
        QuestionResponseDTO questionDTO = new QuestionResponseDTO(questionReturn);

        return ResponseEntity.ok(new Response(questionDTO));
    }

    @PutMapping("/{id}/**")
    public ResponseEntity<?> updateQuestionInfo(@PathVariable Integer id, @RequestBody QuestionRequestDTO requestQuestionDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Question question = new Question(requestQuestionDTO);
        question.setUser(user);
        question.setId(id);
        Question questionresult = questionService.update(question);
        if (questionresult != null) {
            return ResponseEntity.ok(new Response(new StatusDTO(204, "updated success")));
        } else {
            return ResponseEntity.ok(new Response(new StatusDTO(403, "updated failed")));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionInfo(@PathVariable Integer id) throws NotFoundException {
        questionService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204, "delete success")));
    }

    @GetMapping("/{id}/answers")
    @ResponseBodyDTO(SimpleAnswerResponseDTO.class)
    public ResponseEntity<?> getAnswers(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException {
        List<Answer> answers = this.answerService.getAnswers(id);
        Question question = this.questionService.getBasicQuestion(id);
        for (Answer answer : answers) {
            answer.setQuestion(question);
        }
        List<Answer> pagedAnswers = answers.subList(pageRequest.getStart(), Math.min(pageRequest.getEnd() + 1, answers.size()));
        ListResponse listResponse = new ListResponse(pageRequest, answers.size(), pagedAnswers);
        return ResponseEntity.ok(listResponse);
    }

    @PostMapping("/{id}/answers")
    @ResponseBodyDTO(SimpleAnswerResponseDTO.class)
    public ResponseEntity<?> addAnswers(@PathVariable Integer id, @RequestBody SimpleAnswerRequestDTO simpleAnswerDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Answer answer = new Answer(simpleAnswerDTO);
        answer.setUser(user);
        answer.setTargetId(id);
        answer.setTargetType(0);
        Answer createdAnswer = answerService.add(answer);
        return ResponseEntity.ok(new Response(createdAnswer, new StatusDTO(200, "success")));
    }
}