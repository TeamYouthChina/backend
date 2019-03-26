package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Attention;
import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttentionService extends DomainCRUDService<Attention,Integer> {
    void attention(Attentionable entity, Integer userId) throws NotFoundException;
    void cancel(Attentionable entity, Integer userId) throws NotFoundException;
    List<Integer> getAllIdsOfAttention(Attentionable entity, Integer userId) throws NotFoundException;
}
