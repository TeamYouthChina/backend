package com.youthchina.controller.zhongyang;

import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
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
 * Created by hongshengzhang on 2/2/19.
 */

@RestController
@RequestMapping("${web.url.prefix}/answers/**")
public class AnswerController extends DomainCRUDController<SimpleAnswerDTO, QuestionAnswer, Integer> {
    private String url;
    private CommunityQAService communityQAService;

    @Autowired
    public QuestionController(CommunityQAService communityQAService, @Value("${web.url.prefix}") String prefix) {
        this.communityQAService = communityQAService;
        this.url = prefix + "/answers/";
    }

    @Override
    protected DomainCRUDService<Question, Integer> getService() {
        return this.communityQAService;
    }

    @Override
    protected SimpleAnswerDTO DomainToDto(Question domain) {
        return new SimpleAnswerDTO(domain);
    }

    @Override
    protected QuestionAnswer DtoToDomain(SimpleAnswerDTO simpleAnswerDTO) {
        return new QuestionAnswer(questionDTO);
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
        questionDTO.setInvitation();
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
}
