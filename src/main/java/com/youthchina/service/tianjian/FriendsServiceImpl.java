package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendGroupMap;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.exception.zhongyang.ConflictException;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service("friendsService")
@Transactional
public class FriendsServiceImpl implements FriendsService {
    @Resource
    CommunityMapper friendsMapper;

    @Override
    public int saveFriend(ComFriendRelation comFriendRelation) {
        friendsMapper.saveFriendsRelation(comFriendRelation);
        ComFriendRelation comFriendRelation1 = new ComFriendRelation();
        comFriendRelation1.setUserId(comFriendRelation.getFriendId());
        comFriendRelation1.setFriendId(comFriendRelation.getUserId());
        return friendsMapper.saveFriendsRelation(comFriendRelation1);
    }

    @Override
    public void deleteFriend(ComFriendRelation comFriendRelation) throws NotFoundException {
        List<ComFriendRelation> comFriendRelations = friendsMapper.getFriend(comFriendRelation.getUserId());
        boolean flag = false;
        Iterator it = comFriendRelations.iterator();
        while(it.hasNext()){
            ComFriendRelation comFriendRelation1 = (ComFriendRelation) it.next();
            if(comFriendRelation1.getFriendId()==comFriendRelation.getFriendId()&&comFriendRelation1.getUserId()==comFriendRelation.getUserId())
                flag = true;
        }
        if (flag==false)
            throw new NotFoundException(404, 404, "this relation is not exist");//todo
        ComFriendRelation comFriendRelationAnother = new ComFriendRelation();
        comFriendRelationAnother.setUserId(comFriendRelation.getFriendId());
        comFriendRelationAnother.setIsDeleteTime(comFriendRelation.getIsDeleteTime());
        comFriendRelationAnother.setFriendId(comFriendRelation.getUserId());
        friendsMapper.deleteFriend(comFriendRelation);
        friendsMapper.deleteFriend(comFriendRelationAnother);
    }

    @Override
    public List<ComFriendRelation> getFriend(Integer own_Id) {
        return friendsMapper.getFriend(own_Id);
    }

    @Override
    public int saveFriendGroup(ComFriendGroup comFriendGroup, Integer relaId) {
        friendsMapper.saveFriendGroup(comFriendGroup);
        ComFriendGroupMap cfgm = new ComFriendGroupMap();
        cfgm.setGroupId(comFriendGroup.getGroupId());
        cfgm.setRelaId(relaId);
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

    @Override
    public void addFriendApply(ComFriendApply comFriendApply) throws ConflictException {
        ComFriendApply comFriendApply1 = friendsMapper.getFriendApply(comFriendApply.getUserId(),comFriendApply.getFriendId());
        if(comFriendApply1!=null)
            throw new ConflictException(409, 409, "Application is already existing");
        friendsMapper.addFriendApply(comFriendApply);
    }

    @Override
    public List<ComFriendApply>  getAllFriendApply(Integer userId) {
        return friendsMapper.getAllFriendApply(userId);
    }

    @Override
    public ComFriendApply getFriendApply(Integer userId, Integer friendId) {
        return friendsMapper.getFriendApply(userId,friendId);
    }
}
