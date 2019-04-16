package com.youthchina.service.community;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendGroupMap;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.exception.zhongyang.exception.ConflictException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
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

    @Resource
    UserMapper userMapper;

    @Override
    public void addFriend(ComFriendRelation comFriendRelation) throws NotFoundException {
        //判断A是否向B发过好友申请
        ComFriendApply comFriendApply = friendsMapper.getFriendApply(comFriendRelation.getFriendId(),comFriendRelation.getUserId());
        if(comFriendApply!=null&&comFriendApply.getFriApplyAccept()==1){
            friendsMapper.saveFriendsRelation(comFriendRelation);
            ComFriendRelation comFriendRelation1 = new ComFriendRelation();
            comFriendRelation1.setUserId(comFriendRelation.getFriendId());
            comFriendRelation1.setFriendId(comFriendRelation.getUserId());
            friendsMapper.saveFriendsRelation(comFriendRelation1);
        }else
            throw new NotFoundException(404, 404, "the friend application is not exist");//todo
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
    public ComFriendApply addFriendApply(ComFriendApply comFriendApply) throws ConflictException {
        ComFriendApply comFriendApply1 = friendsMapper.getFriendApply(comFriendApply.getUserId(),comFriendApply.getFriendId());
        if(comFriendApply1!=null)
            throw new ConflictException(409, 409, "Application is already existing");
        friendsMapper.addFriendApply(comFriendApply);
        ComFriendApply comFriendApply2 = friendsMapper.getFriendApplication(comFriendApply.getApplyId());
        comFriendApply2.setUser(userMapper.findOne(comFriendApply2.getUserId()));
        return comFriendApply2;
    }

    @Override
    public List<ComFriendApply>  getAllFriendApply(Integer userId) {
        List<ComFriendApply> comFriendApplies = friendsMapper.getAllFriendApply(userId);
        Iterator it = comFriendApplies.iterator();
        while(it.hasNext()){
            ComFriendApply comFriendApply = (ComFriendApply) it.next();
            comFriendApply.setUser(userMapper.findOne(comFriendApply.getUserId()));
        }
        return comFriendApplies;
    }

    @Override
    public ComFriendApply getFriendApplication(Integer applicationId) {
        ComFriendApply comFriendApply = friendsMapper.getFriendApplication(applicationId);
        comFriendApply.setUser(userMapper.findOne(comFriendApply.getUserId()));
        return comFriendApply;
    }

    @Override
    public void changeApplicationStatus(ComFriendApply comFriendApply) {
       friendsMapper.changeApplicationStatus(comFriendApply);
    }

    @Override
    public ComFriendApply getFriendApply(Integer userId, Integer friendId) {
        ComFriendApply comFriendApply = friendsMapper.getFriendApply(userId,friendId);
        comFriendApply.setUser(userMapper.findOne(userId));
        return comFriendApply;
    }
}
