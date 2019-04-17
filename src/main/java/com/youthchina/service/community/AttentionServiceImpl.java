package com.youthchina.service.community;

import com.youthchina.dao.jinhao.AttentionMapper;
import com.youthchina.domain.jinhao.Attention;
import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {
    @Resource
    AttentionMapper attentionMapper;

    @Resource
    IsExistService isExistService;

    @Override
    @Transactional
    public void attention(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
        Integer id = entity.getId();
        isExistService.isExist(entity);
        Attention attention = attentionMapper.isEverAttention(type, id, userId);
        if (attention == null) {
            attentionMapper.follow(type, id, userId);
        } else {
            if (attention.getIsCancel() == 0) {
                throw new NotFoundException(4040, 404, "You have already followed!");//todo
            }
            attentionMapper.reFollow(attention.getId());
        }

    }

    @Override
    @Transactional
    public void cancel(Attentionable entity, Integer userId) throws NotFoundException {
        Integer type = entity.getAttentionTargetType();
        Integer id = entity.getId();
        isExistService.isExist(entity);
        Attention attention = attentionMapper.isEverAttention(type, id, userId);
        if (attention == null || attention.getIsCancel() == 1) {
            throw new NotFoundException(4040, 404, "You have not followed yet, you cannot cancel!");//todo
        } else {
            attentionMapper.cancel(attention.getId());
        }
    }

    @Override
    public List<Integer> getAllIdsOfAttention(Integer targetType, Integer userId) {
        return attentionMapper.getAllfollows(targetType, userId);
    }

    @Override
    public Integer isEverAttention(Attentionable entity, Integer userId) {
        Attention attention = attentionMapper.isEverAttention(entity.getAttentionTargetType(), entity.getId(), userId);
        if (attention == null) {
            return 0;
        } else {
            return attention.getIsCancel();
        }
    }

    @Override
    public Integer countAttention(Attentionable entity) {
        return attentionMapper.countAttention(entity.getAttentionTargetType(), entity.getId());
    }

    @Override
    public void cancel(Attentionable attentionable) {
        this.attentionMapper.cancelAttentionByEntity(attentionable.getId(), attentionable.getAttentionTargetType());
    }
}
