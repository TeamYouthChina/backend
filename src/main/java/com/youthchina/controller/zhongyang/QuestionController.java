package com.youthchina.controller.zhongyang;

import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.QuestionDTO;
import com.youthchina.dto.community.SimpleAnswerDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.jinhao.communityQA.CommunityQAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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
    public ResponseEntity<?> createQuestionInfo(@RequestBody QuestionDTO questionDTO) {
        return add(questionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestionInfo(@RequestBody QuestionDTO questionDTO) throws NotFoundException {
        return update(questionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<?> getAnswers(@PathVariable Integer id) throws NotFoundException{
        QuestionDTO questionDTO = getDto(id);
        return ResponseEntity.ok(new Response(questionDTO.getAnswers()));
    }
/*
    @PostMapping("/{id}/invite")
    public ResponseEntity<?> createInviteInfo(@RequestBody Integer) {
        return ;
    }
*/

    private QuestionDTO getDto(Integer id) throws NotFoundException {
        return this.DomainToDto(this.getService().get(id));
    }

    @PostMapping("/{id}/answers")
    public ResponseEntity<?> createQuestionAnswer(@PathVariable("id") Integer ques_id,@RequestBody SimpleAnswerDTO simpleAnswerDTO) throws NotFoundException{
           QuestionAnswer questionAnswer = new QuestionAnswer(simpleAnswerDTO);
            communityQAService.addAnswer(questionAnswer,ques_id,null);
             return ResponseEntity.ok(new Response(simpleAnswerDTO,new StatusDTO(201,"success")));

    }
}
