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

    int deleteEssay(@Param("essay_id") Integer essay_id, @Param("delete_time") Timestamp delete_time);

    int updateEssay(ComEssay essay);

    ComEssay getEssay(Integer essay_id);

    ComAuthorEssayMap getEssayAuthor(Integer essay_id);

    int updateEssayAuthor(ComAuthorEssayMap caem);

    int addEssayLabel(List<ComEssayLabelMap> cel);

    int deleteEssayLabel(Integer essay_id);

    int addEssayAuthor(ComAuthorEssayMap caem);

    int addFavoriteEssay(ComEssayAttention comessayattention);

    int addFavoriteEssayMap(ComEssayAttentionMap ceam);

    int deleteFavoriteEssay(@Param("essay_id") Integer essay_id, @Param("user_id") Integer user_id);

    ComEssayAttention getFavoriteEssayWhetherAtten(@Param("essay_id") Integer essay_id, @Param("user_id") Integer user_id);

    int addReply(ComEssayReply comessayanswer);

    int addEssayReplyMap(ComEssayReplyMap cerm);

    int updateReply(@Param("comessayreply") ComEssayReply comessayreply, @Param("essay_id") Integer essay_id);

    int deleteReply(@Param("essay_id") Integer essay_id, @Param("user_id") Integer user_id, @Param("reply_level") Integer reply_level);

    List<ComEssayReply> getReply(Integer essay_id);

    int addReplyEvaluate(ComReplyEvaluate comreplyevaluate);

    int addReplyEvaluateMap(ComReplyEvaluateMap crem);

    int updateReplyEvaluate(@Param("comreplyevaluate") ComReplyEvaluate comreplyevaluate, @Param("reply_id") Integer reply_id);

    List<ComReplyEvaluate> getReplyEvaluate(Integer reply_id);

    List<ComEssay> getEssayLatest();

    List<ComEssayReply> getEssayReply(Integer essay_id);

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
