package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface CommunityMapper {
    int addEssay(ComEssay essay);

    int deleteEssay(@Param("essayId") Integer essay_id, @Param("isDeleteTime") Timestamp delete_time);

    int updateEssay(ComEssay essay);

    ComEssay getEssay(Integer essay_id);

    List<ComEssay> getEssayList(List<Integer> essayId);

    List<ComEssay> getEssayLatest();

    int saveFriendsRelation(ComFriendRelation comFriendRelation);

    int saveFriendsRelationMap(ComFriendRelationMap comFriendRelationMap);

    int deleteFriend(@Param("comFriendRelation") ComFriendRelation comFriendRelation, @Param("own_id") Integer own_id);

    List<ComFriendRelation> getFriend(Integer own_id);

    int saveFriendGroup(ComFriendGroup cfg);

    int saveFriendGroupMap(ComFriendGroupMap cfgm);

    int updateFriendGroup(@Param("comFriendGroup") ComFriendGroup comFriendGroup, @Param("rela_id") Integer rela_id);

    List<ComFriendGroup> getFriendGroup(List<ComFriendRelation> comFriendRelation);

    List<ComEssay> getAllEssayUserAttention(@Param("user_id") Integer user_id);
}
