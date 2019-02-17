package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BriefReviewServiceImplement implements BriefReviewService {

    @Override
    public BriefReview add(BriefReview entity) {
        return null;
    }

    @Override
    public BriefReview get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<BriefReview> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public BriefReview update(BriefReview briefReview) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Evaluate doEvaluate(Evaluate evaluate) throws NotFoundException {
        return null;
    }

    @Override
    public Evaluate getEvaluate(Integer evaluate_id) throws NotFoundException {
        return null;
    }

    @Override
    public Comment addComment(Comment comment) {
        return null;
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
