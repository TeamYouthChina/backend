package com.youthchina.service.community;

import com.youthchina.dao.jinhao.DiscussMapper;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Resource
    DiscussMapper discussMapper;

    @Resource
    CommentService commentService;

    @Resource
    UserService userService;

    @Override
    @Transactional
    public List<Discuss> getDiscussesByCommentId(Integer id) {
        List<Discuss> discusses = discussMapper.getDiscusses(id);
        for (Discuss discuss : discusses) {
            try {
                discuss.setUser(userService.get(discuss.getUser().getId()));
            } catch (NotFoundException e) {

            }
        }
        return discusses;
    }

    @Override
    public List<Discuss> getDiscussesByCommentId(Integer id, Integer start, Integer end) {
        List<Discuss> discusses = discussMapper.getLimitedDiscusses(id, start, end - start + 1);
        for (Discuss discuss : discusses) {
            try {
                discuss.setUser(userService.get(discuss.getUser().getId()));
            } catch (NotFoundException e) {

            }
        }
        return discusses;
    }

    @Override
    public void isDiscussExist(Integer id) throws NotFoundException {
        if (discussMapper.checkIfDiscussExist(id) == null) {
            throw new NotFoundException(404, 404, "没有找到这个讨论");
        }
    }

    @Override
    public void deleteAllDiscussOfComment(Integer id) {
        discussMapper.deleteAllDiscussOfComment(id);
    }

    @Override
    public Integer count(Integer id) {
        return discussMapper.count(id);
    }

    @Override
    @Transactional
    public Discuss add(Discuss discuss) throws NotFoundException {
        commentService.isCommentExist(discuss.getCommentId());
        discussMapper.add(discuss);
        return discuss;
    }

    @Override
    public Discuss get(Integer id) throws NotFoundException {
        Discuss discuss = discussMapper.get(id);
        if (discuss == null) {
            throw new NotFoundException(4040, 404, "No entity found");
        } else {
            discuss.setUser(userService.get(discuss.getUser().getId()));
            return discuss;
        }
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        isDiscussExist(id);
        discussMapper.delete(id);
    }

    @Override
    public Discuss update(Discuss discuss) throws NotFoundException {
        return null;
    }
}
