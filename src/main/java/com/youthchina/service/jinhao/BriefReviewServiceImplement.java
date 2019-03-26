package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.RichTextService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BriefReviewServiceImplement implements BriefReviewService {
    @Resource
    BriefReviewMapper briefReviewMapper;

    @Resource
    CommentService commentService;

    @Resource
    RichTextService richTextService;

    @Resource
    UserMapper userMapper;

    @Override
    @Transactional
    public BriefReview get(Integer id) throws NotFoundException {
        BriefReview briefReview = briefReviewMapper.get(id);
        if(briefReview == null){
            throw new NotFoundException(404,404,"没有找到这个短评");
        }
        briefReview.setRichText(richTextService.getComRichText(id,3));
        commentService.getComments(briefReview);
        briefReview.setUser(userMapper.findOne(briefReview.getUserId()));
        return briefReview;
    }

    @Override
    public List<BriefReview> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        isBriefReviewExist(id);
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        commentService.delete(briefReview);
        briefReviewMapper.delete(id);
    }

    @Override
    @Transactional
    public BriefReview update(BriefReview briefReview) throws NotFoundException {
        isBriefReviewExist(briefReview.getId());
        briefReviewMapper.update(briefReview);
        richTextService.updateComRichText(briefReview.getRichText());
        return  briefReview;
    }

    @Override
    @Transactional
    public BriefReview add(BriefReview entity) {
        briefReviewMapper.add(entity);
        richTextService.addComRichText(entity.getRichText());
        return entity;
    }

    @Override
    public void isBriefReviewExist(Integer id) throws NotFoundException {
        if(briefReviewMapper.checkIfBriefReviewExist(id) == null){
            throw new NotFoundException(404,404,"没有找到这个短评");
        }
    }
}
