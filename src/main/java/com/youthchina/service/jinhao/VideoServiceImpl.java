package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.VideoMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.exception.zhongyang.NotFoundException;
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

    @Override
    public void isVideoExist(Integer id) throws NotFoundException {
        if(videoMapper.checkIfVideoExist(id) == null){
            throw new NotFoundException(404,404,"该视频不存在");
        }
    }

    @Override
    @Transactional
    public Video get(Integer id) throws NotFoundException {
        Video video = videoMapper.get(id);
        if(video == null){
            throw new NotFoundException(404,404,"这个视频不存在");
        }
        commentService.getComments(video);
        return video;
    }

    @Override
    @Transactional
    public List<Video> get(List<Integer> id) throws NotFoundException {
        List<Video> videos = new LinkedList<>();
        for(Integer one : id){
            Video video = videoMapper.get(one);
            if(video != null){
                List<Comment> comments = commentService.getComments(video);
                video.setComments(comments);
                videos.add(video);
            }
        }
        if(videos.size() == 0){
            throw new NotFoundException(404,404,"没有找到这些视频");
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
