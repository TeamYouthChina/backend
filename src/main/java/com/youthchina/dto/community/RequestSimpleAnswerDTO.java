package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RichTextDTO;

import java.sql.Timestamp;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class RequestSimpleAnswerDTO {
    private RichTextDTO body;
    private Boolean isAnonymous;

    public RequestSimpleAnswerDTO(QuestionAnswer questionAnswer) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(questionAnswer.getAnswer_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.isAnonymous = (questionAnswer.getUser_anony() == 0) ? false : true;
    }

    public RequestSimpleAnswerDTO(){}

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

}
