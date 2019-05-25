package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.*;
import com.youthchina.util.permission.HasOwnerMapper;
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
public interface CommunityMapper extends HasOwnerMapper {
    int addEssay(ComEssay essay);

    int deleteEssay(@Param("essayId") Integer essay_id, @Param("isDeleteTime") Timestamp delete_time);

    int updateEssay(ComEssay essay);

    ComEssay getEssay(@Param("essayId") Integer essayId);

    List<ComEssay> getEssayList(List<Integer> essayId);

    List<ComEssay> getEssayLatest();

    Integer countEssay();

    int saveFriendsRelation(ComFriendRelation comFriendRelation);

    int deleteFriend(@Param("comFriendRelation") ComFriendRelation comFriendRelation);

    List<ComFriendRelation> getFriend(@Param("userId") Integer userId);

    int saveFriendGroup(ComFriendGroup cfg);

    int saveFriendGroupMap(ComFriendGroupMap cfgm);

    int updateFriendGroup(@Param("comFriendGroup") ComFriendGroup comFriendGroup, @Param("relaId") Integer rela_id);

    List<ComFriendGroup> getFriendGroup(List<ComFriendRelation> comFriendRelation);

    void addFriendApply(ComFriendApply comFriendApply);

    List<ComFriendApply> getAllFriendApply(@Param("userId") Integer userId);

    ComFriendApply getFriendApply(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

    List<ComEssay> getAllEssayByUserId(@Param("userId") Integer user_id);

    ComFriendApply getFriendApplication(@Param("applicationId") Integer applicationId);

    void changeApplicationStatus(ComFriendApply comFriendApply);
}
