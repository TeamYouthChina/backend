package com.youthchina.dto.community.answer;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.dto.util.RichTextRequestDTO;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class SimpleAnswerRequestDTO {
    private RichTextRequestDTO body;
    private Boolean is_anonymous;

    public SimpleAnswerRequestDTO(Answer answer) {
        RichTextRequestDTO richt = new RichTextRequestDTO(answer.getBody());
        this.body = richt;
        this.is_anonymous = (answer.getIsAnony() == 0) ? false : true;
    }

    public SimpleAnswerRequestDTO(){}

    public RichTextRequestDTO getBody() {
        return body;
    }

    public void setBody(RichTextRequestDTO body) {
        this.body = body;
    }

    public Boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(Boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
