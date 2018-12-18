package com.youthchina.Qinghong;

import com.youthchina.domain.Qinghong.Candidate;
import com.youthchina.service.Qinghong.EmployerPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CandidateTest {
    @Autowired
    private EmployerPageService employerPageService;

    @Test
    @Rollback
    public void Test(){
        Candidate candidate=employerPageService.findCandidate(1);
        if(candidate == null){
            System.out.println("fail");
        }else {
            System.out.println("success");
        }
    }
}
