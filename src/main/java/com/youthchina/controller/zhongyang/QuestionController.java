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

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Integer id) throws NotFoundException {
        return get(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createQuestionInfo(@RequestBody QuestionDTO questionDTO, @AuthenticationPrincipal User user) {
        questionDTO.setCreator(user);
        return add(questionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestionInfo(@RequestBody QuestionDTO questionDTO) throws NotFoundException {
        return update(questionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionInfo(@PathVariable Integer id) throws NotFoundException {
        //System.out.println("delete processing!!!");
        return delete(id);
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<?> getAnswers(@PathVariable Integer id) throws NotFoundException{
        QuestionDTO questionDTO = getDto(id);
        return ResponseEntity.ok(new Response(questionDTO.getAnswers()));
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<?> sendInvites(@RequestBody Integer id, List<Integer> userIds, @AuthenticationPrincipal User user) throws NotFoundException {
        communityQAService.invitUsersToAnswer(user.getId(), id, userIds);
        return ResponseEntity.ok(new Response());
    }

    @PostMapping("/{questionId}/invite/{userId}")
    public ResponseEntity<?> sendInvite(@RequestBody Integer questionId, Integer userId,@AuthenticationPrincipal User user) throws NotFoundException {
        List<Integer> list = new ArrayList<>();
        list.add(userId);
        communityQAService.invitUsersToAnswer(user.getId(), questionId, list);
        return ResponseEntity.ok(new Response());
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<?> followUp(@RequestBody Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        communityQAService.attentionQuestion(id, user.getId());
        return ResponseEntity.ok(new Response());
    }


    private QuestionDTO getDto(Integer id) throws NotFoundException {
        return this.DomainToDto(this.getService().get(id));
    }
}
