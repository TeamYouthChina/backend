package com.youthchina.jinhao;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.service.jinhao.communityQA.CommunityQAService;
import com.youthchina.service.jinhao.communityQA.CommunityQAServiceImplement;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @InjectMocks
    private CommunityQAService communityQAService = new CommunityQAServiceImplement();

    @Mock
    private CommunityQAMapper communityQAMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

}
