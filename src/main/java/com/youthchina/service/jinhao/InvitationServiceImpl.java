package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.CommunityInvitationMapper;
import com.youthchina.domain.jinhao.CommunityInvitation;
import com.youthchina.domain.jinhao.property.Invitable;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Resource
    CommunityInvitationMapper communityInvitationMapper;


    @Override
    public void add(Invitable entity, Integer userId, Integer invitedUserId) {
        Integer type = entity.getInviteTargetType();
        Integer id = entity.getId();
        communityInvitationMapper.add(type,id,userId,invitedUserId);
    }

    @Override
    public void accept(Integer id)throws NotFoundException {
        if(communityInvitationMapper.checkIfInvitationExist(id) == null){
            throw new NotFoundException(404,404,"没有找到这个邀请");
        }
        communityInvitationMapper.update(id);
    }

    @Override
    public CommunityInvitation get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<CommunityInvitation> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public CommunityInvitation update(CommunityInvitation communityInvitation) throws NotFoundException {
        return null;
    }

    @Override
    public CommunityInvitation add(CommunityInvitation entity) throws NotFoundException {
        return null;
    }
}
