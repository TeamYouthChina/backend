package com.youthchina.controller.Xiaoyi;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.community.video.VideoResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.solr.SolrDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Xiaoyi.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaoyi Wang on 3/30/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/search")
public class SearchController {
    @Autowired private SearchService searchService;

    @PostMapping("/users")
    public ResponseEntity searchUser(@RequestBody String keyWord) throws Exception {
        List<User> list = searchService.userSearch(keyWord);
        List<UserDTO> returnList = new ArrayList<>();

        for (User u : list) {
            UserDTO userDTO = new UserDTO(u);
            returnList.add(userDTO);
        }

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

    @PostMapping("/companies")
    public ResponseEntity searchCompany(@RequestBody String keyWord) throws Exception {
        List<Company> list = searchService.companySearch(keyWord);
        List<CompanyResponseDTO> returnList = new ArrayList<>();

        for (Company c : list) {
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(c);
            returnList.add(companyResponseDTO);
        }

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

    @PostMapping("/questions")
    public ResponseEntity searchQuestion(@RequestBody String keyWord) throws Exception {
        List<Question> list = searchService.questionSearch(keyWord);
        List<QuestionResponseDTO> returnList = new ArrayList<>();

        for (Question q : list) {
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(q);
            returnList.add(questionResponseDTO);
        }

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

    @PostMapping("/answers")
    public ResponseEntity searchAnswer(@RequestBody String keyWord) throws Exception {
        List<Answer> list = searchService.answerSearch(keyWord);
        List<SimpleAnswerResponseDTO> returnList = new ArrayList<>();

        for (Answer a : list) {
            SimpleAnswerResponseDTO simpleAnswerResponseDTO = new SimpleAnswerResponseDTO(a);
            returnList.add(simpleAnswerResponseDTO);
        }

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

    @PostMapping("/articles")
    public ResponseEntity searchArticle(@RequestBody String keyWord) throws Exception {
        List<ComEssay> list = searchService.essaySearch(keyWord);
        List<EssayResponseDTO> returnList = new ArrayList<>();

        for (ComEssay c : list) {
            EssayResponseDTO essayResponseDTO = new EssayResponseDTO(c);
            returnList.add(essayResponseDTO);
        }

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

    @PostMapping("/videos")
    public ResponseEntity searchVideo(@RequestBody String keyWord) throws Exception {
        List<Video> list = searchService.videoSearch(keyWord);
        List<VideoResponseDTO> returnList = new ArrayList<>();

        for (Video v : list) {
            VideoResponseDTO videoResponseDTO = new VideoResponseDTO(v);
            returnList.add(videoResponseDTO);
        }

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

    @PostMapping("/jobs")
    public ResponseEntity searchJobs(@RequestBody String keyWord) throws Exception {
        List<Job> list = searchService.jobSearch(keyWord);
        List<JobResponseDTO> returnList = new ArrayList<>();

        for (Job j : list) {
            JobResponseDTO jobResponseDTO = new JobResponseDTO(j);
            returnList.add(jobResponseDTO);
        }

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

    @PostMapping("/muti")
    public ResponseEntity searchMuti(@RequestBody String keyWord) throws Exception {
        List<SolrDTO> returnList = searchService.multipleSearch(keyWord);

        if (returnList !=null)
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(returnList, new StatusDTO(400,"fail")));
    }

}