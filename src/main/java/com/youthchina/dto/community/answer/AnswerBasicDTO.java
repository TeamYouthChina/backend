package com.youthchina.dto.community.answer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTO;

/**
 * Created by xiaoyiwang on 2/24/19.
 */

public class AnswerBasicDTO {
    private Integer id;
    private RichTextDTO body;
    private boolean is_anonymous;
    private UserDTO creator;
    private String modified_at;
    private String create_at;

    public AnswerBasicDTO(){}

    public AnswerBasicDTO(Answer answer){
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(answer.getAnswer_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.is_anonymous = (answer.getIsAnony() == 0) ? false : true;
        this.creator = new UserDTO(answer.getUser());
        this.modified_at = answer.getEditTime().toString();
        this.create_at = answer.getPubTime().toString();
        this.id = answer.getId();
    }

    public RichTextDTO getBody() {
        return body;
    }

    public void setId(Integer id){this.id = id;}

    public Integer getId(){return id;}

    public void setBody(RichTextDTO body) {
        this.body = body;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

}