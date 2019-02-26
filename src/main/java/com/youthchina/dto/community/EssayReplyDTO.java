package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RichTextDTO;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class EssayReplyDTO {
    private Integer id;
    private User user;
    private RichTextDTO body;
    private String create_at;
    private boolean is_anonymous;

    @Autowired
    UserServiceImpl userService;
    public EssayReplyDTO(){}

    public EssayReplyDTO(ComEssayReply comEssayReply){
        this.id = comEssayReply.getReply_id();
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssayReply.getReply_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.create_at = String.valueOf(comEssayReply.getReply_pub_time());
        this.is_anonymous = (comEssayReply.getUser_anony()==1)? true:false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
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
