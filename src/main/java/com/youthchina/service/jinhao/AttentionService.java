package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.NotFoundException;

import java.util.List;

public interface AttentionService {
    void attention(Attentionable entity, Integer userId) throws NotFoundException;
    void cancel(Attentionable entity, Integer userId) throws NotFoundException;
    List<Integer> getAllIdsOfAttention(Attentionable entity, Integer userId) throws NotFoundException;
}
