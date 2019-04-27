package com.youthchina.service.community;

import com.youthchina.dao.jinhao.CommunityInvitationMapper;
import com.youthchina.domain.jinhao.property.Invitable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Resource
    CommunityInvitationMapper communityInvitationMapper;


    @Override
    public void add(Invitable entity, Integer userId, Integer invitedUserId) throws NotFoundException{
        Integer type = entity.getInviteTargetType();
        Integer id = entity.getId();
        Integer invitId = communityInvitationMapper.checkIfInvitationExist(type,id,userId,invitedUserId);
        if(invitId != null){
            throw new NotFoundException(4040,404,"you have invited this user");//todo
        }
        communityInvitationMapper.add(type,id,userId,invitedUserId);
    }

    @Override
    public void accept(Invitable entity, Integer userId, Integer invitedUserId)throws NotFoundException {
        Integer type = entity.getInviteTargetType();
        Integer id = entity.getId();
        Integer invitId = communityInvitationMapper.checkIfInvitationExist(type,id,userId,invitedUserId);
        if(invitId == null){
            throw new NotFoundException(4040,404,"没有找到这个邀请");//todo
        }
        communityInvitationMapper.update(invitId);
    }

}
