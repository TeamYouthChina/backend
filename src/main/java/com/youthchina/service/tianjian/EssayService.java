package com.youthchina.service.tianjian;

import com.youthchina.domain.tianjian.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.DomainCRUDService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mapper.tianjian on 11/8/18.
 */
public interface EssayService extends DomainCRUDService<User, Integer> {
//    public CompanyInfo getCompanyInformation(String company_id);
//
//    public StuCollect getFavoriteCompany(StuCollect company);
//
//    public int addFavoriteCompany(StuCollect company);
//
//    public JobInfo getJobInformation(String job_id);
//
//    public int deleteFavoriteCompany(StuCollect deletefavoritecompany);
//
//    public int addFavoriteJob(StuCollect job);
//
//    public int deleteFavoriteJob(StuCollect deletefavoritejob);
    /**
     * 添加文章和标签和作者
     * param ComEssay essay, List<Integer> lab_num, Integer user_id
     * return 0 or 1
     */
    public int addEssay(ComEssay essay, List<Integer> lab_num, Integer user_id, Integer rela_type, Integer rela_id);
    /**
     * 删除文章
     * param Integer essay_id
     * return 0 or 1
    */
    public int deleteEssay(Integer essay_id, Timestamp delete_time);

     /**
     * 更新文章
     * 更新文章和文章作者对应关系
     * 更新文章标签
     * param ComEssay essay, Integer user_id
     * return 1
     */
    public int updateEssay(ComEssay essay);
    /**
     * 更新文章作者及类型
     * param Integer essay_id, Integer user_id
     * return 1
     */
    public int updateEssayAuthor(ComAuthorEssayMap comAuthorEssayMap );
    /**
     * 更新文章标签
     * param ComEssay essay
     * return 1
     */
    public int updateEssayLabel(Integer essay_id, Integer user_id, List<Integer> lab_num);
      /**
     * 根据文章id获取文章
     * param Integer essay_id
     * return ComEssay
     */
       public ComEssay getEssay(Integer essay_id);

    /**
     * 根据文章id获取文章作者
     * param Integer essay_id
     * return ComEssayMap
     */
      public ComAuthorEssayMap getEssayAuthor(Integer essay_id);

     /**
     * 添加文章关注
     * 添加文章关注和文章id对应关系
     * param ComEssayAttention comessayattention Integer essay_id
     * return 0 or 1
     */
        public int addFavoriteEssay(ComEssayAttention comessayattention, Integer essay_id);

     /**
     * 删除文章关注
     * param Integer essay_id  Integer user_id
     * return 0 or 1
     */
       public int deleteFavoriteEssay(Integer essay_id, Integer user_id);

    /**
     * 获取文章是否被一个特定用户关注
     * param Integer essay_id  Integer user_id
     * return 0 or 1
     */
        public int getFavoriteEssayWhetherAtten(Integer essay_id, Integer user_id);

   /**
     * 添加评论
     * param ComEssayReply comessayanswer
     * return 0 or 1
     */
       public int addReply(ComEssayReply comessayanswer, Integer essay_id, Integer reply_level);
     /**
     * 更新评论
     * param ComEssayReply comessayreply Integer essay_id
     * return 0 or 1
     */
       public int updateReply(ComEssayReply comessayreply, Integer essay_id);
     /**
     * 删除评论
     * param Integer essay_id, Integer user_id
     * return 0 or 1
     */
       public int deleteReply(Integer essay_id, Integer user_id, Integer reply_level);

      /**
     * 查找文章的所有评论
     * param Integer essay_id
     * return 0 or 1
     */
       public List<ComEssayReply> getReply(Integer essay_id);

   /**
     * 添加回复评价
     * param ComReplyEvaluate comreplyevaluate
     * return 0 or 1
     */
      public int addReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id);

    /**
     * 更改回复评价
     * param ComReplyEvaluate comreplyevaluate Integer reply_id
     * return 0 or 1
     */
       public int updateReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id);

    /**
     * 根据回复id 获取它的所有评价
     * param ComReplyEvaluate comreplyevaluate Integer reply_id
     * return 0 or 1
     */
      public List<ComReplyEvaluate> getReplyEvaluate(Integer reply_id);

   /**
     * 获取最新10个文章
     * param
     * return List<ComEssay>
     */
       public List<ComEssay> getEssayLatest();
   /**
     * 根据文章id 获取所有回复
     * param Integer essay_id
     * return List<ComEssayReply>
     */
       public List<ComEssayReply> getEssayReply(Integer essay_id);
}