package com.youthchina.dto.community.friend;

import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.dto.security.UserDTO;

import java.sql.Timestamp;


public class FriendApplicationResponseDTO {
    private Integer reference_id;
    private Timestamp create_at;
    private UserDTO applicant;
    private boolean is_read;

    public FriendApplicationResponseDTO() {
    }

    public FriendApplicationResponseDTO(ComFriendApply comFriendApply) {
        this.reference_id = comFriendApply.getApplyId();
        this.create_at = comFriendApply.getFriApplyTime();
        this.is_read = comFriendApply.getFriIsRead() == 0 ? false : true;
        this.applicant = new UserDTO();
        this.applicant.convertToDTO(comFriendApply.getUser());

    }

    public Integer getReference_id() {
        return reference_id;
    }

    public void setReference_id(Integer reference_id) {
        this.reference_id = reference_id;
    }

    @JsonTimeStamp
    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public UserDTO getApplicant() {
        return applicant;
    }

    public void setApplicant(UserDTO applicant) {
        this.applicant = applicant;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
