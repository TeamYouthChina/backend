package com.youthchina.others;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.util.AuthGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by zhongyangwu on 3/23/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
public class ResponseBodyDTOAspectTest {
    @Autowired
    public AspectTestClass aspectTestClass;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ApplicationContext applicationContext;

    private AuthGenerator authGenerator = new AuthGenerator();


    @Value("${web.url.prefix}")
    private String urlPrefix;


    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void testAspectWithResponse() {
        Assert.assertEquals(((Response) aspectTestClass.testedResponseMethod().getBody()).getContent().getClass(), UserDTO.class);
    }

    @Test
    public void testAspectWithListResponse() {
        List list = (List) aspectTestClass.testedListResponseMethod().getBody().getContent().getData();
        Assert.assertEquals(list.size(), 3);
        Assert.assertEquals(list.get(0).getClass(), UserDTO.class);
    }

    @Test
    public void testGetMe() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                post(this.urlPrefix + "/test/")
                        .content("{\n" +
                                "  \"id\":1,\n" +
                                "  \"username\": \"asfdfsf\",\n" +
                                "  \"password\": \"sldfjlsdkfj\",\n" +
                                "  \"email\": \"email@gdk.com\"\n" +
                                "}")
                        .with(authGenerator.authentication())
        )
                .andDo(print());

    }

}


@Component
class AspectTestClass {
    @ResponseBodyDTO(UserDTO.class)
    public ResponseEntity<?> testedResponseMethod() {
        User user = new User();
        user.setId(122);
        user.setPassword("ldskjfl");
        user.setGender("male");

        return ResponseEntity.ok(new Response(user));
    }

    @ResponseBodyDTO(UserDTO.class)
    public ResponseEntity<ListResponse> testedListResponseMethod() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i);
            user.setPassword("sdlfksdf");
            user.setPhonenumber("2323987948");
            users.add(user);
        }
        return ResponseEntity.ok(new ListResponse(new PageRequest(), 12, users));
    }
}

@RestController
class TestController {

    @PostMapping("${web.url.prefix}/test/**")
    public ResponseEntity test(@RequestBodyDTO(UserDTO.class) User user) {
        return ResponseEntity.ok(user);
    }
}


