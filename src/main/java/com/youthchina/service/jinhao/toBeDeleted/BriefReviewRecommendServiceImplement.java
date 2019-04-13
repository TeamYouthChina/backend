package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.BriefReviewServiceImplement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BriefReviewRecommendServiceImplement implements BriefReviewRecommendService {
    @Resource
    RecommendMapper recommendMapper;
    @Resource
    BriefReviewServiceImplement briefReviewServiceImplement;
     @Override
    public List<BriefReview> getBriefReviewForYou() throws NotFoundException {
        List<Integer> briefReviewId = recommendMapper.getRandomBriefReview();
        List<BriefReview> briefReviews = new ArrayList<>();
        for(Integer id : briefReviewId){
            briefReviews.add(briefReviewServiceImplement.get(id));
        }
        return briefReviews;
    }

    @Override
    public BriefReview get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<BriefReview> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public BriefReview update(BriefReview briefReview) throws NotFoundException {
        return null;
    }

    @Override
    public BriefReview add(BriefReview entity) {
        return null;
    }
}
