package com.youthchina.service.Tianjian;

import com.youthchina.domain.Tianjian.*;
import com.youthchina.dao.Tianjian.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Get User by id.
     *
     * @param id id of User
     * @return User with id equals to param.
     */
    @Override
    public User get(Integer id) {
       // return mapper.findOne(id);
        return null;
    }

    @Override
    public List<User> get(List<Integer> id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User add(User user) {
        return null;
    }

    public CompanyInfo getCompanyInformation(String company_id){
        CompanyInfo companyInfo =  mapper.getCompanyInformation(company_id);
        return  companyInfo;
    }

    public StuCollect getFavoriteCompany(StuCollect company){
        return mapper.getFavoriteCompany(company);
    }

    public int addFavoriteCompany(StuCollect company){
        if(mapper.addFavoriteCompany(company)>0)
          return 1;
        else
            return 0;
    }

    public int addFavoriteJob(StuCollect Job){
        if(mapper.addFavoriteJob(Job)>0)
            return 1;
        else
            return 0;
    }

    public JobInfo getJobInformation(String job_id){
        return mapper.getJobInformation(job_id);
    }

    public int deleteFavoriteCompany(StuCollect deletefavoritecompany){
        return mapper.deleteFavoriteCompany(deletefavoritecompany);
    }

    @Override
    public int deleteFavoriteJob(StuCollect deletefavoritejob) {
        return mapper.deleteFavoriteJob(deletefavoritejob);
    }

    @Override
    public int addEssay(ComEssay essay,List<Integer> lab_num,Integer user_id) {
        int i = mapper.addEssay(essay);
        int essayid = essay.getEssay_id();
        mapper.addEssayLabel(lab_num,essayid);
        mapper.addEssayAuthor(essayid,user_id);
        return i;
    }

    @Override
    public int deleteEssay(Integer essay_id) {
        return mapper.deleteEssay(essay_id);
    }

    @Override
    public int updateEssay(ComEssay essay, Integer user_id, List<Integer> lab_num) {
         mapper.updateEssay(essay);
         mapper.updateEssayAuthor(user_id,essay.getEssay_id());
         mapper.deleteEssayLabel(essay.getEssay_id());
         int i = mapper.addEssayLabel(lab_num,essay.getEssay_id());
         return i;
    }

    @Override
    public ComEssay getEssay(Integer essay_id) {
        return mapper.getEssay(essay_id);
    }

    @Override
    public int addFavoriteEssay(ComEssayAttention comessayattention, Integer essay_id){
        mapper.addFavoriteEssay(comessayattention);
        int attenid = comessayattention.getAtten_id();
        mapper.addFavoriteEssayMap(attenid,essay_id);
        return 1;
    }

    @Override
    public int deleteFavoriteEssay(Integer essay_id, Integer user_id) {
        return mapper.deleteFavoriteEssay(essay_id, user_id);
    }

    @Override
    public int getFavoriteEssayWhetherAtten(Integer essay_id, Integer user_id) {
        ComEssayAttention c = mapper.getFavoriteEssayWhetherAtten(essay_id, user_id);
        if(c!=null)
            return 1;
        else
            return 0;

    }

    @Override
    public int addReply(ComEssayReply comessayanswer, Integer essay_id) {
        mapper.addReply(comessayanswer);
        return mapper.addEssayReplyMap(essay_id,comessayanswer.getReply_id());
    }

    @Override
    public int updateReply(ComEssayReply comessayanswer, Integer essay_id) {
        return mapper.updateReply(comessayanswer,essay_id);
    }

    @Override
    public int deleteReply(Integer essay_id, Integer user_id) {
        return mapper.deleteReply(essay_id,user_id);
    }

    @Override
    public int getReply(Integer essay_id) {
        return mapper.getReply(essay_id);
    }

    @Override
    public int addReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id) {
        mapper.addReplyEvaluate(comreplyevaluate);
        return mapper.addReplyEvaluateMap(comreplyevaluate.getEvaluate_id(),reply_id);
    }

    @Override
    public int updateReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id) {
        return mapper.updateReplyEvaluate(comreplyevaluate,reply_id);
    }

    @Override
    public List<ComReplyEvaluate> getReplyEvaluate(Integer reply_id) {
        return mapper.getReplyEvaluate(reply_id);
    }

    @Override
    public List<ComEssay> getEssayLatest() {
        return mapper.getEssayLatest();
    }

    @Override
    public List<ComEssayReply> getEssayReply(Integer essay_id) {
        return mapper.getEssayReply(essay_id);
    }

}
