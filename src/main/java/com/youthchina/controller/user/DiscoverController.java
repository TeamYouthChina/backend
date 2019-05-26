package com.youthchina.controller.user;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.recommendation.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianjian on 2019/5/22.
 */
@RestController
@RequestMapping("${web.url.prefix}/discovery/**")
public class DiscoverController {
    @Autowired
    private RecommendServiceImpl recommendService;

    @GetMapping("/users")
    public ResponseEntity getRecommendUsers( @AuthenticationPrincipal User user, PageRequest pageRequest) throws NotFoundException {
        List<User> users  = recommendService.getRecommendUser(user.getId());
        List<UserDTO> userDTOS = new ArrayList<>();
        Iterator iterator = users.iterator();
        while (iterator.hasNext()){
            UserDTO userDTO = new UserDTO((User)iterator.next());
            userDTOS.add(userDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, userDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/companies")
    public ResponseEntity getRecommendCompanies( @AuthenticationPrincipal User user, PageRequest pageRequest)throws NotFoundException{
        List<Company> companies  = recommendService.getRecommendCompany(user.getId());
        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();
        Iterator iterator = companies.iterator();
        while (iterator.hasNext()){
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO((Company) iterator.next());
            companyResponseDTOS.add(companyResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, companyResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/articles")
    public ResponseEntity getRecommendArticles( @AuthenticationPrincipal User user, PageRequest pageRequest)throws NotFoundException{
        List<ComEssay> comEssays  = recommendService.getRecommendEssay(user.getId());
        List<EssayResponseDTO> essayResponseDTOS = new ArrayList<>();
        Iterator iterator = comEssays.iterator();
        while (iterator.hasNext()){
            EssayResponseDTO essayResponseDTO = new EssayResponseDTO((ComEssay) iterator.next());
            essayResponseDTOS.add(essayResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, essayResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/questions")
    public ResponseEntity getRecommendQuestions( @AuthenticationPrincipal User user, PageRequest pageRequest)throws NotFoundException{
        List<Question> questionList  = recommendService.getRecommendQuestion(user.getId());
        List<QuestionResponseDTO> questionResponseDTOArrayList = new ArrayList<>();
        Iterator iterator = questionList.iterator();
        while (iterator.hasNext()){
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO((Question) iterator.next());
            questionResponseDTOArrayList.add(questionResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, questionResponseDTOArrayList);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/jobs")
    public ResponseEntity getRecommendJobs( @AuthenticationPrincipal User user, PageRequest pageRequest)throws NotFoundException{
        List<Job> jobs  = recommendService.getRecommendJob(user.getId());
        List<JobResponseDTO> jobResponseDTOS = new ArrayList<>();
        Iterator iterator = jobs.iterator();
        while (iterator.hasNext()){
            JobResponseDTO jobResponseDTO = new JobResponseDTO((Job) iterator.next());
            jobResponseDTOS.add(jobResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, jobResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/editorials")
    public ResponseEntity getRecommendEditorials( @AuthenticationPrincipal User user, PageRequest pageRequest)throws NotFoundException{
        List<BriefReview> briefReviews  = recommendService.getRecommendBriefReview(user.getId());
        List<BriefReviewResponseDTO> briefReviewResponseDTOS = new ArrayList<>();
        Iterator iterator = briefReviews.iterator();
        while (iterator.hasNext()){
            BriefReviewResponseDTO briefReviewResponseDTO = new BriefReviewResponseDTO((BriefReview) iterator.next());
            briefReviewResponseDTOS.add(briefReviewResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, briefReviewResponseDTOS);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping
    public ResponseEntity getRecommend( @AuthenticationPrincipal User user,@PathVariable Integer id,@PathVariable Integer type)throws NotFoundException {
        if (type.equals("user")) {
            List<User> users = recommendService.getRecommendUser(id);
            List<UserDTO> userDTOS = new ArrayList<>();
            Iterator iterator = users.iterator();
            while (iterator.hasNext()) {
                UserDTO userDTO = new UserDTO((User) iterator.next());
                userDTOS.add(userDTO);
            }
            return ResponseEntity.ok(new Response(userDTOS, new StatusDTO(200, "success")));
        }
        if (type.equals("company")) {
            List<Company> companies = recommendService.getRecommendCompany(id);
            List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();
            Iterator iterator = companies.iterator();
            while (iterator.hasNext()) {
                CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO((Company) iterator.next());
                companyResponseDTOS.add(companyResponseDTO);
            }
            return ResponseEntity.ok(new Response(companyResponseDTOS, new StatusDTO(200, "success")));
        }
        if (type.equals("article")) {
            List<ComEssay> comEssays = recommendService.getRecommendEssay(id);
            List<EssayResponseDTO> essayResponseDTOS = new ArrayList<>();
            Iterator iterator = comEssays.iterator();
            while (iterator.hasNext()) {
                EssayResponseDTO essayResponseDTO = new EssayResponseDTO((ComEssay) iterator.next());
                essayResponseDTOS.add(essayResponseDTO);
            }
            return ResponseEntity.ok(new Response(essayResponseDTOS, new StatusDTO(200, "success")));
        }
        if (type.equals("question")) {
            List<Question> questionList = recommendService.getRecommendQuestion(user.getId());
            List<QuestionResponseDTO> questionResponseDTOArrayList = new ArrayList<>();
            Iterator iterator = questionList.iterator();
            while (iterator.hasNext()) {
                QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO((Question) iterator.next());
                questionResponseDTOArrayList.add(questionResponseDTO);
            }
            return ResponseEntity.ok(new Response(questionResponseDTOArrayList, new StatusDTO(200, "success")));
        }
        if (type.equals("job")) {
            List<Job> jobs = recommendService.getRecommendJob(user.getId());
            List<JobResponseDTO> jobResponseDTOS = new ArrayList<>();
            Iterator iterator = jobs.iterator();
            while (iterator.hasNext()) {
                JobResponseDTO jobResponseDTO = new JobResponseDTO((Job) iterator.next());
                jobResponseDTOS.add(jobResponseDTO);
            }
            return ResponseEntity.ok(new Response(jobResponseDTOS, new StatusDTO(200, "success")));
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    @PostMapping("/tag")
    public ResponseEntity addTags( @PathVariable Integer id, @PathVariable Integer labelCode,@PathVariable Integer targetType)throws NotFoundException{
        recommendService.addTag(labelCode,targetType,id);
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @PutMapping("/tag")
    public ResponseEntity updateTags( @PathVariable Integer id, @PathVariable Integer labelCode,@PathVariable Integer targetType)throws NotFoundException{
        recommendService.addTag(labelCode,targetType,id);
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("/tag")
    public ResponseEntity deleteTags( @PathVariable Integer id, @PathVariable Integer labelCode,@PathVariable Integer targetType)throws NotFoundException{
        recommendService.deleteTag(labelCode,targetType,id);
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }
}
