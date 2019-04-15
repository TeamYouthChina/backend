package com.youthchina.repository;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.ComEssay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by zhongyangwu on 4/14/19.
 */
@Component
public class EssayRepository implements BaseRepository<Integer, ComEssay> {

    private CommunityMapper communityMapper;

    @Autowired
    public EssayRepository(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    @Override
    public ComEssay get(Integer id) {
        return communityMapper.getEssay(id);
    }

    @Override
    @Transactional
    public void delete(ComEssay entity) {
        communityMapper.deleteEssay(entity.getId(), new Timestamp(System.currentTimeMillis()));


    }
}
