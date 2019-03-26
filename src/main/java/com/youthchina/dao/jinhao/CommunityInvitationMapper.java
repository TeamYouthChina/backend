package com.youthchina.dao.jinhao;

import org.apache.ibatis.annotations.Param;

public interface CommunityInvitationMapper {
//    CommunityInvitation get(Integer id);
    void add(@Param("type") Integer targetType, @Param("targetId") Integer targetId,
             @Param("invitUserId") Integer invitUserId, @Param("invitedUserID") Integer invitedUserId);
    void update(Integer id);
//    void delete(Integer id);
    Integer checkIfInvitationExist(Integer id);
}
