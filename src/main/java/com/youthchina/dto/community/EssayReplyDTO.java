package com.youthchina.dto.community;

import com.youthchina.domain.tianjian.ComEssayReply;


public class EssayReplyDTO {
    private String body;
    private boolean isAnonymous;

    public EssayReplyDTO(ComEssayReply comEssayReply){
        this.body = comEssayReply.getReply_content();
        this.isAnonymous = (comEssayReply.getUser_anony()==0)? false:true;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

}
