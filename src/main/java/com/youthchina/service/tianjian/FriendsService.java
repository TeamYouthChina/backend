package com.youthchina.service.tianjian;

import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendRelation;

import java.util.List;

public interface FriendsService {
    /*
     * 添加好友关系
     * parameter ComFriendRelation comfriendrelation, Integer own_id
     * return 0 or 1;
     * */
    public int saveFriend(ComFriendRelation comFriendRelation, Integer own_Id);

    /*
     * 删除好友关系
     * parameter ComFriendRelation comfriendrelation, Integer own_id
     * return 0 or 1;
     * */
    public int deleteFriend(ComFriendRelation comFriendRelation, Integer own_Id);

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


}
