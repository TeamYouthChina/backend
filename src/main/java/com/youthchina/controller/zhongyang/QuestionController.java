package com.youthchina.controller.zhongyang;

import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.community.QuestionDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.jinhao.communityQA.CommunityQAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
        System.out.println("enter getquestions");
        if(company != ""){
            List<Question> qlists=  communityQAService.searchQuestionByTitleOrCompanyName(company);
            if(qlists.size() != 0){
                return ResponseEntity.ok(new Response(qlists));
            }
        }else if(job != ""){
            List<Question> qlists2=  communityQAService.searchQuestionByTitleOrCompanyName(job);
            if (qlists2.size() != 0){
                return ResponseEntity.ok(new Response(qlists2));
            }
        }
        throw new NotFoundException(4000,404,"not found questions");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Integer id) throws NotFoundException {
        System.out.println("get question");
        return get(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createQuestionInfo(@RequestBody QuestionDTO questionDTO, @AuthenticationPrincipal User user) {
        questionDTO.setCreator(user);
       // questionDTO.setAbbreviation(questionDTO.getRichTextDTO().getBraftEditorRaw());
        return add(questionDTO);
    }

    @PutMapping("/{id}/**")
    public ResponseEntity<?> updateQuestionInfo(@RequestBody QuestionDTO questionDTO, @PathVariable Integer id) throws NotFoundException {
        questionDTO.setId(id);
        return update(questionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionInfo(@PathVariable Integer id) throws NotFoundException {
        //System.out.println("delete processing!!!");
        return delete(id);
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<?> getAnswers(@PathVariable Integer id) throws NotFoundException{
        System.out.println("get answers");
        QuestionDTO questionDTO = getDto(id);
        return ResponseEntity.ok(new Response(questionDTO.getAnswers()));
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
}
