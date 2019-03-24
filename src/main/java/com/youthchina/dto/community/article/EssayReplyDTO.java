package com.youthchina.dto.community.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTOResponse;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class EssayReplyDTO {
    private Integer id;
    private UserDTO creator;
    private RichTextDTOResponse body;
    private String create_at;
    private boolean is_anonymous;
    private String modified_at;

    @Autowired
    UserServiceImpl userService;

    public EssayReplyDTO() {
    }

    public EssayReplyDTO(ComEssayReply comEssayReply) {
        this.id = comEssayReply.getReply_id();
        try {
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTOResponse richt = mapper.readValue(comEssayReply.getReply_content(), RichTextDTOResponse.class);
            this.body = richt;
        } catch (Exception e) {
            System.out.println("Exception");
        }
        this.create_at = String.valueOf(comEssayReply.getReply_pub_time());
        this.is_anonymous = (comEssayReply.getUser_anony() == 1) ? true : false;
        this.modified_at = String.valueOf(comEssayReply.getReply_edit_time());
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

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

    public RichTextDTOResponse getBody() {
        return body;
    }

    public void setBody(RichTextDTOResponse body) {
        this.body = body;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}
