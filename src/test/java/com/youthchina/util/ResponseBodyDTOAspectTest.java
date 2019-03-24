package com.youthchina.util;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.security.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 3/23/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ResponseBodyDTOAspectTest {
    @Autowired
    public AspectTestClass aspectTestClass;

    @Test
    public void testAspectWithResponse() {
        Assert.assertEquals(((Response)aspectTestClass.testedResponseMethod().getBody()).getContent().getClass(), UserDTO.class);
    }

    @Test
    public void testAspectWithListResponse() {
        List list = (List) aspectTestClass.testedListResponseMethod().getBody().getContent().get("users");
        Assert.assertEquals(list.size(), 3);
        Assert.assertEquals(list.get(0).getClass(), UserDTO.class);
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
        return ResponseEntity.ok(new ListResponse(users, "users"));
    }
}
