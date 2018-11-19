package com.youthchina.service.zhongyang;

import com.youthchina.controller.zhongyang.ResumeController;
import com.youthchina.domain.Qinghong.Resume;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongyangwu on 11/18/18.
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Override
    public Resume get(User user, String resumeId) throws NotBelongException {
        return null;
    }

    @Override
    public void delete(User user, String resumeId) throws NotFoundException {

    }

    @Override
    public Resume get(String id) {
        return null;
    }

    @Override
    public List<Resume> get(List<String> id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Resume update(Resume resume) {
        return null;
    }

    @Override
    public Resume add(Resume entity) {
        return null;
    }
}
