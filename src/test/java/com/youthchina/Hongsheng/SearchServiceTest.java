package com.youthchina.Hongsheng;

import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.service.Hongsheng.SearchService;
import com.youthchina.service.Hongsheng.SearchServiceImplememt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchServiceTest {

    @Autowired
    SearchService searchService;

    @Test
    public void  testQuestion() throws Exception{
        searchService.search("question","腾讯",null,1,10);
        searchService.search("question",null,"腾讯",1,10);
    }

    @Test
    public void  testAnswer() throws Exception{
        searchService.search("answer",null,"1",1,10);
    }

    @Test
    public void  testJob() throws Exception{
        searchService.search("job","实习生",null,1,10);
        searchService.search("job",null,"实习生",1,10);
    }

    @Test
    public void  testCompany() throws Exception{
        searchService.search("company","中国",null,1,10);
        searchService.search("company",null,"中国",1,10);
    }

    @Test
    public void  testEssay() throws Exception{
        searchService.search("essay","中国",null,1,10);
        searchService.search("essay",null,"中国",1,10);
    }// todo: have problem need to fix

    @Test
    public void  testVideo() throws Exception{
        searchService.search("video","视频1",null,1,10);
    }

    @Test
    public void  testReview() throws Exception{
        searchService.search("briefReview",null,"阿里",1,10);
    }

    @Test
    public void  testComment() throws Exception{
        searchService.search("comment",null,"好好好",1,10);
    } // todo: have problem need to fix

    @Test
    public void  testMuti() throws Exception{
        searchService.search("all",null,"好好好",1,10);
    } // todo: have problem need to fix

}