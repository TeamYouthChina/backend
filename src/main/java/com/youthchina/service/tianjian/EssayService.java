package com.youthchina.service.tianjian;

import com.youthchina.domain.tianjian.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotFoundException;
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
    public void addEssay(ComEssay essay) throws NotFoundException;

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
    public int updateEssay(ComEssay essay) throws NotFoundException;

    /**
     * 根据文章id获取文章
     * param Integer essay_id
     * return ComEssay
     */
    public ComEssay getEssay(Integer essay_id) throws NotFoundException;

    /**
     * 根据文章List<id>获取文章List
     * param Integer essay_id
     * return ComEssay
     */
    public List<ComEssay> getEssay(List<Integer> essayId) throws NotFoundException;

    /**
     * 获取最新10个文章
     * param
     * return List<ComEssay>
     */
    public List<ComEssay> getEssayLatest();



    /**
     * 根据用户id 获取他关注的所有文章
     * param Integer user_id
     * return List<ComEssay>
     */
    public List<ComEssay> getAllEssayUserAttention(Integer user_id);


}