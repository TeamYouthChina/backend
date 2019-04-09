package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.VideoMapper;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    VideoMapper videoMapper;

    @Resource
    CommentService commentService;

    @Resource
    UserService userService;

    @Override
    public void isVideoExist(Integer id) throws NotFoundException {
        if(videoMapper.checkIfVideoExist(id) == null){
            throw new NotFoundException(4040,404,"该视频不存在");//todo
        }
    }

    @Override
    public Integer count() {
        return videoMapper.count();
    }

    @Override
    @Transactional
    public Video get(Integer id) throws NotFoundException {
        Video video = videoMapper.get(id);
        if(video == null){
            throw new NotFoundException(4040,404,"这个视频不存在");//todo
        }
        video.setUser(userService.get(video.getUser().getId()));
        commentService.getComments(video);
        return video;
    }

    @Override
    @Transactional
    public List<Video> get(List<Integer> id) {
        List<Video> videos = new LinkedList<>();
        for(Video video : videos){
            try {
                video.setUser(userService.get(video.getUser().getId()));
            } catch (NotFoundException e) {

            }
        }
        return videos;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        isVideoExist(id);
        Video video = new Video();
        video.setId(id);
        commentService.delete(video);
        videoMapper.delete(id);
    }

    @Override
    public Video update(Video video) throws NotFoundException {
        isVideoExist(video.getId());
        videoMapper.update(video);
        return video;
    }

    @Override
    public Video add(Video entity) {
        videoMapper.add(entity);
        return entity;
    }
}
