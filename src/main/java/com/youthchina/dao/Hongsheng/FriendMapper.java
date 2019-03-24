package com.youthchina.dao.Hongsheng;

import com.youthchina.domain.tianjian.ComFriendRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FriendMapper {

    ComFriendRelation addFriendApplication(ComFriendRelation comFriendRelation);

    ComFriendRelation getFriendApplication(Integer id);

    ComFriendRelation updateFriendApplication(ComFriendRelation comFriendRelation);

    void deleteFriendApplication(Integer id);

}
