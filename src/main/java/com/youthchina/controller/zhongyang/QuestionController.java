package com.youthchina.controller.zhongyang;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.question.QuestionBasicDTO;
import com.youthchina.dto.community.question.QuestionDTO;
import com.youthchina.dto.community.question.RequestQuestionDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.jinhao.CommunityQAService;
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
public class QuestionController extends DomainCRUDController<QuestionDTO, Question, Integer>{
    private String url;
    private CommunityQAService communityQAService;

    @Autowired
    public QuestionController(CommunityQAService communityQAService, @Value("${web.url.prefix}") String prefix) {
        this.communityQAService = communityQAService;
        this.url = prefix + "/questions/";
    }

    @Override
    protected DomainCRUDService<Question, Integer> getService() {
        return this.communityQAService;
    }

    @Override
    protected QuestionDTO DomainToDto(Question domain) {
        return new QuestionDTO(domain);
    }

    @Override
    protected Question DtoToDomain(QuestionDTO questionDTO) {
        return new Question(questionDTO);
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    @GetMapping("/**")
    public ResponseEntity<?> getQuestions(@RequestParam(value = "Company") String company,@RequestParam(value = "Job") String job) throws NotFoundException {
        if(!company.equals("")){
            List<Question> qlists=  communityQAService.searchQuestionByTitleOrCompanyName(company);
            if(qlists.size() != 0){
                List<QuestionDTO> qdlist2 = new ArrayList<>();
                for(int i = 0; i<qlists.size(); i++){
                    QuestionDTO questionDTO = DomainToDto(qlists.get(i));
                    qdlist2.add(questionDTO);
                }
                HashMap<String,Object> map3 = new HashMap<>();
                map3.put("questions", qdlist2);
                return ResponseEntity.ok(new Response(map3));
            }
        }else if(!job.equals("")){
            List<Question> qlists2=  communityQAService.searchQuestionByTitleOrCompanyName(job);
            if (qlists2.size() != 0){
                List<QuestionDTO> qdlist = new ArrayList<>();
                for(int i = 0; i<qlists2.size(); i++){
                    QuestionDTO questionDTO = DomainToDto(qlists2.get(i));
                    qdlist.add(questionDTO);
                }
                HashMap<String,Object> map3 = new HashMap<>();
                map3.put("questions", qdlist);
                return ResponseEntity.ok(new Response(map3));
            }
        }
        throw new NotFoundException(4000,404,"not found questions");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Integer id) throws NotFoundException {
        System.out.println("get question");
        return get(id);
    }

    @PostMapping("/**")
    public ResponseEntity<?> createQuestionInfo(@RequestBody RequestQuestionDTO requestQuestionDTO, @AuthenticationPrincipal User user) {
        Question question = new Question(requestQuestionDTO);
        question.setQues_user(user);

        QuestionDTO questionDTO = new QuestionDTO(question);
        questionDTO.setCreator(new UserDTO(user));

        return add(questionDTO);
    }

    @PutMapping("/{id}/**")
    public ResponseEntity<?> updateQuestionInfo(@PathVariable Integer id, @RequestBody RequestQuestionDTO requestQuestionDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Question question = new Question(requestQuestionDTO);
        question.setQues_user(user);
        QuestionDTO questionDTO = new QuestionDTO(question);
        questionDTO.setId(id);
        update(questionDTO);
        return ResponseEntity.ok(new Response(new StatusDTO(204,"updated success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<?> getAnswers(@PathVariable Integer id) throws NotFoundException {
        System.out.println("get answers");
        QuestionDTO questionDTO = getDto(id);
        Question question = communityQAService.get(id);
        QuestionBasicDTO questionBasicDTO = new QuestionBasicDTO(question);
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("answers", questionDTO.getAnswers());

        return ResponseEntity.ok(new Response(map1));
    }

    @PutMapping("/{id}/invite/**")
    public ResponseEntity<?> sendInvites(@PathVariable Integer id, @RequestBody List<Integer> userIds, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("invite users to answer");
        communityQAService.invitUsersToAnswer(user.getId(), id, userIds);
        return ResponseEntity.ok(new Response());
    }

    @PutMapping("/{questionId}/invite/{userId}")
    public ResponseEntity<?> sendInvite(@PathVariable Integer questionId, @PathVariable Integer userId, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("invite user 1 to answer");
        List<Integer> list = new ArrayList<>();
        list.add(userId);
        communityQAService.invitUsersToAnswer(user.getId(), questionId, list);
        return ResponseEntity.ok(new Response());
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity<?> followUp (@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("add attention");
        communityQAService.attentionQuestion(id, user.getId());
        return ResponseEntity.ok(new Response());
    }


    private QuestionDTO getDto(Integer id) throws NotFoundException {
        return this.DomainToDto(this.getService().get(id));
    }

    @PostMapping("/{id}/answers")
    public ResponseEntity<?> addAnswers(@PathVariable Integer id, @RequestBody SimpleAnswerRequestDTO simpleAnswerDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("add answers");
        Answer answer = new Answer(simpleAnswerDTO);
        answer.setUser_id(user.getId());
        answer.setAnswer_pub_time(new Timestamp(System.currentTimeMillis()));
        answer.setAnswer_edit_time(new Timestamp(System.currentTimeMillis()));

        SimpleAnswerResponseDTO returnSimpleAnswer = new SimpleAnswerResponseDTO(communityQAService.addAnswer(answer,id,1));
        if (returnSimpleAnswer!=null)
            return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnSimpleAnswer, new StatusDTO(400,"fail")));
    }

}
