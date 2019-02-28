package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.dto.RichTextDTO;
import com.youthchina.dto.UserDTO;

public class SimpleAnswerDTO {
    private RichTextDTO body;
    private boolean is_anonymous;
    private UserDTO creator;
    private String modified_at;
    private String create_at;
    private QuestionBasicDTO question;
    private Integer id;

    public SimpleAnswerDTO(){}

    public SimpleAnswerDTO(QuestionAnswer questionAnswer){
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(questionAnswer.getAnswer_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.id = questionAnswer.getAnswer_id();
        this.is_anonymous = (questionAnswer.getUser_anony() == 0) ? false : true;
        this.creator = new UserDTO(questionAnswer.getAnswer_user());
        this.modified_at = questionAnswer.getAnswer_edit_time().toString();
        this.create_at =questionAnswer.getAnswer_pub_time().toString();
        this.question = new QuestionBasicDTO(questionAnswer.getQuestion());
    }

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
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

    public QuestionBasicDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBasicDTO question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
