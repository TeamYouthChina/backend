package com.youthchina.service.Hongsheng;

import com.youthchina.dao.Hongsheng.FriendMapper;
import com.youthchina.domain.tianjian.ComFriendRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("friendService")
@Transactional
public class FriendServiceImplement extends FriendService {
    @Autowired
    FriendMapper friendMapper;

    @Override
    public ComFriendRelation add(ComFriendRelation comFriendRelation) {
        friendMapper.addFriendApplication(comFriendRelation);

    }

}
