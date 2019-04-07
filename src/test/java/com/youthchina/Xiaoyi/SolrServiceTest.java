package com.youthchina.Xiaoyi;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.Xiaoyi.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class SolrServiceTest {
    SearchService searchService;

    public void  testUser() throws Exception{
        List<User> listu = new ArrayList<>();
        listu = searchService.userSearch("Admin");
        System.out.println(listu);
    }

    public static void main(String[] args) throws  Exception{
        SolrServiceTest testu = new SolrServiceTest();
        testu.testUser();
    }
}