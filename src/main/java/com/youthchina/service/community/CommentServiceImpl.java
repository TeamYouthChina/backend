package com.youthchina.service.community;

import com.youthchina.dao.jinhao.CommentMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import com.youthchina.util.LoggedInUserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    EvaluateService evaluateService;

    @Resource
    DiscussService discussService;

    @Resource
    UserService userService;

    @Resource
    IsExistService isExistService;

    @Override
    public List<Comment> getComments(Commentable entity) {
        List<Comment> comments = commentMapper.getComments(entity.getCommentTargetType(), entity.getId());
        for (Comment comment : comments) {
            try {
                comment.setUser(userService.get(comment.getUser().getId()));
                comment.setEvaluateStatus(evaluateService.evaluateStatus(comment, LoggedInUserUtil.currentUser().getId()));
                comment.setUpvoteCount(evaluateService.countUpvote(comment));
                comment.setDownvoteCount(evaluateService.countDownvote(comment));
            } catch (NotFoundException e) {
            }
        }
        return comments;
    }

    @Override
    public List<Comment> getComments(Commentable entity, Integer start, Integer limit) {
        List<Comment> comments = commentMapper.getLimitedComments(entity.getCommentTargetType(), entity.getId(), start, limit);
        for (Comment comment : comments) {
            try {
                comment.setUser(userService.get(comment.getUser().getId()));
                comment.setEvaluateStatus(evaluateService.evaluateStatus(comment,LoggedInUserUtil.currentUser().getId()));
                comment.setUpvoteCount(evaluateService.countUpvote(comment));
                comment.setDownvoteCount(evaluateService.countDownvote(comment));
            } catch (NotFoundException e) {
            }
        }
        return comments;
    }

    @Override
    @Transactional
    public List<Comment> get(List<Integer> ids) {
        List<Comment> comments = new ArrayList<>();
        for(Integer id : ids){
            try {
                Comment comment = get(id);
                comment.setEvaluateStatus(evaluateService.evaluateStatus(comment,LoggedInUserUtil.currentUser().getId()));
                comment.setUpvoteCount(evaluateService.countUpvote(comment));
                comment.setDownvoteCount(evaluateService.countDownvote(comment));
                comments.add(comment);
            } catch (NotFoundException e) {

            }
        }
        return comments;
    }

    @Override
    public void isCommentExist(Integer id) throws NotFoundException {
        if (commentMapper.checkIfCommentExist(id) == null) {
            throw new NotFoundException(404, 404, "该评论不存在");
        }
    }

    @Override
    @Transactional
    public Comment add(Comment comment, Commentable entity) throws NotFoundException {
        Integer type = entity.getCommentTargetType();
        Integer targetId = entity.getId();
        isExistService.isExist(entity);
        comment.setTargetType(type);
        comment.setTargetId(targetId);
        commentMapper.add(comment);
        comment.setEvaluateStatus(evaluateService.evaluateStatus(comment,LoggedInUserUtil.currentUser().getId()));
        comment.setUpvoteCount(evaluateService.countUpvote(comment));
        comment.setDownvoteCount(evaluateService.countDownvote(comment));
        return comment;
    }

    @Override
    public Integer countComments(Commentable entity) {
        return commentMapper.count(entity.getCommentTargetType(), entity.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        isCommentExist(id);
        List<Discuss> discusses = discussService.getDiscussesByCommentId(id);
        for(Discuss discuss : discusses){
            discussService.delete(discuss.getId());
        }
        Comment comment = new Comment();
        comment.setId(id);
        evaluateService.cancel(comment);
        commentMapper.delete(id);
    }


    @Override
    @Transactional
    public Comment get(Integer id) throws NotFoundException {
        Comment comment = commentMapper.get(id);
        if (comment == null) {
            throw new NotFoundException(404, 404, "This comment dose not exist!");
        }
        comment.setUser(userService.get(comment.getUser().getId()));
        comment.setEvaluateStatus(evaluateService.evaluateStatus(comment,LoggedInUserUtil.currentUser().getId()));
        comment.setUpvoteCount(evaluateService.countUpvote(comment));
        comment.setDownvoteCount(evaluateService.countDownvote(comment));
        return comment;
    }

}
