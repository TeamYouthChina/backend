package com.youthchina.service.community;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.util.List;

public interface AttentionService {
    void attention(Attentionable entity, Integer userId) throws NotFoundException;

    void cancel(Attentionable entity, Integer userId) throws NotFoundException;

    List<Integer> getAllIdsOfAttention(Integer targetType, Integer userId);

    Integer isEverAttention(Attentionable entity, Integer userId);

    Integer countAttention(Attentionable entity);

    /**
     * Delete all attention whose target is parameter
     *
     * @param attentionable target
     */
    void cancel(Attentionable attentionable);

}
