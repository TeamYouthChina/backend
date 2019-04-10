package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.CompCollectResponseDTO;
import com.youthchina.dto.applicant.JobCollectResponseDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.community.video.VideoResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.jinhao.AnswerServiceImpl;
import com.youthchina.service.jinhao.AttentionServiceImpl;
import com.youthchina.service.jinhao.QuestionServiceImpl;
import com.youthchina.service.jinhao.VideoServiceImpl;
import com.youthchina.service.qingyang.JobServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/users/**")
public class UserController extends DomainCRUDController<User, Integer> {

    private UserService userService;
    private String url;

    @Autowired
    private StudentService studentService;
    @Autowired
    private AttentionServiceImpl attentionService;
    @Autowired
    private EssayServiceImpl essayService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    public UserController(UserService userService, @Value("${web.url.prefix}") String prefix) {
        this.userService = userService;
        this.url = prefix + "/users/";
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            return get(id);
        } else {
            throw new ForbiddenException();
        }
    }
    /**
    * @Description: 需要添加分页
    * @Param: [user_id, type]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/4/4
    */

    @GetMapping("/{id}/attentions")
    public ResponseEntity<?> getAllCollections(@PathVariable("id") Integer user_id, @RequestParam(value = "type") String type,@AuthenticationPrincipal User user) throws NotFoundException,ForbiddenException {
        if(user.getId()!=user_id){
            throw new ForbiddenException();
        }
        switch (type) {
            case "job": {
                List<JobCollect> jobCollects = studentService.getJobCollect(user_id);
                List<JobCollectResponseDTO> jobCollectResponseDTOS = new ArrayList<>();
                if(jobCollects!=null){
                    for (JobCollect jobCollect : jobCollects) {
                        JobCollectResponseDTO jobCollectResponseDTO = new JobCollectResponseDTO(jobCollect);
                        jobCollectResponseDTOS.add(jobCollectResponseDTO);
                }

                }
                return ResponseEntity.ok(new Response(jobCollectResponseDTOS));

            }

            case "company": {
                List<CompCollect> compCollects = studentService.getCompCollect(user_id);
                List<CompCollectResponseDTO> compCollectResponseDTOS = new ArrayList<>();
                if(compCollects!=null){
                    for (CompCollect compCollect : compCollects) {
                        CompCollectResponseDTO compCollectResponseDTO = new CompCollectResponseDTO(compCollect);
                        compCollectResponseDTOS.add(compCollectResponseDTO);
                }

                }
                return ResponseEntity.ok(new Response(compCollectResponseDTOS));

            }
            case "article": {
                List<EssayResponseDTO> essayResponseDTOS = new ArrayList<>();
                List<Integer> result = attentionService.getAllIdsOfAttention(new ComEssay(),user_id);
                if(result!=null){
                    for (Integer id : result) {
                        ComEssay comEssay = essayService.getEssay(id);
                        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
                        essayResponseDTOS.add(essayResponseDTO);

                }


                }
                return ResponseEntity.ok((new Response(essayResponseDTOS)));

            }
            case "video": {
                List<VideoResponseDTO> videoResponseDTOS = new ArrayList<>();
                List<Integer> result = attentionService.getAllIdsOfAttention(new Video(),user_id);
                if(result!=null){
                    for (Integer id : result) {
                        Video video = videoService.get(id);
                        VideoResponseDTO videoResponseDTO = new VideoResponseDTO(video);
                        videoResponseDTOS.add(videoResponseDTO);
                }

                }
                return ResponseEntity.ok(new Response(videoResponseDTOS));

            }
            case "question": {
                List<QuestionResponseDTO> questionResponseDTOS = new ArrayList<>();
                List<Integer> result = attentionService.getAllIdsOfAttention(new Question(),user_id);
                if(result!=null){
                    for (Integer id : result) {
                        Question question = questionService.get(id);
                        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(question);
                        questionResponseDTOS.add(questionResponseDTO);
                }

                }
                return ResponseEntity.ok(new Response(questionResponseDTOS));
            }
            case "answer":{
                List<SimpleAnswerResponseDTO> answerResponseDTOS=new ArrayList<>();
                List<Integer> result=new ArrayList<>();
                result=attentionService.getAllIdsOfAttention(new Answer(),user_id);
                if(result!=null){
                    for(Integer id:result){
                        Answer answer=answerService.get(id);
                        SimpleAnswerResponseDTO answerResponseDTO=new SimpleAnswerResponseDTO(answer);
                        answerResponseDTOS.add(answerResponseDTO);
                    }
                }

                return ResponseEntity.ok(new Response(answerResponseDTOS));
            }
            default:
                throw new NotFoundException(404, 404, "do not have this type");


        }


    }

    /**
     * 返回我的 收藏公司 发布职位
     * @param id
     * @param user
     * @return
     * @throws ForbiddenException
     * @throws NotFoundException
     */
    @GetMapping("/{id}/my")
    public ResponseEntity<?> getMy(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {

            Map<String, Object> result = new HashMap<>();

            /**收藏的公司*/
            List<CompCollect> compCollects = studentService.getCompCollect(id);
            List<CompanyResponseDTO> companyResponseDTOList = new ArrayList<>();
            if(compCollects != null && compCollects.size() > 0){
                for(CompCollect compCollect : compCollects){
                    CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
                    companyResponseDTO.convertToDTO(compCollect.getCompany());
                    companyResponseDTOList.add(companyResponseDTO);
                }
            }
            result.put("companies", companyResponseDTOList);

            /**发布的职位*/
            List<Job> jobsOwnedByUserId = jobService.getJobByUserId(id);
            List<JobResponseDTO> jobResponseDTOList = new JobResponseDTO().convertToDTO(jobsOwnedByUserId);
            result.put("jobs", jobResponseDTOList);


            return ResponseEntity.ok(new Response(result));
        } else {
            throw new ForbiddenException();
        }
    }


    @Override
    protected DomainCRUDService<User, Integer> getService() {
        return this.userService;
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id);
    }

}
