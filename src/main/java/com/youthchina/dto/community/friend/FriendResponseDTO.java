package com.youthchina.dto.community.friend;

import com.youthchina.domain.tianjian.ComFriendApply;


public class FriendResponseDTO {
    private Integer reference_id;
    private String create_at;
    public FriendResponseDTO(){}
    public FriendResponseDTO(ComFriendApply comFriendApply){
        this.reference_id = comFriendApply.getApplyId();
        this.create_at = comFriendApply.getFriApplyTime().toString();
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
}
