package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.property.Attentionable;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttentionService {
    void attention(Attentionable entity, Integer userId) throws NotFoundException;
    void cancel(Attentionable entity, Integer userId) throws NotFoundException;
    List<Integer> getAllIdsOfAttention(Attentionable entity, Integer userId) throws NotFoundException;
}
