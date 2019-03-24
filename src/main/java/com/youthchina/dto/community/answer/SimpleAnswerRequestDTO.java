package com.youthchina.dto.community.answer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.RichTextDTO;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class SimpleAnswerRequestDTO implements RequestDTO {
    private RichTextDTO body;
    private Boolean is_anonymous;

    public SimpleAnswerRequestDTO(QuestionAnswer questionAnswer) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(questionAnswer.getAnswer_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.is_anonymous = (questionAnswer.getUser_anony() == 0) ? false : true;
    }

    public SimpleAnswerRequestDTO(){}

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
        this.body = body;
    }

    public Boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(Boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
