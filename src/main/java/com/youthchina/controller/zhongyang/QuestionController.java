package com.youthchina.controller.zhongyang;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.RequestSimpleAnswerDTO;
import com.youthchina.dto.community.answer.SimpleAnswerDTO;
import com.youthchina.dto.community.question.QuestionBasicDTO;
import com.youthchina.dto.community.question.QuestionDTO;
import com.youthchina.dto.community.question.QuestionRequestDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.jinhao.AnswerService;
import com.youthchina.service.jinhao.CommunityQAService;
import com.youthchina.service.jinhao.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhongyangwu on 1/2/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/questions/**")
public class QuestionController {
    private String url;
    private QuestionService questionService;
    private AnswerService answerService;

    @Autowired
    public QuestionController(QuestionService questionService, @Value("${web.url.prefix}") String prefix) {
        this.questionService = questionService;
        this.url = prefix + "/questions/";
    }

    protected QuestionResponseDTO DomainToDto(Question domain) { return new QuestionResponseDTO(domain); }

    protected Question DtoToDomain(QuestionRequestDTO questionDTO) {
        return new Question(questionDTO);
    }

    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    /*@GetMapping("/**")
    public ResponseEntity<?> getQuestions(@RequestParam(value = "Company") String company,@RequestParam(value = "Job") String job) throws NotFoundException {
        if(!company.equals("")){
            List<Question> qlists=  questionService.searchQuestionByTitleOrCompanyName(company);
            if(qlists.size() != 0){
                List<QuestionDTO> qdlist2 = new ArrayList<>();
                for(int i = 0; i<qlists.size(); i++){
                    QuestionResponseDTO questionResponseDTO = DomainToDto(qlists.get(i));
                    qdlist2.add(questionResponseDTO);
                }
                HashMap<String,Object> map3 = new HashMap<>();
                map3.put("questions", qdlist2);
                return ResponseEntity.ok(new Response(map3));
            }
        }else if(!job.equals("")){
            List<Question> qlists2=  questionService.searchQuestionByTitleOrCompanyName(job);
            if (qlists2.size() != 0){
                List<QuestionDTO> qdlist = new ArrayList<>();
                for(int i = 0; i<qlists2.size(); i++){
                    QuestionResponseDTO questionResponseDTO = DomainToDto(qlists2.get(i));
                    qdlist.add(questionResponseDTO);
                }
                HashMap<String,Object> map3 = new HashMap<>();
                map3.put("questions", qdlist);
                return ResponseEntity.ok(new Response(map3));
            }
        }
        throw new NotFoundException(4000,404,"not found questions");
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Integer id) throws NotFoundException {
        QuestionResponseDTO responseDTO = DomainToDto(questionService.get(id));
        return ResponseEntity.ok(new Response(responseDTO));
    }

    @PostMapping("/**")
    public ResponseEntity<?> createQuestionInfo(@RequestBody QuestionRequestDTO requestQuestionDTO, @AuthenticationPrincipal User user) {
        Question question = new Question(requestQuestionDTO);
        question.setUser(user);
        QuestionResponseDTO questionDTO = new QuestionResponseDTO(question);
        questionDTO.setCreator(new UserDTO(user));
        questionService.add(question);
        return ResponseEntity.ok(new Response(questionDTO));
    }

    @PutMapping("/{id}/**")
    public ResponseEntity<?> updateQuestionInfo(@PathVariable Integer id, @RequestBody QuestionRequestDTO requestQuestionDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Question question = new Question(requestQuestionDTO);
        question.setUser(user);
        question.setId(id);
        //QuestionResponseDTO questionDTO = new QuestionResponseDTO(question);
        //questionDTO.setId(id);
        questionService.update(question);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"updated success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionInfo(@PathVariable Integer id) throws NotFoundException {
        questionService.delete(id);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"delete success")));
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<?> getAnswers(@PathVariable Integer id) throws NotFoundException {
        QuestionResponseDTO responseDTO = DomainToDto(questionService.get(id));
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("answers", responseDTO.getAnswers());
        return ResponseEntity.ok(new Response(map1));
    }

    /*@PutMapping("/{id}/invite/**")
    public ResponseEntity<?> sendInvites(@PathVariable Integer id, @RequestBody List<Integer> userIds, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("invite users to answer");
        questionService.invitUsersToAnswer(user.getId(), id, userIds);
        return ResponseEntity.ok(new Response());
    }

    @PutMapping("/{questionId}/invite/{userId}")
    public ResponseEntity<?> sendInvite(@PathVariable Integer questionId, @PathVariable Integer userId, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("invite user 1 to answer");
        List<Integer> list = new ArrayList<>();
        list.add(userId);
        questionService.invitUsersToAnswer(user.getId(), questionId, list);
        return ResponseEntity.ok(new Response());
    }*/

    @PutMapping("/{id}/attention")
    public ResponseEntity<?> followUp (@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("add attention");
        questionService.attentionQuestion(id, user.getId());
        return ResponseEntity.ok(new Response());
    }

    @PostMapping("/{id}/answers")
    public ResponseEntity<?> addAnswers(@PathVariable Integer id, @RequestBody RequestSimpleAnswerDTO simpleAnswerDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("add answeimport com.youthchina.service.DomainCRUDService; ");
        Answer answer = new Answer(simpleAnswerDTO);
        answer.setUserId(user.getId());
        answer.setPubTime(new Timestamp(System.currentTimeMillis()));
        answer.setEditTime(new Timestamp(System.currentTimeMillis()));

        SimpleAnswerDTO returnSimpleAnswer = new SimpleAnswerDTO(questionService.addAnswer(answer,id,1));
        if (returnSimpleAnswer!=null)
            return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(400,"fail")));
    }
}
