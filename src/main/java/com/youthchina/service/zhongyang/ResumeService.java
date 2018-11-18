package com.youthchina.service.zhongyang;

import com.youthchina.domain.Qinghong.Resume;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.service.DomainCRUDService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zhongyangwu on 11/18/18.
 */
@Service
public interface ResumeService extends DomainCRUDService<Resume, String> {

    /**
     * get resume by resume Id
     *
     * @param user   user
     * @param resumeId resume id
     * @return resume
     * @throws NotBelongException throw if user does not have target resume
     */
    Resume get(User user, String resumeId) throws NotBelongException;

    void delete(User user, String resumeId) throws NotFoundException;


}
