package com.youthchina.controller.Xiaoyi;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.question.QuestionRequestDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.solr.SolrDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Xiaoyi.SearchService;
import com.youthchina.service.jinhao.QuestionService;
import io.micrometer.core.instrument.search.Search;
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
 * Created by Xiaoyi Wang on 3/30/19.
 */

@RestController
@RequestMapping("${web.url.prefix}/search/**")
public class SearchController {
    private String url;
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService, @Value("${web.url.prefix}") String prefix) {
        this.searchService = searchService;
        this.url = prefix + "/search/";
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchQuestion(@RequestBody String keyword) throws Exception {
        List<Question> listq = searchService.questionSearch(keyword);
        List<QuestionResponseDTO> listqq = new ArrayList<>();
        for(Question q : listq){
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(q);
            listqq.add(questionResponseDTO);
        }
        if(listqq.get(0).getId() != null){
            return ResponseEntity.ok(new Response(listqq, new StatusDTO(200,"success")));
        }
        else
            return ResponseEntity.ok(new Response(listqq, new StatusDTO(400,"fail")));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchEssay(@RequestBody String keyword) throws Exception {
        List<ComEssay> liste = searchService.essaySearch(keyword);
        List<EssayResponseDTO> listee = new ArrayList<>();
        for(ComEssay c : liste){
            EssayResponseDTO essayResponseDTO = new EssayResponseDTO(c);
            listee.add(essayResponseDTO);
        }
        if(listee.get(0).getId() != null){
            return ResponseEntity.ok(new Response(listee, new StatusDTO(200,"success")));
        }
        else
            return ResponseEntity.ok(new Response(listee, new StatusDTO(400,"fail")));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchCompany(@RequestBody String keyword) throws Exception {
        List<Company> listcom = searchService.companySearch(keyword);
        return ResponseEntity.ok(new Response(listcom));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchJob(@RequestBody String keyword) throws Exception {
        List<Job> listj = searchService.jobSearch(keyword);
        return ResponseEntity.ok(new Response(listj));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchComment(@RequestBody String keyword) throws Exception {
        List<Comment> listc = searchService.commentSearch(keyword);
        return ResponseEntity.ok(new Response(listc));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchVideo(@RequestBody String keyword) throws Exception {
        List<Video> listv = searchService.videoSearch(keyword);
        return ResponseEntity.ok(new Response(listv));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchAnswer(@RequestBody String keyword) throws Exception {
        List<Answer> lista = searchService.answerSearch(keyword);
        return ResponseEntity.ok(new Response(lista));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> searchMultiple(@RequestBody String keyword) throws Exception {
        List<SolrDTO> listmulti = searchService.multipleSearch(keyword);
        return ResponseEntity.ok(new Response(listmulti));
    }

}