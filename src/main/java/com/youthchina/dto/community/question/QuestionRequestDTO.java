package com.youthchina.dto.community.question;

import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

/**
 * Created by hongshengzhang on 2/23/19.
 */
public class QuestionRequestDTO implements RequestDTO, QuestionDTO {
    String title;
    RichTextResponseDTO body;
    boolean is_anonymous;
    Integer rela_type;
    Integer rela_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RichTextResponseDTO getBody() {
        return body;
    }

    public void setBody(RichTextResponseDTO body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id) {
        this.rela_id = rela_id;
    }
}
