package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Video;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface VideoMapper {
    Video get(Integer id);
    void add(Video video);
    void delete(Integer id);
    void update(Video video);
    Integer checkIfVideoExist(Integer id);
    Integer count();
    List<Video> getMyVideo(Integer id);
}
