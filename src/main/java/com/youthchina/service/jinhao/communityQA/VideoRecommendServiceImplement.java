package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoRecommendServiceImplement implements VideoRecommendService{
    @Resource
    RecommendMapper recommendMapper;
    @Resource
    CommunityQAMapper communityQAMapper;
     @Override
    public List<Video> getVideoForYou() {
        List<Integer> videoId = recommendMapper.getRandomVideo();
        List<Video> videos = new ArrayList<>();
        for(Integer id : videoId){
            videos.add(communityQAMapper.getVideoById(id));
        }
        return videos;
    }

    @Override
    public Video get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Video> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Video update(Video video) throws NotFoundException {
        return null;
    }

    @Override
    public Video add(Video entity) {
        return null;
    }
}
