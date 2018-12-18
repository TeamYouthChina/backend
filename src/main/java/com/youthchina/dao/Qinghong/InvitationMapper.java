package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.Invitation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface InvitationMapper {
    Invitation findInvitationMapper(Integer id);
}
