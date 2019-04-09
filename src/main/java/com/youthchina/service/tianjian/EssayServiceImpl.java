package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AttentionService;
import com.youthchina.service.jinhao.CommentServiceImpl;
import com.youthchina.service.jinhao.EvaluateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianjian chen
 */
@Service("essayService")
@Transactional
public class EssayServiceImpl implements EssayService {
    @Resource
    CommunityMapper mapper;

    @Resource
    RichTextServiceImpl richTextService;

    @Resource
    CommentServiceImpl commentService;

    @Resource
    AttentionService attentionService;

    @Resource
    EvaluateServiceImpl evaluateService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    public EssayServiceImpl(CommunityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void addEssay(ComEssay essay) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(essay.getId());
        if (comEssaytest != null)
            throw new NotFoundException(4040, 404, "this essay is exist");//todo
        else {
            richTextService.addComRichText(essay.getBody());
            mapper.addEssay(essay);
        }
    }

    @Override
    public int deleteEssay(Integer essay_id, Timestamp delete_time) {
        ComEssay comEssay = new ComEssay();
        comEssay.setId(essay_id);
        commentService.delete(comEssay);
        return mapper.deleteEssay(essay_id, delete_time);
    }

    @Override
    public int updateEssay(ComEssay essay) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(essay.getId());
        if (comEssaytest == null) {
            throw new NotFoundException(4040, 404, "this essay is not exist");//todo
        } else {
            richTextService.getComRichText(comEssaytest);
            essay.getBody().setTextId(comEssaytest.getBody().getTextId());
            if (essay.getIsAnony() != null)
                comEssaytest.setIsAnony(essay.getIsAnony());
            if (essay.getAbbre() != null)
                comEssaytest.setAbbre(essay.getAbbre());
            if (essay.getBody() != null) {
                richTextService.updateComRichText(essay.getBody());
                comEssaytest.setBody(essay.getBody());
            }
            if (essay.getTitle() != null)
                comEssaytest.setTitle(essay.getTitle());
            mapper.updateEssay(comEssaytest);
            return 1;
        }
    }

    @Override
    public ComEssay getEssay(Integer essay_id) throws NotFoundException {
        ComEssay comEssay = mapper.getEssay(essay_id);
        if (comEssay == null) {
            throw new NotFoundException(404, 404, "this essay does not exist");
        }
        richTextService.getComRichText(comEssay);
        comEssay.setUser(userMapper.findOne(comEssay.getUser().getId()));
        comEssay.setUpvotecount(evaluateService.countUpvote(comEssay));
        //comEssay.setDownvotecount();
        return comEssay;
    }

    @Override
    public List<ComEssay> getEssay(List<Integer> essayId) {
        return mapper.getEssayList(essayId);
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
        List<ComEssay> res = new ArrayList<>();
        for (Integer i : id) {
            ComEssay essay = this.getEssay(i);
            if (essay != null) {
                res.add(essay);
            }
        }
        return res;
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
