package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.*;
import com.youthchina.domain.zhongyang.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 11/8/18.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    CommunityMapper mapper;

    @Autowired
    public UserServiceImpl(CommunityMapper mapper) {
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

//    public CompanyInfo getCompanyInformation(String company_id){
//        CompanyInfo companyInfo =  mapper.getCompanyInformation(company_id);
//        return  companyInfo;
//    }
//
//    public StuCollect getFavoriteCompany(StuCollect company){
//        return mapper.getFavoriteCompany(company);
//    }
//
//    public int addFavoriteCompany(StuCollect company){
//        if(mapper.addFavoriteCompany(company)>0)
//          return 1;
//        else
//            return 0;
//    }
//
//    public int addFavoriteJob(StuCollect Job){
//        if(mapper.addFavoriteJob(Job)>0)
//            return 1;
//        else
//            return 0;
//    }
//
//    public JobInfo getJobInformation(String job_id){
//        return mapper.getJobInformation(job_id);
//    }
//
//    public int deleteFavoriteCompany(StuCollect deletefavoritecompany){
//        return mapper.deleteFavoriteCompany(deletefavoritecompany);
//    }
//
//    @Override
//    public int deleteFavoriteJob(StuCollect deletefavoritejob) {
//        return mapper.deleteFavoriteJob(deletefavoritejob);
//    }

    @Override
    public int addEssay(ComEssay essay, List<Integer> lab_num, Integer user_id) {
        mapper.addEssay(essay);
        int essayid = essay.getEssay_id();
        List<ComEssayLabelMap> l = new ArrayList<ComEssayLabelMap>();
        for( int i = 0 ; i < lab_num.size() ; i++) {
            ComEssayLabelMap cel = new ComEssayLabelMap();
            cel.setEssay_id(essayid);
            cel.setLab_num(lab_num.get(i));
            l.add(cel);
        }
        mapper.addEssayLabel(l);

        ComAuthorEssayMap caem = new ComAuthorEssayMap();
        caem.setEssay_id(essayid);
        caem.setUser_id(user_id);
        mapper.addEssayAuthor(caem);
        return 1;
    }
    @Override
    public int deleteEssay(Integer essay_id, Timestamp delete_time) {
        return mapper.deleteEssay(essay_id,delete_time);
    }

    @Override
    public int updateEssay(ComEssay essay, Integer user_id, List<Integer> lab_num) {
         mapper.updateEssay(essay);

          ComAuthorEssayMap caem = new ComAuthorEssayMap();
          caem.setEssay_id(essay.getEssay_id());
         caem.setUser_id(user_id);
         mapper.updateEssayAuthor(caem);
         mapper.deleteEssayLabel(essay.getEssay_id());

         List<ComEssayLabelMap> l = new ArrayList<ComEssayLabelMap>();
        for( int i = 0 ; i < lab_num.size() ; i++) {
            ComEssayLabelMap cel = new ComEssayLabelMap();
            cel.setEssay_id(essay.getEssay_id());
            cel.setLab_num(lab_num.get(i));
            l.add(cel);
        }
         int k = mapper.addEssayLabel(l);
         return k;
    }

    @Override
    public ComEssay getEssay(Integer essay_id) {
        return mapper.getEssay(essay_id);
    }

    @Override
    public int addFavoriteEssay(ComEssayAttention comessayattention, Integer essay_id){
        mapper.addFavoriteEssay(comessayattention);
        int attenid = comessayattention.getAtten_id();
        ComEssayAttentionMap ceam = new ComEssayAttentionMap();
        ceam.setAtten_id(attenid);
        ceam.setEssay_id(essay_id);
        mapper.addFavoriteEssayMap(ceam);
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
    public int addReply(ComEssayReply comessayanswer, Integer essay_id, Integer reply_level) {
        mapper.addReply(comessayanswer);
        ComEssayReplyMap cerm = new ComEssayReplyMap();
        cerm.setEssay_id(essay_id);
        cerm.setReply_id(comessayanswer.getReply_id());
        cerm.setReply_level(reply_level);
        return mapper.addEssayReplyMap(cerm);
    }

    @Override
    public int updateReply(ComEssayReply comessayreply, Integer essay_id) {
        return mapper.updateReply(comessayreply,essay_id);
    }

    @Override
    public int deleteReply(Integer essay_id, Integer user_id,Integer reply_level) {
        return mapper.deleteReply(essay_id,user_id,reply_level);
    }
//
    @Override
    public  List<ComEssayReply> getReply(Integer essay_id) {
        return mapper.getReply(essay_id);
    }
//
    @Override
    public int addReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id) {

        mapper.addReplyEvaluate(comreplyevaluate);
        ComReplyEvaluateMap crem = new ComReplyEvaluateMap();
        crem.setEvaluate_id(comreplyevaluate.getEvaluate_id());
        crem.setReply_id(reply_id);
        return mapper.addReplyEvaluateMap(crem);
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
