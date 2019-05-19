package com.youthchina.dto.community.friend;

import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.dto.RequestDTO;


public class FriendApplicationRequestDTO implements RequestDTO<ComFriendApply> {
    private String message;

    public FriendApplicationRequestDTO() {
    }
    public FriendApplicationRequestDTO(ComFriendApply comFriendApply) {
        this.message = comFriendApply.getApplyMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public ComFriendApply convertToDomain() {
        {
            return new ComFriendApply(this);
        }
    }
}
