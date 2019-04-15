package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Attention;
import com.youthchina.domain.jinhao.property.Attentionable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AttentionMapper {
    Attention isEverAttention(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);

    void reFollow(Integer id);

    void follow(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);

    void cancel(Integer id);

    List<Integer> getAllfollows(@Param("type") Integer type, @Param("userId") Integer userId);

    Attention get(Integer id);

    Integer countAttention(@Param("type") Integer type, @Param("targetId") Integer targetId);

    List<Integer> getAttentionList(@Param("user_id") Integer userId, @Param("target_type") Integer targetType);

    default Integer cancelAttention(Attentionable attentionable, Integer userId) {
        return this.cancelAttention(attentionable.getAttentionTargetType(), attentionable.getId(), userId);
    }

    /**
     * cancel attention based on entity, usually happens then the entity is deleted
     *
     * @param targetId   target id
     * @param targetType attention type
     * @return
     * @see com.youthchina.util.dictionary.AttentionTargetType
     */
    Integer cancelAttentionByEntity(@Param("target_id") Integer targetId, @Param("target_type") Integer targetType);

    /**
     * Cancel Attention by
     *
     * @param attentionType   attention type
     * @param attentionableId target id
     * @param userId          user id
     * @return row that affect
     * @see com.youthchina.util.dictionary.AttentionTargetType
     */
    Integer cancelAttention(@Param("type") Integer attentionType, @Param("attention_id") Integer attentionableId, @Param("user_id") Integer userId);
}
