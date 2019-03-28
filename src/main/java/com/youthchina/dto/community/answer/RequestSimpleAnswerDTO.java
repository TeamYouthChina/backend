package com.youthchina.dto.community.answer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.dto.util.RichTextDTO;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class RequestSimpleAnswerDTO {
    private RichTextDTO body;
    private Boolean is_anonymous;

    public RequestSimpleAnswerDTO(Answer answer) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(answer.getAnswer_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.is_anonymous = (answer.getUser_anony() == 0) ? false : true;
    }

    public RequestSimpleAnswerDTO(){}

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
