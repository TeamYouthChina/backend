package Hongsheng;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.service.Hongsheng.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceTest {
    SearchService searchService;

    public void  testQuestion() throws Exception{
        SearchResult result = searchService.search("question","腾讯","",1,10);
        System.out.println(result);
    }

    public static void main(String[] args) throws  Exception{
        SearchServiceTest test = new SearchServiceTest();
        test.testQuestion();
    }
}