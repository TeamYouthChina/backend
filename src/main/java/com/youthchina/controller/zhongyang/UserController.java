package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Applicant.CompCollectResponseDTO;
import com.youthchina.dto.Applicant.JobCollectResponseDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.UserDTO;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
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

//    @GetMapping("/{id}/attentions")
//    public ResponseEntity<?> getAllCollections(@PathVariable("id") Integer user_id,@RequestParam(value="type") String type) throws NotFoundException{
//        switch (type){
//            case "Job":{
//                List<JobCollect> jobCollects=studentService.getJobCollect(user_id);
//                List<JobCollectResponseDTO> jobCollectResponseDTOS=new ArrayList<>();
//                for(JobCollect jobCollect:jobCollects){
//                    JobCollectResponseDTO jobCollectResponseDTO=new JobCollectResponseDTO(jobCollect);
//                    jobCollectResponseDTOS.add(jobCollectResponseDTO);
//                }
//                return ResponseEntity.ok(new Response(jobCollectResponseDTOS));
//
//            }
//
//            case "Company":{
//                List<CompCollect> compCollects=studentService.getCompCollect(user_id);
//                List<CompCollectResponseDTO> compCollectResponseDTOS=new ArrayList<>();
//                for(CompCollect compCollect:compCollects){
//                    CompCollectResponseDTO compCollectResponseDTO=new CompCollectResponseDTO(compCollect);
//                    compCollectResponseDTOS.add(compCollectResponseDTO);
//                }
//                return ResponseEntity.ok(new Response(compCollectResponseDTOS));
//
//            }
//            case "Question":{
//
//            }
//
//
//        }
//
//
//    }


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
