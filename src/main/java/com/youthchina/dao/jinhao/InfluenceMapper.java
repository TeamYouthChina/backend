package com.youthchina.dao.jinhao;

import com.youthchina.domain.Qinghong.EducationInfo;
import com.youthchina.domain.Qinghong.Work;
import com.youthchina.domain.jinhao.communityQA.Influence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface InfluenceMapper {
    //拿到用户基本信息和得到的点赞数
    Influence getInfluenceByUserId(Integer uesr_id);

    //拿到好友的影响力分数
    Float getFriendInfluencePoints(Integer friend_user_id);

    //拿到和好友的互动
    Influence getInteraction(@Param("user_id") Integer user_id,
                             @Param("friend_user_id") Integer friend_user_id);

    Integer getBestEducation(@Param("educationInfos") List<EducationInfo> educationInfos);

    Integer getBestWork(@Param("works") List<Work> works);

}
