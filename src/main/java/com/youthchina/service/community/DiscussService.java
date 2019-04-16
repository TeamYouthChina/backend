package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface DiscussService extends DomainCRUDService<Discuss, Integer> {
    /**
     * get all the discusses of a comment
     * @param id id of the comment
     * @return a list of comment
     * @throws NotFoundException
     */

    List<Discuss> getDiscussesByCommentId(Integer id) throws NotFoundException;
    List<Discuss> getDiscussesByCommentId(Integer id, Integer start, Integer end);

    void isDiscussExist(Integer id) throws NotFoundException;
    void deleteAllDiscussOfComment(Integer id);
    Integer count(Integer id);
}
