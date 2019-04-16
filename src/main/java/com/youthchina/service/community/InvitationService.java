package com.youthchina.service.community;

import com.youthchina.domain.jinhao.property.Invitable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

public interface InvitationService {
    void add(Invitable entity, Integer userId, Integer invitedUserId) throws NotFoundException;
    void accept(Invitable entity, Integer userId, Integer invitedUserId) throws NotFoundException;
}
