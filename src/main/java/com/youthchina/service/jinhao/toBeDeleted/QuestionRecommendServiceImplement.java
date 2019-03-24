package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionRecommendServiceImplement implements QuestionRecommendService {
    @Resource
    RecommendMapper recommendMapper;
    @Resource
    CommunityQAMapper communityQAMapper;

    @Override
    public List<Question> getQuestionForYou() {
        List<Integer> questionIds = recommendMapper.getRandomQuestion();
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionIds) {
            questions.add(communityQAMapper.getQuestionById(id));
        }
        return questions;
    }

    @Override
    public Question get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Question> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Question update(Question question) throws NotFoundException {
        return null;
    }

    @Override
    public Question add(Question entity) {
        return null;
    }
}
