package com.youthchina.zhongyang;

import com.youthchina.domain.Tianjian.ComEssayReply;
import com.youthchina.domain.Tianjian.ComReplyEvaluate;
import com.youthchina.service.Tianjian.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class InternPageServiceTest extends com.youthchina.zhongyang.BaseTest {
    @Autowired
    UserService userService;

   /* @Test
    public void testAddEssay(){
        ComEssay comessay = new ComEssay();
        comessay.setEssay_title("essay_title");
        comessay.setEssay_abbre("abbre");
        comessay.setEssay_body("body");
        comessay.setEssay_delete(0);
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        comessay.setEssay_edit_time(time1);
        comessay.setEssay_pub_time(time1);
        List<Integer> l = new ArrayList<Integer>();
        l.add(0);
        l.add(1);
        l.add(2);
        int t = 1000;
        userService.addEssay(comessay,l,t);
    }*/

   /* @Test
    public void testDeleteEssay(){
        int t = 30;
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        userService.deleteEssay(t,time1);
    }*/

    /*@Test
    public void testUpdateEssay(){
        ComEssay comessay = new ComEssay();
        comessay.setEssay_id(30);
        comessay.setEssay_title("essay_title_update");
        comessay.setEssay_abbre("abbre_update");
        comessay.setEssay_body("body_update");
        comessay.setEssay_delete(0);
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        comessay.setEssay_edit_time(time1);
        comessay.setEssay_pub_time(time1);
        List<Integer> l = new ArrayList<Integer>();
        l.add(3);
        l.add(4);
        l.add(5);
        int t = 1000;
        userService.updateEssay(comessay,t,l);
    }*/

    @Test
    public void testupdateReply(){
        ComReplyEvaluate cre = new ComReplyEvaluate();
        cre.setEvaluate_id(1);
        cre.setUser_id(1001);
        cre.setEvaluate_type(2);
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        cre.setEvaluate_time(time1);
        List<ComEssayReply> i = userService.getEssayReply(30);
        System.out.println(i.size());
    }
}
