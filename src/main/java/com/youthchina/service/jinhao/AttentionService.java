package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Attention;
import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface AttentionService extends DomainCRUDService<Attention,Integer> {
    void attention(Attentionable entity, Integer userId) throws NotFoundException;
    void cancel(Attentionable entity, Integer userId) throws NotFoundException;
    List<Integer> getAllIdsOfAttention(Attentionable entity, Integer userId) throws NotFoundException;
    Integer isEverAttention(Attentionable entity, Integer userId);
    Integer countAttention(Attentionable entity);
}
