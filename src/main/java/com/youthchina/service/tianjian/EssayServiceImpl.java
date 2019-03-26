package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.dao.tianjian.RichTextMapper;
import com.youthchina.domain.tianjian.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    UserService userService;

    @Autowired
    public EssayServiceImpl(CommunityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void addEssay(ComEssay essay) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(essay.getId());
        if (comEssaytest != null)
            throw new NotFoundException(404, 404, "this essay is exist");//todo
        else {
            mapper.addEssay(essay);
        }
        richTextMapper.addRichText(essay.getBody());
    }

    @Override
    public int deleteEssay(Integer essay_id, Timestamp delete_time) {
        return mapper.deleteEssay(essay_id, delete_time);
    }

    @Override
    public int updateEssay(ComEssay essay) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(essay.getId());
        if (comEssaytest == null) {
            throw new NotFoundException(404, 404, "this essay is not exist");//todo
        } else {
            if (essay.getIsAnony() != null)
                comEssaytest.setIsAnony(essay.getIsAnony());
            if (essay.getAbbre() != null)
                comEssaytest.setAbbre(essay.getAbbre());
            if (essay.getBody() != null)
                richTextMapper.updateRichText(essay.getBody());
            if (essay.getTitle() != null)
                comEssaytest.setTitle(essay.getTitle());

            return mapper.updateEssay(comEssaytest);
        }
    }

    @Override
    public ComEssay getEssay(Integer essay_id) throws NotFoundException {
        ComEssay comEssay = mapper.getEssay(essay_id);
        ComRichText comRichText = richTextMapper.getRichText(comEssay.getBody());
        comEssay.setBody(comRichText);
        User user = userService.get(comEssay.getUserId());
        comEssay.setUser(user);
        return comEssay;
    }

    @Override
    public List<ComEssay> getEssay(List<Integer> essayId) {
       List<ComEssay> comEssays = mapper.getEssayList(essayId);
        return comEssays;
    }

    @Override
    public List<ComEssay> getEssayLatest() {
        return mapper.getEssayLatest();
    }

    @Override
    public List<ComEssay> getAllEssayUserAttention(Integer user_id) {
        return mapper.getAllEssayUserAttention(user_id);
    }

    @Override
    public ComEssay get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<ComEssay> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public ComEssay update(ComEssay comEssay) throws NotFoundException {
        return null;
    }

    @Override
    public ComEssay add(ComEssay entity) throws NotFoundException {
        return null;
    }
}
