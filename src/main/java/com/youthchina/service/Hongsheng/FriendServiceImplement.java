package com.youthchina.service.Hongsheng;

/*
import com.youthchina.dao.com.youthchina.Hongsheng.FriendMapper;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.tianjian.ComFriendRelationMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("friendService")
@Transactional
public class FriendServiceImplement extends FriendService {
    @Autowired
    FriendMapper friendMapper;

    @Override
    public ComFriendRelation add(ComFriendRelation comFriendRelation, Integer own_Id) {
        friendMapper.addFriendApplication(comFriendRelation);
        ComFriendRelationMap comFriendRelationMap = new ComFriendRelationMap();
        comFriendRelationMap.setRela_id(comFriendRelation.getRela_id());
        comFriendRelationMap.setUser_id(own_Id);


    }

}
*/