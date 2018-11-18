package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.Question;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComminityQAServiceImplement implements CommunityQAService {
    @Resource
    CommunityQAMapper communityQAMapper;

    @Override
    public Integer addQuestion(Question question) {
        communityQAMapper.addQuestion(question);
        return question.getQues_id();
    }
}
