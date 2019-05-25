package com.youthchina.others;

import com.youthchina.controller.BaseControllerTest;
import com.youthchina.util.permission.HasOwner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhongyangwu on 5/24/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IsOwnerExpressionTest extends BaseControllerTest {

    @Test
    public void testProtectedMethod() throws Exception {
        this.mvc.perform(post(this.urlPrefix + "/test/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"ownerId\": 1\n" +
                        "}")
                .with(authGenerator.authentication(1))
        )
                .andExpect(status().is2xxSuccessful());

        this.mvc.perform(post(this.urlPrefix + "/test/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"ownerId\": 1\n" +
                        "}")
                .with(authGenerator.authentication(3))
        )
                .andExpect(status().is(403));
    }
}


class HasOwnerEntity implements HasOwner {

    @Override
    public Integer getOwnerId() {
        return 1;
    }

}


@RestController
class IsOwnerTestController {

    @PostMapping("${web.url.prefix}/test/owner")
    @PreAuthorize("isOwner(#hasOwnerEntity)")
    public ResponseEntity protectedMethod(HasOwnerEntity hasOwnerEntity) {
        return ResponseEntity.ok(null);
    }
}
