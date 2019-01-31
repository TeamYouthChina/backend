package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.tianjian.ComReplyEvaluate;
import com.youthchina.domain.tianjian.PersonInfluencePoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface InfluenceMapper {
    List<AnswerEvaluate> agreementGotInAnswer(Integer user_id);
    List<CommentEvaluate> agreementGotInComment(Integer user_id);
    List<DiscussEvaluate> agreementGotInDiscuss(Integer user_id);
    List<VideoEvaluate> agreeementGotInVideo(Integer user_id);
    List<ComReplyEvaluate> agreementGotInEssayReply(Integer user_id);
    List<PersonInfluencePoint> getFriendsInfluence(Integer friend_user_id);
    List<AnswerEvaluate> interactionInAnswerEvaluate(@Param("user_id")Integer user_id,
                                                     @Param("friend_user_id")Integer friend_user_id);
    List<CommentEvaluate> interactionInCommentEvaluate(@Param("user_id")Integer user_id,
                                                       @Param("friend_user_id")Integer friend_user_id);
    List<DiscussEvaluate> interationInDiscussEvaluate(@Param("user_id")Integer user_id,
                                                      @Param("friend_user_id")Integer friend_user_id);
    List<VideoEvaluate> interactionInDiscussEvaluate(@Param("user_id")Integer user_id,
                                                     @Param("friend_user_id")Integer friend_user_id);
    List<ComReplyEvaluate> interactionInVideoReplyEvaluate(@Param("user_id")Integer user_id,
                                                           @Param("friend_user_id")Integer friend_user_id);
    Influence getInfluenceByUserId(Integer uesr_id);

    Integer getFriendInfluencePoints(Integer friend_user_id);

    Influence getInteraction(@Param("user_id")Integer user_id,
                             @Param("friend_user_id")Integer friend_user_id);
}
