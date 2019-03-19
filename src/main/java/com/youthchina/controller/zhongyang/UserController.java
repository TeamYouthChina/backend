package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.CompCollectResponseDTO;
import com.youthchina.dto.applicant.JobCollectResponseDTO;
import com.youthchina.dto.community.article.EssayDTO;
import com.youthchina.dto.community.question.QuestionDTO;
import com.youthchina.dto.community.video.VideoDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.jinhao.communityQA.CommunityQAServiceImplement;
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
import java.util.List;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/users/**")
public class UserController extends DomainCRUDController<UserDTO, User, Integer> {

    private UserService userService;
    private String url;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CommunityQAServiceImplement communityQAService;
    @Autowired
    private EssayServiceImpl essayService;

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

    @GetMapping("/{id}/attentions")
    public ResponseEntity<?> getAllCollections(@PathVariable("id") Integer user_id,@RequestParam(value="type") String type) throws NotFoundException{
        switch (type){
            case "Job":{
                List<JobCollect> jobCollects=studentService.getJobCollect(user_id);
                List<JobCollectResponseDTO> jobCollectResponseDTOS=new ArrayList<>();
                for(JobCollect jobCollect:jobCollects){
                    JobCollectResponseDTO jobCollectResponseDTO=new JobCollectResponseDTO(jobCollect);
                    jobCollectResponseDTOS.add(jobCollectResponseDTO);
                }
                return ResponseEntity.ok(new Response(jobCollectResponseDTOS));

            }

            case "Company":{
                List<CompCollect> compCollects=studentService.getCompCollect(user_id);
                List<CompCollectResponseDTO> compCollectResponseDTOS=new ArrayList<>();
                for(CompCollect compCollect:compCollects){
                    CompCollectResponseDTO compCollectResponseDTO=new CompCollectResponseDTO(compCollect);
                    compCollectResponseDTOS.add(compCollectResponseDTO);
                }
                return ResponseEntity.ok(new Response(compCollectResponseDTOS));

            }
            case "Essay":{
                List<ComEssay> comEssays=essayService.getAllEssayUserAttention(user_id);
                List<EssayDTO> essayDTOS=new ArrayList<>();
                for(ComEssay comEssay:comEssays){
                    EssayDTO essayDTO=new EssayDTO(comEssay);
                    essayDTOS.add(essayDTO);

                }
                return ResponseEntity.ok((new Response(essayDTOS)));

            }
            case "Video":{
                List<Video> videos=communityQAService.listAllUserAttenVideos(user_id);
                List<VideoDTO> videoDTOS=new ArrayList<>();
                for(Video video:videos){
                    VideoDTO videoDTO=new VideoDTO(video);
                    videoDTOS.add(videoDTO);
                }
                return ResponseEntity.ok(new Response(videoDTOS));

            }
            case "Question":{
                List<QuestionDTO> questionDTOS=new ArrayList<>();
                List<Question> questions=communityQAService.listAllUserAttenQuestions(user_id);
                for (Question question:questions){
                    QuestionDTO questionDTO=new QuestionDTO(question);
                    questionDTOS.add(questionDTO);
                }
                return ResponseEntity.ok(new Response(questionDTOS));
            }
            default:throw new NotFoundException(404,404,"do not have this type");


        }


    }


    @Override
    protected DomainCRUDService<User, Integer> getService() {
        return this.userService;
    }

    @Override
    protected UserDTO DomainToDto(User domain) {
        return new UserDTO(domain);
    }

    @Override
    protected User DtoToDomain(UserDTO userDTO) {
        return new User(userDTO);
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id);
    }

}
