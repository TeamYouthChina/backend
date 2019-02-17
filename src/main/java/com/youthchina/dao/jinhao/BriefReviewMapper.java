package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.CommentEvaluate;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BriefReviewMapper {
    void add(BriefReviewMapper briefReviewMapper);
    void delete(Integer id);
    void deleteAllCommentsByReviewId(Integer id);
    void deleteAllCommentEvaluationByReviewId(Integer id);

    void update(BriefReviewMapper briefReviewMapper);
    BriefReview get(Integer id);
    List<BriefReview> getList(List<Integer> id);

    void addComment(Comment comment);
    void createCommentReviewMap(Integer comment_id, Integer comment_level, Integer review_id);
    void deleteComment(Integer id);
    void updateComment(Comment comment);
    Comment getComment(Integer id);
    List<Comment> getAllCommentsOfReview(Integer id);

    void addCommentEvaluation(CommentEvaluate commentEvaluate);
    void createEvaluationCommentMap(Integer evaluate_id, Integer comment_id);
    CommentEvaluate checkCommentEvaluationStatus(Integer user_id, Integer comment_id);
    void updateCommentEvaluation(CommentEvaluate commentEvaluate);
    CommentEvaluate getCommentEvaluation(Integer evaluate_id);
}
