package com.youthchina.dao.Tianjian;

import com.youthchina.domain.Tianjian.*;
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
public interface UserMapper {
//    CompanyInfo getCompanyInformation(String company_id);
//
//    StuCollect getFavoriteCompany(StuCollect company);
//
//    int addFavoriteCompany(StuCollect s);
//
//    int deleteFavoriteCompany(StuCollect s);
//
//    JobInfo getJobInformation(String job_id);
//
//    int addFavoriteJob(StuCollect s);
//
//    int deleteFavoriteJob(StuCollect s);

    /**
     * 添加文章
     */
    int addEssay(ComEssay essay);

      int deleteEssay(@Param("essay_id")Integer essay_id, @Param("delete_time") Timestamp delete_time);

       int updateEssay(ComEssay essay);

      ComEssay getEssay(Integer essay_id);

      int updateEssayAuthor(ComAuthorEssayMap caem);

     int addEssayLabel(List<ComEssayLabel> cel);

     int deleteEssayLabel(Integer essay_id);

    int addEssayAuthor(ComAuthorEssayMap caem);

      int addFavoriteEssay(ComEssayAttention comessayattention);

      int addFavoriteEssayMap(ComEssayAttentionMap ceam);

       int deleteFavoriteEssay(@Param("essay_id")Integer essay_id, @Param("user_id")Integer user_id);

        ComEssayAttention getFavoriteEssayWhetherAtten(@Param("essay_id")Integer essay_id, @Param("user_id")Integer user_id);

       int addReply(ComEssayReply comessayanswer);

       int addEssayReplyMap(ComEssayReplyMap cerm);

       int updateReply(@Param("comessayreply")ComEssayReply comessayreply,@Param("essay_id")Integer essay_id);

       int deleteReply(@Param("essay_id")Integer essay_id, @Param("user_id")Integer user_id,@Param("reply_level")Integer reply_level);

       List<ComEssayReply> getReply(Integer essay_id);

       int addReplyEvaluate(ComReplyEvaluate comreplyevaluate);

       int addReplyEvaluateMap(ComReplyEvaluateMap crem);

       int updateReplyEvaluate(@Param("comreplyevaluate")ComReplyEvaluate comreplyevaluate, @Param("reply_id")Integer reply_id);

       List<ComReplyEvaluate> getReplyEvaluate(Integer reply_id);

       List<ComEssay> getEssayLatest();

       List<ComEssayReply> getEssayReply(Integer essay_id);

}
