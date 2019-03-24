package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.communityQA.*;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BriefReviewMapper {
    void add(BriefReview briefReview);
    void createReviewMap(@Param("review_id") Integer review_id,
                         @Param("user_id") Integer user_id, @Param("rela_type") Integer rela_type,
                         @Param("rela_id") Integer rela_id);
    List<BriefReview> getUsersReview(Integer user_id);
    void delete(Integer id);
    void deleteAllReviewEvaluationByReviewId(Integer id);
    void deleteAllCommentsByReviewId(Integer id);
    void deleteAllCommentEvaluationByReviewId(Integer id);
    void deleteAllDiscussesByReviewId(Integer id);
    void deleteAllDiscussEvaluateByReviewId(Integer id);
    void update(BriefReview briefReview);
    BriefReview simplyGetReview(Integer id);
    BriefReview get(Integer id);
    List<BriefReview> getList(List<Integer> ids);

    List<BriefReview> getUserUpvoteReview(Integer user_id);
    Evaluate getEvaluation(Integer evaluate_id);
    void addReviewEvaluation(Evaluate evaluate);
    void createEvaluationReviewMap(@Param("evaluate_id") Integer evaluate_id, @Param("review_id") Integer review_id);
    Evaluate checkEvaluateStatus(@Param("user_id") Integer user_id, @Param("review_id") Integer review_id);
    void updateEvaluation(Evaluate evaluate);
    Integer countReviewAgreement(Integer id);

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
    Comment simplyGetComment(Integer id);
    List<Comment> getCommentsByReviewId(Integer id);

    void addCommentEvaluation(Evaluate evaluate);
    void createEvaluationCommentMap(@Param("evaluate_id") Integer evaluate_id,
                                    @Param("comment_id") Integer comment_id);
    Evaluate checkCommentEvaluationStatus(@Param("user_id") Integer user_id,
                                                 @Param("comment_id") Integer comment_id);
    void updateCommentEvaluation(Evaluate evaluate);
    Integer countCommentAgreement(Integer id);
    Evaluate getCommentEvaluation(Integer id);

    void addDiscuss(Discuss discuss);
    void createDiscussCommentMap(@Param("discuss_id") Integer discuss_id,
                              @Param("discuss_level") Integer discuss_level,
                              @Param("comment_id") Integer comment_id);
    void deleteDiscuss(Integer id);
    void deleteAllDiscussEvaluationByDiscussId(Integer id);
    void updateDiscuss(Discuss discuss);
    Discuss getDiscuss(Integer id);
    List<Discuss> getAllDiscussOfComment(Integer id);

    void addDiscussEvaluation(Evaluate evaluate);
    void createEvaluationDiscussMap(@Param("evaluate_id") Integer evaluate_id,
                                    @Param("discuss_id") Integer discuss_id);
    Evaluate checkDiscussEvaluateStatus(@Param("user_id") Integer user_id,
                                               @Param("discuss_id") Integer discuss_id);
    void updateDiscussEvaluation(Evaluate evaluate);
    Evaluate getDiscussEvaluation(Integer evaluate_id);
    Integer countDiscussEvaluate(Integer id);
}
