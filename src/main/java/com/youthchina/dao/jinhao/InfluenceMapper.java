package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.Influence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface InfluenceMapper {
    //拿到用户基本信息和得到的点赞数
    Influence getInfluenceByUserId(Integer uesr_id);
    //拿到好友的影响力分数
    Integer getFriendInfluencePoints(Integer friend_user_id);
    //拿到和好友的互动
    Influence getInteraction(@Param("user_id")Integer user_id,
                             @Param("friend_user_id")Integer friend_user_id);
}
