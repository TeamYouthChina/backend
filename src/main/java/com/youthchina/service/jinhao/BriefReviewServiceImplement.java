package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.RichTextService;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
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
    UserService userService;

    @Override
    @Transactional
    public BriefReview get(Integer id) throws NotFoundException {
        BriefReview briefReview = briefReviewMapper.get(id);
        if(briefReview == null){
            throw new NotFoundException(404,404,"没有找到这个短评");
        }
        briefReview.setUser(userService.get(briefReview.getUser().getId()));
        richTextService.getComRichText(briefReview);
        List<Comment> comments = commentService.getComments(briefReview);
        briefReview.setComments(comments);
        return briefReview;
    }

    @Override
    public List<BriefReview> get(List<Integer> id){
       List<BriefReview> briefReviews = new LinkedList<>();
       for(Integer one : id){
           try {
               briefReviews.add(get(one));
           } catch (NotFoundException e) {

           }
       }
       return briefReviews;
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
        BriefReview briefReview1 = get(briefReview.getId());
        briefReview.getBody().setTextId(briefReview1.getBody().getTextId());
        richTextService.updateComRichText(briefReview.getBody());
        return  get(briefReview.getId());
    }

    @Override
    @Transactional
    public BriefReview add(BriefReview entity) {
        richTextService.addComRichText(entity.getBody());
        briefReviewMapper.add(entity);
        return entity;
    }

    @Override
    public void isBriefReviewExist(Integer id) throws NotFoundException {
        if(briefReviewMapper.checkIfBriefReviewExist(id) == null){
            throw new NotFoundException(404,404,"没有找到这个短评");
        }
    }

    @Override
    public Integer count() {
        return briefReviewMapper.count();
    }

    @Override
    public List<BriefReview> getMyBriefReview(Integer id) {
        List<BriefReview> briefReviews = briefReviewMapper.getMyBriefReview(id);
        for(BriefReview briefReview : briefReviews){

            try {
                briefReview.setUser(userService.get(briefReview.getUser().getId()));
            } catch (NotFoundException e) {

            }

        }
        return briefReviews;
    }
}
