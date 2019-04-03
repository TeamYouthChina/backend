package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendGroupMap;
import com.youthchina.domain.tianjian.ComFriendRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("friendsService")
@Transactional
public class FriendsServiceImpl implements FriendsService {
    @Autowired
    CommunityMapper friendsMapper;

    @Override
    public int saveFriend(ComFriendRelation comFriendRelation) {
        friendsMapper.saveFriendsRelation(comFriendRelation);

        ComFriendRelation comFriendRelation1 = new ComFriendRelation();
        comFriendRelation1.setAddTime(comFriendRelation.getAddTime());
        comFriendRelation1.setIsDelete(0);
        comFriendRelation1.setUserId(comFriendRelation.getFriendId());
        comFriendRelation1.setFriendId(comFriendRelation.getUserId());
        return friendsMapper.saveFriendsRelation(comFriendRelation1);
    }

    @Override
    public int deleteFriend(ComFriendRelation comFriendRelation) {
        ComFriendRelation comFriendRelationAnother = new ComFriendRelation();
        comFriendRelationAnother.setUserId(comFriendRelation.getFriendId());
        comFriendRelationAnother.setIsDeleteTime(comFriendRelation.getIsDeleteTime());
        comFriendRelationAnother.setFriendId(comFriendRelation.getUserId());
        friendsMapper.deleteFriend(comFriendRelation);
        return friendsMapper.deleteFriend(comFriendRelationAnother);
    }

    @Override
    public List<ComFriendRelation> getFriend(Integer own_Id) {
        return friendsMapper.getFriend(own_Id);
    }

    @Override
    public int saveFriendGroup(ComFriendGroup comFriendGroup, Integer rela_Id) {
        friendsMapper.saveFriendGroup(comFriendGroup);
        ComFriendGroupMap cfgm = new ComFriendGroupMap();
        cfgm.setGroup_id(comFriendGroup.getGroup_id());
        cfgm.setRela_id(rela_Id);
        return friendsMapper.saveFriendGroupMap(cfgm);
    }

    @Override
    public int updateFriendGroup(ComFriendGroup comFriendGroup, Integer rela_Id) {
        return friendsMapper.updateFriendGroup(comFriendGroup, rela_Id);
    }

    @Override
    public List<ComFriendGroup> getFriendGroup(Integer own_Id) {
        List<ComFriendRelation> comFriendRelation = friendsMapper.getFriend(own_Id);
        List<ComFriendGroup> comFriendGroups = friendsMapper.getFriendGroup(comFriendRelation);
        return comFriendGroups;
    }
}
