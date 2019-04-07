package com.youthchina.dto.community.answer;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.question.QuestionBasicDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

public class SimpleAnswerResponseDTO implements ResponseDTO<Answer>{
    private RichTextResponseDTO body;
    private boolean is_anonymous;
    private UserDTO creator;
    private String modified_at;
    private String create_at;
    private QuestionBasicDTO question;
    private Integer id;

    public SimpleAnswerResponseDTO(){}

    public SimpleAnswerResponseDTO(Answer answer)  {
        RichTextResponseDTO richt = new RichTextResponseDTO(answer.getBody());
        this.body = richt;
        this.id = answer.getId();
        this.is_anonymous = (answer.getIsAnony() == 0) ? false : true;
        this.creator = new UserDTO(answer.getUser());
        this.modified_at = answer.getEditTime().toString();
        this.create_at = answer.getPubTime().toString();
        this.question = new QuestionBasicDTO(answer.getQuestion());
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

    @Override
    public void convertToDTO(Answer answer) {
        RichTextResponseDTO richt = new RichTextResponseDTO(answer.getBody());
        this.body = richt;
        this.id = answer.getId();
        this.is_anonymous = (answer.getIsAnony() == 0) ? false : true;
        this.creator = new UserDTO(answer.getUser());
        this.modified_at = answer.getEditTime().toString();
        this.create_at = answer.getPubTime().toString();
        this.question = new QuestionBasicDTO(answer.getQuestion());
    }
}
