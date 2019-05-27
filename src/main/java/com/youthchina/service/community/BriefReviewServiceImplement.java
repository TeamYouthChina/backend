package com.youthchina.service.community;

import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import com.youthchina.util.LoggedInUserUtil;
import com.youthchina.util.dictionary.AttentionTargetType;
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
    EvaluateService evaluateService;

    @Resource
    AttentionService attentionService;

    @Resource
    UserService userService;

    @Override
    @Transactional
    public BriefReview get(Integer id) throws NotFoundException {
        BriefReview briefReview = briefReviewMapper.get(id);
        if(briefReview == null){
            throw new NotFoundException(4040,404,"没有找到这个短评");//todo
        }
        briefReview.setUser(userService.get(briefReview.getUser().getId()));
        richTextService.getComRichText(briefReview);
        List<Comment> comments = commentService.getComments(briefReview);
        briefReview.setComments(comments);
        briefReview.setAttentionCount(attentionService.countAttention(briefReview));
        briefReview.setEvaluateStatus(evaluateService.evaluateStatus(briefReview, LoggedInUserUtil.currentUser().getId()));
        briefReview.setUpvoteCount(evaluateService.countUpvote(briefReview));
        briefReview.setDownvoteCount(evaluateService.countDownvote(briefReview));
        briefReview.setAttention(attentionService.isAttention(AttentionTargetType.BRIEFREVIEW,briefReview.getId(),LoggedInUserUtil.currentUser().getId()));
        return briefReview;
    }


    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        get(id);
        BriefReview briefReview = new BriefReview();
        briefReview.setId(id);
        List<Comment> comments = commentService.getComments(briefReview);
        for(Comment comment : comments){
            commentService.delete(comment.getId());
        }
        evaluateService.cancel(briefReview);
        attentionService.cancel(briefReview);
        briefReviewMapper.delete(id);
    }

    @Override
    @Transactional
    public BriefReview update(BriefReview briefReview) throws NotFoundException {
        get(briefReview.getId());
        briefReviewMapper.update(briefReview);
        BriefReview briefReview1 = get(briefReview.getId());
        briefReview.getBody().setTextId(briefReview1.getBody().getTextId());
        richTextService.updateComRichText(briefReview.getBody());
        return get(briefReview.getId());
    }

    @Override
    @Transactional
    public BriefReview add(BriefReview entity) throws NotFoundException {
        richTextService.addComRichText(entity.getBody());
        briefReviewMapper.add(entity);
        return get(entity.getId());
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
                briefReview.setAttentionCount(attentionService.countAttention(briefReview));
                briefReview.setUpvoteCount(evaluateService.countUpvote(briefReview));
                briefReview.setDownvoteCount(evaluateService.countDownvote(briefReview));
                briefReview.setEvaluateStatus(evaluateService.evaluateStatus(briefReview, LoggedInUserUtil.currentUser().getId()));
            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(briefReview);
        }
        return briefReviews;
    }
}
