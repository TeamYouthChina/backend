package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RichTextDTO;

/**
 * Created by xiaoyiwang on 2/24/19.
 */

public class AnswerBasicDTO {
    private Integer id;
    private RichTextDTO body;
    private boolean is_anonymous;
    private User creator;
    private String modified_at;
    private String create_at;
    private String company_id;

    public AnswerBasicDTO(){}

    public AnswerBasicDTO(QuestionAnswer questionAnswer){
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(questionAnswer.getAnswer_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.is_anonymous = (questionAnswer.getUser_anony() == 0) ? false : true;
        this.creator = questionAnswer.getAnswer_user();
        this.modified_at = questionAnswer.getAnswer_edit_time().toString();
        this.create_at =questionAnswer.getAnswer_pub_time().toString();
        this.id = questionAnswer.getAnswer_id();
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
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

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

}