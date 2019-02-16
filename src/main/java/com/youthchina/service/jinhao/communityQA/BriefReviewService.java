package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;


public interface BriefReviewService extends DomainCRUDService<BriefReview, Integer> {
    @Override
    BriefReview add(BriefReview entity);

    @Override
    BriefReview get(Integer id) throws NotFoundException;

    @Override
    List<BriefReview> get(List<Integer> id) throws NotFoundException;

    @Override
    BriefReview update(BriefReview briefReview) throws NotFoundException;

    @Override
    void delete(Integer id) throws NotFoundException;

    // 评论
    Comment addComment(Comment comment);

    Comment getComment(Integer comment_id) throws NotFoundException;

    void deleteComment(Integer comment_id) throws NotFoundException;

    Comment updateComment(Comment comment) throws NotFoundException;

    List<Comment> getAllCommentsOfReview(Integer review_id) throws NotFoundException;

    // 评论评价
    CommentEvaluate evaluateComment(Integer user_id, Integer comment_id, Integer evaluate_type) throws NotFoundException;

    CommentEvaluate getCommentEvaluate(Integer evaluate_id) throws NotFoundException;

    //讨论
    CommentDiscuss addDiscuss(CommentDiscuss commentDiscuss);

    CommentDiscuss getDiscuss(Integer discuss_id) throws NotFoundException;

    void deleteDiscuss(Integer discuss_id) throws NotFoundException;

    CommentDiscuss updateDiscuss(CommentDiscuss commentDiscuss) throws NotFoundException;

    List<CommentDiscuss> getAllDiscussesOfComment(Integer comment_id) throws NotFoundException;

    //评价讨论
    DiscussEvaluate EvaluateDiscuss(Integer user_id, Integer discuss_id, Integer evaluate_type) throws NotFoundException;

    DiscussEvaluate getDiscussEvaluate(Integer evaluate_id) throws NotFoundException;
}
