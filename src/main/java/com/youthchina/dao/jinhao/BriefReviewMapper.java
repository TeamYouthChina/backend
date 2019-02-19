package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.*;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BriefReviewMapper {
    void add(BriefReviewMapper briefReviewMapper);
    void createReviewMap(@Param("review_id") Integer review_id,
                         @Param("user_id") Integer user_id, @Param("rela_type") Integer rela_type,
                         @Param("rela_id") Integer rela_id);
    void delete(Integer id);
    void deleteAllReviewEvaluationByReviewId(Integer id);
    void deleteAllCommentsByReviewId(Integer id);
    void deleteAllCommentEvaluationByReviewId(Integer id);
    void deleteAllDiscussesByReviewId(Integer id);
    void deleteAllDiscussEvaluateByReviewId(Integer id);
    void update(BriefReviewMapper briefReviewMapper);
    BriefReview get(Integer id);
    List<BriefReview> getList(List<Integer> ids);

    void addReviewEvaluation(Evaluate evaluate);
    void createEvaluationReviewMap(@Param("evaluate_id") Integer evaluate_id, @Param("review_id") Integer review_id);
    Evaluate checkEvaluateStatus(@Param("user_id") Integer user_id, @Param("review_id") Integer review_id);
    void updateEvaluation(Evaluate evaluate);
    Evaluate getEvaluate(Integer evaluate_id);

    void addComment(Comment comment);
    void createCommentReviewMap(@Param("comment_id") Integer comment_id,
                                @Param("comment_level") Integer comment_level,
                                @Param("review_id") Integer review_id);
    void deleteComment(Integer id);
    void deleteAllCommentEvaluationByCommentId(Integer id);
    void deleteAllDiscussEvaluationByCommentId(Integer id);
    void deleteAllDiscussByCommentId(Integer id);
    void updateComment(Comment comment);
    Comment getComment(Integer id);
    List<Comment> getAllCommentsOfReview(Integer id);

    void addCommentEvaluation(Evaluate evaluate);
    void createEvaluationCommentMap(@Param("evaluate_id") Integer evaluate_id,
                                    @Param("comment_id") Integer comment_id);
    CommentEvaluate checkCommentEvaluationStatus(@Param("user_id") Integer user_id,
                                                 @Param("comment_id") Integer comment_id);
    void updateCommentEvaluation(CommentEvaluate commentEvaluate);
    CommentEvaluate getCommentEvaluation(Integer evaluate_id);

    void addDiscuss(Discuss discuss);
    void createDiscussComment(@Param("discuss_id") Integer discuss_id,
                              @Param("discuss_level") Integer discuss_level,
                              @Param("comment_id") Integer comment_id);
    void deleteDiscuss(Integer id);
    void deleteAllDiscussEvaluationByDiscussId(Integer id);
    void updateDiscuss(Discuss discuss);
    Discuss getDiscuss(Integer id);
    List<Discuss> getAllDiscussOfComment(Integer id);

    void addDiscussEvaluation(DiscussEvaluate discussEvaluate);
    void createEvaluationDiscussMap(@Param("evaluate_id") Integer evaluate_id,
                                    @Param("discuss_id") Integer discuss_id);
    DiscussEvaluate checkDiscussEvaluateStatus(@Param("user_id") Integer user_id,
                                               @Param("discuss_id") Integer discuss_id);
    void updateDiscussEvaluation(DiscussEvaluate discussEvaluate);
    DiscussEvaluate getDiscussEvaluation(Integer evaluate_id);
}
