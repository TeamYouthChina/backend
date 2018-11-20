package com.youthchina.dao.Tianjian;

import com.youthchina.domain.Tianjian.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface UserMapper {
    CompanyInfo getCompanyInformation(String company_id);

    StuCollect getFavoriteCompany(StuCollect company);

    int addFavoriteCompany(StuCollect s);

    int deleteFavoriteCompany(StuCollect s);

    JobInfo getJobInformation(String job_id);

    int addFavoriteJob(StuCollect s);

    int deleteFavoriteJob(StuCollect s);


    /**
     * 添加文章
     */
    int addEssay(ComEssay essay);

    int deleteEssay(Integer essay_id);

    int updateEssay(ComEssay essay);

    ComEssay getEssay(Integer essay_id);

    int updateEssayAuthor(Integer user_id, Integer essay_id);

    int addEssayLabel(List<Integer> lab_num, Integer essay_id);

    int deleteEssayLabel(Integer essay_id);

    int addEssayAuthor(Integer essay_id, Integer user_id);

    int addFavoriteEssay(ComEssayAttention comessayattention);

    int addFavoriteEssayMap(Integer atten_id, Integer essay_id);

    int deleteFavoriteEssay(Integer essay_id, Integer user_id);

    ComEssayAttention getFavoriteEssayWhetherAtten(Integer essay_id, Integer user_id);

    int addReply(ComEssayReply comessayanswer);

    int addEssayReplyMap(Integer essay_id,Integer reply_id);

    int updateReply(ComEssayReply comessayanswer, Integer essay_id);

    int deleteReply(Integer essay_id, Integer user_id);

    int getReply(Integer essay_id);

    int addReplyEvaluate(ComReplyEvaluate comreplyevaluate);

    int addReplyEvaluateMap(Integer evaluate_id, Integer reply_id);

    int updateReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id);

    List<ComReplyEvaluate> getReplyEvaluate(Integer reply_id);

    List<ComEssay> getEssayLatest();

    List<ComEssayReply> getEssayReply(Integer essay_id);


}
