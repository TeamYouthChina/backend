package com.youthchina.service.Hongsheng;

import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

public interface FriendService extends DomainCRUDService<ComFriendRelation, Integer> {

    /*
     * 添加好友关系 发出申请
     * parameter ComFriendRelation comfriendrelation
     * */
    @Override
    ComFriendRelation add(ComFriendRelation comFriendRelation);

    /*
     * 得到申请
     * parameter Integer id
     * */
    @Override
    ComFriendRelation get(Integer id)throws NotFoundException;

    /*
     * 更新申请
     * parameter Integer id
     * */
    @Override
    ComFriendRelation update(ComFriendRelation comFriendRelation)throws NotFoundException;

    /*
     * 删除申请
     * parameter Integer id
     * */
    @Override
    void delete(Integer id) throws NotFoundException;
}
