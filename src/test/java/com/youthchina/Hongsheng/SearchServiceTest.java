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
        SearchResult result = searchService.search("question","腾讯","",1,10);
        System.out.println(result);
    }

}