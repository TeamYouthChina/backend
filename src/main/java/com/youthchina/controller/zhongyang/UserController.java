package com.youthchina.controller.zhongyang;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.UserDTO;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/users/**")
public class UserController extends DomainCRUDController<UserDTO, User, Integer> {

    private UserService userService;
    private String url;

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
//    public ResponseEntity<?> getAllCollections(@PathVariable("id") Integer user_id) throws NotFoundException{
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
