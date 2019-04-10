package com.youthchina.dto.community.question;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

import java.sql.Timestamp;

/**
 * Created by xiaoyiwang on 2/24/19.
 */

public class QuestionBasicDTO implements QuestionDTO{
    private Integer id;
    private UserDTO creator;
    private String title;
    private boolean is_anonymous;
    private Timestamp create_at;
    private Timestamp modified_at;
    private Integer rela_type;
    private Integer rela_id;
    private RichTextResponseDTO body;

    public QuestionBasicDTO(Question question) {
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
        this.rela_id = question.getRelaId();
    }

    public QuestionBasicDTO(){}

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

/*public InvitationService getInvitation() {
        return invitation;
    }

    public void setInvitation(InvitationService invitation) {
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
}