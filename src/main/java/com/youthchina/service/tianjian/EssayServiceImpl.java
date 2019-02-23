package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianjian chen
 */
@Service("essayService")
@Transactional
public class EssayServiceImpl implements EssayService {
    @Autowired
    CommunityMapper mapper;

    @Autowired
    public EssayServiceImpl(CommunityMapper mapper) {
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
    public int addEssay(ComEssay essay, List<Integer> lab_num, Integer user_id, Integer rela_type, Integer rela_id) {
        ComEssay comEssaytest = mapper.getEssay(essay.getEssay_id());
        if(comEssaytest==null)
            return 0;
        mapper.addEssay(essay);
        int essayid = essay.getEssay_id();
        List<ComEssayLabelMap> l = new ArrayList<ComEssayLabelMap>();
        if(lab_num!=null){
            for( int i = 0 ; i < lab_num.size() ; i++) {
                ComEssayLabelMap cel = new ComEssayLabelMap();
                cel.setEssay_id(essayid);
                cel.setLab_num(lab_num.get(i));
                l.add(cel);
            }
        }
        mapper.addEssayLabel(l);

        ComAuthorEssayMap caem = new ComAuthorEssayMap();
        caem.setEssay_id(essayid);
        caem.setUser_id(user_id);
        caem.setRela_type(rela_type);
        caem.setRela_id(rela_id);
        mapper.addEssayAuthor(caem);
        return essayid;
    }
    @Override
    public int deleteEssay(Integer essay_id, Timestamp delete_time) {
        return mapper.deleteEssay(essay_id,delete_time);
    }

    @Override
    public int updateEssay(ComEssay essay) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(essay.getEssay_id());
        if(comEssaytest==null){
                throw new NotFoundException(404,404,"this essay is not exist");//todo
            }else {
            if (essay.getEssay_pub_time() != null)
                comEssaytest.setEssay_pub_time(essay.getEssay_pub_time());
            if (essay.getUser_anony() != null)
                comEssaytest.setUser_anony(essay.getUser_anony());
            if (essay.getIs_delete() != null)
                comEssaytest.setIs_delete(essay.getIs_delete());
            if (essay.getEssay_abbre() != null)
                comEssaytest.setEssay_abbre(essay.getEssay_abbre());
            if (essay.getEssay_body() != null)
                comEssaytest.setEssay_body(essay.getEssay_body());
            if (essay.getEssay_edit_time() != null)
                comEssaytest.setEssay_edit_time(essay.getEssay_edit_time());
            if (essay.getEssay_title() != null)
                comEssaytest.setEssay_title(essay.getEssay_title());

            return mapper.updateEssay(comEssaytest);
        }
    }

    @Override
    public int updateEssayAuthor(ComAuthorEssayMap comAuthorEssayMap ) throws NotFoundException {
        ComAuthorEssayMap comAuthorEssayMap1 = mapper.getEssayAuthor(comAuthorEssayMap.getEssay_id());
        if(comAuthorEssayMap1==null){
            throw new NotFoundException(404,404,"this essay is not exist");//todo
        }else
            return mapper.updateEssayAuthor(comAuthorEssayMap);
    }

    @Override
    public int updateEssayLabel(Integer essay_id, Integer user_id, List<Integer> lab_num) throws NotFoundException {
         ComEssay comEssay = mapper.getEssay(essay_id);
        if(comEssay==null){
            throw new NotFoundException(404,404,"this essay is not exist");//todo
        }else{
            ComAuthorEssayMap caem = new ComAuthorEssayMap();
            caem.setEssay_id(essay_id);
            caem.setUser_id(user_id);
            mapper.updateEssayAuthor(caem);
            mapper.deleteEssayLabel(essay_id);
            List<ComEssayLabelMap> l = new ArrayList<ComEssayLabelMap>();
            for( int i = 0 ; i < lab_num.size() ; i++) {
                ComEssayLabelMap cel = new ComEssayLabelMap();
                cel.setEssay_id(essay_id);
                cel.setLab_num(lab_num.get(i));
                l.add(cel);
            }
            return mapper.addEssayLabel(l);
        }
    }

    @Override
    public ComEssay getEssay(Integer essay_id) {
        return mapper.getEssay(essay_id);
    }

    @Override
    public ComAuthorEssayMap getEssayAuthor(Integer essay_id) {
        return mapper.getEssayAuthor(essay_id);
    }

    @Override
    public int addFavoriteEssay(ComEssayAttention comessayattention, Integer essay_id) throws NotFoundException {
        ComEssay comEssay = mapper.getEssay(essay_id);
        if(comEssay==null){
            throw new NotFoundException(404,404,"this essay is not exist");//todo
        }
        mapper.addFavoriteEssay(comessayattention);
        int attenid = comessayattention.getAtten_id();
        ComEssayAttentionMap ceam = new ComEssayAttentionMap();
        ceam.setAtten_id(attenid);
        ceam.setEssay_id(essay_id);
        mapper.addFavoriteEssayMap(ceam);
        return 1;
    }

    @Override
    public int deleteFavoriteEssay(Integer essay_id, Integer user_id) throws NotFoundException {
        int i =  mapper.deleteFavoriteEssay(essay_id, user_id);
        if(i==0){
            throw new NotFoundException(404,404,"essay or user is not exist");//todo
        }else
            return i;
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
    public int addReply(ComEssayReply comessayanswer, Integer essay_id, Integer reply_level) throws NotFoundException {
        ComEssay comEssay = mapper.getEssay(essay_id);
        if(comEssay==null){
            throw new NotFoundException(404,404,"essay is not exist");//todo
        }
        mapper.addReply(comessayanswer);
        ComEssayReplyMap cerm = new ComEssayReplyMap();
        cerm.setEssay_id(essay_id);
        cerm.setReply_id(comessayanswer.getReply_id());
        cerm.setReply_level(reply_level);
        return mapper.addEssayReplyMap(cerm);
    }

    @Override
    public int updateReply(ComEssayReply comessayreply, Integer essay_id) throws NotFoundException {
        int i = mapper.updateReply(comessayreply,essay_id);
        if(i==0){
            throw new NotFoundException(404,404,"reply is not exist");//todo
        }else
            return i;
    }

    @Override
    public int deleteReply(Integer essay_id, Integer user_id,Integer reply_level) throws NotFoundException {
        int i = mapper.deleteReply(essay_id,user_id,reply_level);
        if(i==0){
            throw new NotFoundException(404,404,"reply is not exist");//todo
        }else
        return i;
    }

    @Override
    public  List<ComEssayReply> getReply(Integer essay_id) {
        return mapper.getReply(essay_id);
    }

    @Override
    public int addReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id) {
        mapper.addReplyEvaluate(comreplyevaluate);
        ComReplyEvaluateMap crem = new ComReplyEvaluateMap();
        crem.setEvaluate_id(comreplyevaluate.getEvaluate_id());
        crem.setReply_id(reply_id);
        return mapper.addReplyEvaluateMap(crem);
    }

    @Override
    public int updateReplyEvaluate(ComReplyEvaluate comreplyevaluate, Integer reply_id) throws NotFoundException {
        int i = mapper.updateReplyEvaluate(comreplyevaluate,reply_id);
        if(i==0){
            throw new NotFoundException(404,404,"ReplyEvaluate is not exist");//todo
        }else
            return i;
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
