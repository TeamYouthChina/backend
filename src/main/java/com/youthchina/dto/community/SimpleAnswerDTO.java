package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RichTextDTO;

import java.sql.Timestamp;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class SimpleAnswerDTO {
    private Integer id;
    private User creator;
    private RichTextDTO body;
    private Boolean isAnonymous;
    private Timestamp creatAt;

    public SimpleAnswerDTO(QuestionAnswer questionAnswer) {
        this.id  = questionAnswer.getAnswer_id();
        this.creator = questionAnswer.getAnswer_user();
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(questionAnswer.getAnswer_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }

        this.isAnonymous = (questionAnswer.getUser_anony() == 0) ? false : true;
        this.creatAt = questionAnswer.getAnswer_pub_time();
    }

    public SimpleAnswerDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
        this.body = body;
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Timestamp getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Timestamp creatAt) {
        this.creatAt = creatAt;
    }
}
