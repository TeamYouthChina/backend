package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoRecommendServiceImplement implements VideoRecommendService{
    @Resource
    RecommendMapper recommendMapper;
    @Resource
    VideoService videoService;
     @Override
    public List<Video> getVideoForYou() throws NotFoundException{
        List<Integer> videoId = recommendMapper.getRandomVideo();
        List<Video> videos = new ArrayList<>();
        for(Integer id : videoId){
            videos.add(videoService.get(id));
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
