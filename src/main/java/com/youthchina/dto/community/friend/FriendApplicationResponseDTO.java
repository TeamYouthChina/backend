package com.youthchina.dto.community.friend;

import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.domain.zhongyang.User;


public class FriendApplicationResponseDTO {
    private Integer reference_id;
    private String create_at;
    private User applicant;
    private boolean is_read;
    public FriendApplicationResponseDTO(){}
    public FriendApplicationResponseDTO(ComFriendApply comFriendApply){
        this.reference_id = comFriendApply.getApplyId();
        this.create_at = comFriendApply.getFriApplyTime().toString();
        this.is_read = comFriendApply.getFriIsRead()==0? false:true;
        this.applicant = comFriendApply.getUser();
    }

    public Integer getReference_id() {
        return reference_id;
    }

    public void setReference_id(Integer reference_id) {
        this.reference_id = reference_id;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
