package com.youthchina.service.community;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.util.LoggedInUserUtil;
import com.youthchina.util.dictionary.AttentionTargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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
    EvaluateServiceImpl evaluateService;

    @Resource
    AttentionService attentionService;

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
    public int deleteEssay(Integer essay_id, Timestamp delete_time) throws NotFoundException {
        ComEssay comEssay = new ComEssay();
        comEssay.setId(essay_id);
        List<Comment> comments = commentService.getComments(comEssay);
        for(Comment comment : comments){
            commentService.delete(comment.getId());
        }
        evaluateService.cancel(comEssay);
        attentionService.cancel(comEssay);
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

    /**
     * 查询文章
     * param Integer user_id
     * return ComEssay
     * Value id, title, abbre, body, pubTime, editTime, isAnony, relaId, relaType, user,
     */
    @Override
    public ComEssay getEssay(Integer essay_id) throws NotFoundException {
        ComEssay comEssay = mapper.getEssay(essay_id);
        if (comEssay == null) {
            throw new NotFoundException(4040, 404, "this essay does not exist");//todo
        }
        richTextService.getComRichText(comEssay);
        comEssay.setUser(userMapper.findOne(comEssay.getUser().getId()));
        comEssay.setAttentionCount(attentionService.countAttention(comEssay));
        if(LoggedInUserUtil.currentUser().getId()!=null)
        comEssay.setEvaluateStatus(evaluateService.evaluateStatus(comEssay, LoggedInUserUtil.currentUser().getId()));
        comEssay.setUpvoteCount(evaluateService.countUpvote(comEssay));
        comEssay.setDownvoteCount(evaluateService.countDownvote(comEssay));
        if(LoggedInUserUtil.currentUser().getId()!=null)
        comEssay.setAttention(attentionService.isAttention(AttentionTargetType.ESSAY,comEssay.getId(),  LoggedInUserUtil.currentUser().getId()));

        return comEssay;
    }

    @Override
    public List<ComEssay> getEssay(List<Integer> essayId) {
        List<ComEssay> res = new ArrayList<>();
        for (Integer i : essayId) {
            try {
                ComEssay essay = this.getEssay(i);
                res.add(essay);
            } catch (NotFoundException ignore) {

            }

        }
        return res;
    }

    @Override
    public List<ComEssay> getEssayLatest() {
        return mapper.getEssayLatest();
    }

    @Override
    public List<ComEssay> getAllEssayByUserId(Integer userId) {
        List<ComEssay> comEssays = mapper.getAllEssayByUserId(userId);
        List<ComEssay> comEssayListReturn = new ArrayList<ComEssay>();
        Iterator iterator = comEssays.iterator();
        while (iterator.hasNext()){
            ComEssay comEssay = (ComEssay) iterator.next();
            richTextService.getComRichText(comEssay);
            comEssay.setEvaluateStatus(evaluateService.evaluateStatus(comEssay, userId));
            comEssay.setUpvoteCount(evaluateService.countUpvote(comEssay));
            comEssay.setDownvoteCount(evaluateService.countDownvote(comEssay));
            comEssay.setAttention(attentionService.isAttention(AttentionTargetType.ESSAY,comEssay.getId(),  userId));
            comEssay.setAttentionCount(attentionService.countAttention(comEssay));
            comEssayListReturn.add(comEssay);
        }
        return comEssayListReturn;
    }

    @Override
    @Transactional
    public ComEssay get(Integer id) throws NotFoundException {
        ComEssay comEssay = mapper.getEssay(id);
        if (comEssay == null) {
            throw new NotFoundException(4040, 404, "this essay does not exist");//todo
        }
        richTextService.getComRichText(comEssay);
        comEssay.setUser(userMapper.findOne(comEssay.getUser().getId()));
        return comEssay;
    }

    @Override
    public List<ComEssay> get(List<Integer> id) {
        return this.getEssay(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        get(id);
        ComEssay comEssay = new ComEssay();
        comEssay.setId(id);
        List<Comment> comments = commentService.getComments(comEssay);
        for(Comment comment : comments){
            commentService.delete(comment.getId());
        }
        evaluateService.cancel(comEssay);
        attentionService.cancel(comEssay);
        Timestamp delete_time = new Timestamp(System.currentTimeMillis());
        mapper.deleteEssay(id, delete_time);
    }

    @Override
    public ComEssay update(ComEssay essay) throws NotFoundException {
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
            return mapper.getEssay(essay.getId());
        }
    }

    @Override
    public ComEssay add(ComEssay entity) throws NotFoundException {
        ComEssay comEssaytest = mapper.getEssay(entity.getId());
        if (comEssaytest != null)
            throw new NotFoundException(4040, 404, "this essay is exist");//todo
        else {
            richTextService.addComRichText(entity.getBody());
            mapper.addEssay(entity);
        }
        return entity;
    }
}
