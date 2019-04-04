package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.CommunityInvitation;
import com.youthchina.domain.jinhao.property.Invitable;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

public interface InvitationService extends DomainCRUDService<CommunityInvitation, Integer> {
    void add(Invitable entity, Integer userId, Integer invitedUserId) throws NotFoundException;
    void accept(Invitable entity, Integer userId, Integer invitedUserId) throws NotFoundException;
}
