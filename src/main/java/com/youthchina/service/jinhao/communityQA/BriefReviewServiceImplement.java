package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BriefReviewServiceImplement implements BriefReviewService {
    @Resource
    BriefReviewMapper briefReviewMapper;

    @Override
    public List<BriefReview> getUserReview(Integer user_id) {
        return null;
    }

    @Override
    public List<BriefReview> getUserUpvoteReview(Integer user_id) {
        return null;
    }

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
            throw new NotFoundException(404,404,"This brief review does not exist!");//todo
        }else {
            return briefReview;
        }
    }

    @Override
    public List<BriefReview> get(List<Integer> id) throws NotFoundException {
        List<BriefReview> briefReviews = briefReviewMapper.getList(id);
        if(briefReviews.size() == 0){
            throw new NotFoundException(404,404,"None of these review exists!");//todo
        }else {
            return briefReviews;
        }
    }

    private void checkIfReviewExist(Integer id) throws NotFoundException{
        BriefReview briefReview = briefReviewMapper.simplyGetReview(id);
        if(briefReview == null) throw new NotFoundException(404,404,"This brief review does not exist!");////todo
    }

    @Override
    @Transactional
    public BriefReview update(BriefReview briefReview) throws NotFoundException {
        checkIfReviewExist(briefReview.getReview_id());
        briefReviewMapper.update(briefReview);
        return get(briefReview.getReview_id());
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
            evaluate1.setEvaluate_time(new Timestamp(System.currentTimeMillis()));
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
        briefReviewMapper.createCommentReviewMap(comment.getComment_id(), 1, review_id);
        return comment;
    }

    @Override
    public Comment getComment(Integer comment_id) throws NotFoundException {
        Comment comment = briefReviewMapper.getComment(comment_id);
        if(comment == null){
            throw new NotFoundException(404,404,"This comment does not exist!");//todo
        }else{
            return comment;
        }
    }
    private void checkIfCommentExist(Integer comment_id) throws NotFoundException{
        if(briefReviewMapper.simplyGetComment(comment_id) == null) throw new NotFoundException(404,404,"This comment does not exist!");//todo
    }
    @Override
    @Transactional
    public void deleteComment(Integer comment_id) throws NotFoundException {
        checkIfCommentExist(comment_id);
        briefReviewMapper.deleteComment(comment_id);
        briefReviewMapper.deleteAllCommentEvaluationByCommentId(comment_id);
        briefReviewMapper.deleteAllDiscussByCommentId(comment_id);
        briefReviewMapper.deleteAllDiscussEvaluationByCommentId(comment_id);
    }

    @Override
    @Transactional
    public Comment updateComment(Comment comment) throws NotFoundException {
        checkIfCommentExist(comment.getComment_id());
        briefReviewMapper.updateComment(comment);
        comment.setComment_edit_time(new Timestamp(System.currentTimeMillis()));
        return comment;
    }

    @Override
    @Transactional
    public List<Comment> getAllCommentsOfReview(Integer review_id) throws NotFoundException {
        checkIfReviewExist(review_id);
        List<Comment> comments = briefReviewMapper.getCommentsByReviewId(review_id);
        if(comments == null){
            throw new NotFoundException(404,404,"This review does not have comments!");//todo
        }else {
            return comments;
        }
    }

    @Override
    @Transactional
    public Evaluate evaluateComment(Integer user_id, Integer comment_id, Integer evaluate_type) throws NotFoundException {
        checkIfCommentExist(comment_id);
        Evaluate evaluate = briefReviewMapper.checkCommentEvaluationStatus(user_id, comment_id);
        if(evaluate == null){
            Evaluate evaluate1 = new Evaluate();
            evaluate1.setUser_id(user_id);
            evaluate1.setEvaluate_type(evaluate_type);
            evaluate1.setEvaluate_time(new Timestamp(System.currentTimeMillis()));
            briefReviewMapper.addCommentEvaluation(evaluate1);
            briefReviewMapper.createEvaluationCommentMap(evaluate1.getEvaluate_id(),comment_id);
            return evaluate1;
        }else {
            evaluate.setEvaluate_type(evaluate_type);
            briefReviewMapper.updateCommentEvaluation(evaluate);
            briefReviewMapper.updateCommentEvaluation(evaluate);
            return evaluate;
        }
    }

    @Override
    @Transactional
    public Integer countCommentAgreement(Integer comment_id) throws NotFoundException {
        checkIfCommentExist(comment_id);
        return briefReviewMapper.countCommentAgreement(comment_id);
    }

    @Override
    @Transactional
    public Discuss addDiscuss(Discuss discuss, Integer comment_id) {
//        briefReviewMapper.addDiscuss(discuss);
//        briefReviewMapper.createDiscussCommentMap(discuss.getDiscuss_id(), null,comment_id);
//        return discuss;
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
