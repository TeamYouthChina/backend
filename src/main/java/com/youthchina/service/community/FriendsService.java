package com.youthchina.service.community;

import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.exception.zhongyang.exception.ConflictException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.util.List;

public interface FriendsService {
    /*
     * 添加好友关系
     * parameter ComFriendRelation comfriendrelation, Integer own_id
     * return 0 or 1;
     * */
    public void addFriend(ComFriendRelation comFriendRelation) throws NotFoundException;

    /*
     * 删除好友关系
     * parameter ComFriendRelation comfriendrelation, Integer own_id
     * return 0 or 1;
     * */
    public void deleteFriend(ComFriendRelation comFriendRelation) throws NotFoundException;

    /*
     * 通过id获取他的所有好友关系
     * parameter Integer own_id
     * return 0 or 1;
     * */
    public List<ComFriendRelation> getFriend(Integer own_Id);

    /*
     * 给好友添加分组
     * parameter Integer own_id
     * return 0 or 1;
     * */
    public int saveFriendGroup(ComFriendGroup comFriendGroup, Integer rela_Id);

    /*
     * 更改好友分组
     * parameter Integer own_id, Integer rela_id
     * return 0 or 1;
     * */
    public int updateFriendGroup(ComFriendGroup comFriendGroup, Integer rela_Id);

    /*
     * 查找好友分组
     * parameter Integer own_id
     * return List<ComFriendGroup>;
     * */
    public List<ComFriendGroup> getFriendGroup(Integer own_Id);
    /*
     * 添加好友申请
     * parameter Integer own_id
     * */
     public ComFriendApply addFriendApply(ComFriendApply comFriendApply) throws ConflictException;
    /*
     * 获取所有的好友申请
     * parameter Integer own_id
     * */
    public List<ComFriendApply> getAllFriendApply(Integer userId);
    /*
     * 获取好友申请
     * parameter Integer userId, Integer friendId
     * */
    public ComFriendApply getFriendApply(Integer userId, Integer friendId);
    /*
     * 获取好友申请
     * parameter Integer applicationId
     * */
    public ComFriendApply  getFriendApplication(Integer applicationId) throws NotFoundException;
    /*
     * 更改申请状态
     * parameter Integer applicationId
     * */
    public void changeApplicationStatus(ComFriendApply comFriendApply);
}
