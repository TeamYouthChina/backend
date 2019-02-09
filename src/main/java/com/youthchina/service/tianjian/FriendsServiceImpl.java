package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendGroupMap;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.tianjian.ComFriendRelationMap;
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
    public int saveFriend(ComFriendRelation comFriendRelation, Integer own_Id) {
        friendsMapper.saveFriendsRelation(comFriendRelation);
        ComFriendRelationMap comFriendRelationMap = new ComFriendRelationMap();
        comFriendRelationMap.setRela_id(comFriendRelation.getRela_id());
        comFriendRelationMap.setUser_id(own_Id);
        friendsMapper.saveFriendsRelationMap(comFriendRelationMap);

        ComFriendRelation comFriendRelation1 = new ComFriendRelation();
        comFriendRelation1.setAdd_time(comFriendRelation.getAdd_time());
        comFriendRelation1.setIs_delete(0);
        comFriendRelation1.setUser_id(own_Id);
        friendsMapper.saveFriendsRelation(comFriendRelation1);
        ComFriendRelationMap comFriendRelationMap1 = new ComFriendRelationMap();
        comFriendRelationMap1.setRela_id(comFriendRelation1.getRela_id());
        comFriendRelationMap1.setUser_id(comFriendRelation.getUser_id());
        return friendsMapper.saveFriendsRelationMap(comFriendRelationMap1);
    }

    @Override
    public int deleteFriend(ComFriendRelation comFriendRelation, Integer own_Id) {
        ComFriendRelation comFriendRelationAnother= new ComFriendRelation();
        comFriendRelationAnother.setUser_id(own_Id);
        comFriendRelationAnother.setIs_delete_time(comFriendRelation.getIs_delete_time());
        friendsMapper.deleteFriend(comFriendRelation,own_Id);
        return friendsMapper.deleteFriend(comFriendRelationAnother,comFriendRelation.getUser_id());
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
        return friendsMapper.updateFriendGroup(comFriendGroup,rela_Id);
    }

    @Override
    public  List<ComFriendGroup> getFriendGroup(Integer own_Id) {
        List<ComFriendRelation>  comFriendRelation = friendsMapper.getFriend(own_Id);
        List<ComFriendGroup> comFriendGroups = friendsMapper.getFriendGroup(comFriendRelation);
        return comFriendGroups;
    }
}
