package com.youthchina.dto.community.question;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.answer.AnswerBasicDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class QuestionResponseDTO implements ResponseDTO<Question>, QuestionDTO {
    private Integer id;
    private UserDTO creator;
    private String title;
    private boolean is_anonymous;
    private Timestamp create_at;
    private Timestamp modified_at;
    private List<AnswerBasicDTO> answers;
    //private AnswerInvitation invitation;
    private Integer rela_type;
    private Integer rela_id;
    private RichTextResponseDTO body;
    private boolean isAttention;


    public QuestionResponseDTO(Question question) {
        this.id = question.getId();
        if(question.getIsAnony()==0)
            this.creator = new UserDTO(question.getUser());
        else
            this.creator = null;
        this.title = question.getTitle();
        RichTextResponseDTO richt = new RichTextResponseDTO(question.getBody());
        this.body = richt;
        //this.invitation = question.getQues_invitation();
        this.is_anonymous = (question.getIsAnony()==1 ? true : false);
        this.create_at = question.getPubTime();
        this.modified_at = question.getEditTime();
        this.rela_type = question.getRelaType();
        this.answers = new ArrayList<AnswerBasicDTO>();
        this.rela_id = question.getRelaId();
        if(question.getAnswers() != null) {
            for(Answer answer : question.getAnswers()) {
                this.answers.add(new AnswerBasicDTO(answer));
            }
        }

    }

    public QuestionResponseDTO(){}

    public RichTextResponseDTO getBody(){return body;}

    public void setBody(RichTextResponseDTO body){this.body = body;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public List<AnswerBasicDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerBasicDTO> answers) {
        this.answers = answers;
    }

    /*public AnswerInvitation getInvitation() {
        return invitation;
    }

    public void setInvitation(AnswerInvitation invitation) {
        this.invitation = invitation;
    }*/

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id){
        this.rela_id = rela_id;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }


    @Override
    public void convertToDTO(Question question) {
        this.id = question.getId();
        this.creator = new UserDTO(question.getUser());
        this.title = question.getTitle();
        RichTextResponseDTO richt = new RichTextResponseDTO(question.getBody());
        this.body = richt;
        //this.invitation = question.getQues_invitation();
        this.is_anonymous = (question.getIsAnony()==1 ? true : false);
        this.create_at = question.getPubTime();
        this.modified_at = question.getEditTime();
        this.rela_type = question.getRelaType();
        this.answers = new ArrayList<AnswerBasicDTO>();
        this.rela_id = question.getRelaId();
        if(question.getAnswers() != null) {
            for(Answer answer : question.getAnswers()) {
                this.answers.add(new AnswerBasicDTO(answer));
            }
        }
    }
}
