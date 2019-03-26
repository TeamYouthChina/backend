package com.youthchina.dto.community.answer;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.dto.util.RichTextDTORequest;
import com.youthchina.dto.util.RichTextDTOResponse;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class SimpleAnswerRequestDTO {
    private RichTextDTORequest body;
    private Boolean is_anonymous;

    public SimpleAnswerRequestDTO(Answer answer) {
        RichTextDTORequest richt = new RichTextDTORequest(answer.getBody());
        this.body = richt;
        this.is_anonymous = (answer.getIsAnony() == 0) ? false : true;
    }

    public SimpleAnswerRequestDTO(){}

    public RichTextDTORequest getBody() {
        return body;
    }

    public void setBody(RichTextDTORequest body) {
        this.body = body;
    }

    public Boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(Boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
