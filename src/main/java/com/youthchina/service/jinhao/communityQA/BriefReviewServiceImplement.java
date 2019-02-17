package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BriefReviewServiceImplement implements BriefReviewService {
    @Resource
    BriefReviewMapper briefReviewMapper;

    @Override
    @Transactional
    public BriefReview add(BriefReview entity) {
        briefReviewMapper.add(entity);
        briefReviewMapper.createReviewMap(entity.getReview_id(), entity.getUser().getId(), entity.getRela_type()
        ,entity.getRela_id());
        return entity;
    }

    @Override
    public BriefReview get(Integer id) throws NotFoundException {
        BriefReview briefReview = briefReviewMapper.get(id);
        if(briefReview == null){
            throw new NotFoundException(404,404,"This brief review does not exist!");
        }else {
            return briefReview;
        }
    }

    @Override
    public List<BriefReview> get(List<Integer> id) throws NotFoundException {
        List<BriefReview> briefReviews = briefReviewMapper.getList(id);
        if(briefReviews.size() == 0){
            throw new NotFoundException(404,404,"None of these review exists!");
        }else {
            return briefReviews;
        }
    }

    private void checkIfReviewExist(Integer id) throws NotFoundException{
        BriefReview briefReview = briefReviewMapper.simplyGetReview(id);
        if(briefReview == null) throw new NotFoundException(404,404,"This brief review does not exist!");
    }

    @Override
    @Transactional
    public BriefReview update(BriefReview briefReview) throws NotFoundException {
        checkIfReviewExist(briefReview.getReview_id());
        briefReviewMapper.update(briefReview);
        return briefReview;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        checkIfReviewExist(id);
        briefReviewMapper.delete(id);
        briefReviewMapper.deleteAllReviewEvaluationByReviewId(id);
        briefReviewMapper.deleteAllCommentsByReviewId(id);
        briefReviewMapper.deleteAllCommentEvaluationByReviewId(id);
        briefReviewMapper.deleteAllDiscussesByReviewId(id);
        briefReviewMapper.deleteAllDiscussEvaluateByReviewId(id);
    }

    @Override
    @Transactional
    public Evaluate doEvaluate(Integer user_id, Integer review_id, Integer evaluate_type) throws NotFoundException {
        checkIfReviewExist(review_id);
        Evaluate evaluate = briefReviewMapper.checkEvaluateStatus(user_id, review_id);
        if(evaluate == null){
            Evaluate evaluate1 = new Evaluate();
            evaluate1.setUser_id(user_id);
            evaluate1.setEvaluate_type(evaluate_type);
            briefReviewMapper.addReviewEvaluation(evaluate1);
            briefReviewMapper.createEvaluationReviewMap(evaluate1.getEvaluate_id(), review_id);
            return evaluate1;
        }else{
            evaluate.setEvaluate_type(evaluate_type);
            briefReviewMapper.updateEvaluation(evaluate);
            return evaluate;
        }
    }

    @Override
    @Transactional
    public Integer getReviewAgreement(Integer review_id) throws NotFoundException{
        checkIfReviewExist(review_id);
        return briefReviewMapper.countReviewAgreement(review_id);
    }

    @Override
    @Transactional
    public Comment addComment(Comment comment, Integer review_id) {
        briefReviewMapper.addComment(comment);
        briefReviewMapper.createCommentReviewMap(comment.getComment_id(), null, review_id);
        return comment;
    }

    @Override
    public Comment getComment(Integer comment_id) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteComment(Integer comment_id) throws NotFoundException {

    }

    @Override
    public Comment updateComment(Comment comment) throws NotFoundException {
        return null;
    }

    @Override
    public List<Comment> getAllCommentsOfReview(Integer review_id) throws NotFoundException {
        return null;
    }

    @Override
    public CommentEvaluate evaluateComment(Integer user_id, Integer comment_id, Integer evaluate_type) throws NotFoundException {
        return null;
    }

    @Override
    public CommentEvaluate getCommentEvaluate(Integer evaluate_id) throws NotFoundException {
        return null;
    }

    @Override
    public Discuss addDiscuss(Discuss discuss) {
        return null;
    }

    @Override
    public Discuss getDiscuss(Integer discuss_id) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteDiscuss(Integer discuss_id) throws NotFoundException {

    }

    @Override
    public Discuss updateDiscuss(Discuss discuss) throws NotFoundException {
        return null;
    }

    @Override
    public List<Discuss> getAllDiscussesOfComment(Integer comment_id) throws NotFoundException {
        return null;
    }

    @Override
    public Evaluate EvaluateDiscuss(Integer user_id, Integer discuss_id, Integer evaluate_type) throws NotFoundException {
        return null;
    }

    @Override
    public Evaluate getDiscussEvaluate(Integer evaluate_id) throws NotFoundException {
        return null;
    }
}
