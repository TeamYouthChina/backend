package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.dao.tianjian.RichTextMapper;
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
    RichTextMapper richTextMapper;

    @Autowired
    RichTextServiceImpl richTextService;

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

    @Override
    public int addEssay(ComEssay essay, List<Integer> lab_num) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(essay.getEssayId());
        if(comEssaytest!=null)
            throw new NotFoundException(404,404,"this essay is exist");//todo
        else{
            mapper.addEssay(essay);
            richTextService.addComRichText(essay.getEssayBody());
            int essayid = essay.getEssayId();
            List<ComEssayLabelMap> l = new ArrayList<ComEssayLabelMap>();
            if(lab_num!=null&&lab_num.size()!=0){
                for( int i = 0 ; i < lab_num.size() ; i++) {
                    ComEssayLabelMap cel = new ComEssayLabelMap();
                    cel.setEssay_id(essayid);
                    cel.setLab_num(lab_num.get(i));
                    l.add(cel);
                }
            }
            return essayid;
        }
    }
    @Override
    public int deleteEssay(Integer essay_id, Timestamp delete_time) {
        return mapper.deleteEssay(essay_id,delete_time);
    }

    @Override
    public int updateEssay(ComEssay essay) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(essay.getEssayId());
        if(comEssaytest==null){
                throw new NotFoundException(404,404,"this essay is not exist");//todo
            }else {
            if (essay.getEssayPubTime() != null)
                comEssaytest.setEssayPubTime(essay.getEssayPubTime());
            if (essay.getUserAnony() != null)
                comEssaytest.setUserAnony(essay.getUserAnony());
            if (essay.getIsDelete() != null)
                comEssaytest.setIsDelete(essay.getIsDelete());
            if (essay.getEssayAbbre() != null)
                comEssaytest.setEssayAbbre(essay.getEssayAbbre());
            if (essay.getEssayBody() != null)
                richTextService.updateComRichText(essay.getEssayBody());
            if (essay.getEssayEditTime() != null)
                comEssaytest.setEssayEditTime(essay.getEssayEditTime());
            if (essay.getEssayTitle() != null)
                comEssaytest.setEssayTitle(essay.getEssayTitle());

            return mapper.updateEssay(comEssaytest);
        }
    }

    @Override
    public ComEssay getEssay(Integer essay_id) {
        ComEssay essay = mapper.getEssay(essay_id);
        essay.setEssayBody(richTextMapper.getRichText(essay_id,1));
        return essay;
    }

    @Override
    public List<ComEssay> getEssayLatest() {
        return mapper.getEssayLatest();
    }


}
