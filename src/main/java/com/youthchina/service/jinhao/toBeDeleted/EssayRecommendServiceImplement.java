package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EssayRecommendServiceImplement implements EssayRecommendService {
    @Resource
    RecommendMapper recommendMapper;

    @Resource
    CommunityMapper communityMapper;

    @Override
    public List<ComEssay> getEssayForYou() {
        List<Integer> essayIds = recommendMapper.getRandomEssay();
        List<ComEssay> comEssays = new ArrayList<>();
        for (Integer id : essayIds) {
            comEssays.add(communityMapper.getEssay(id));
        }
        return comEssays;
    }

    @Override
    public ComEssay get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<ComEssay> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public ComEssay update(ComEssay comEssay) throws NotFoundException {
        return null;
    }

    @Override
    public ComEssay add(ComEssay entity) {
        return null;
    }
}
