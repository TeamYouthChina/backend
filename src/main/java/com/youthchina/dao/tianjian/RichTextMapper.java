package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.ComRichText;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface RichTextMapper {
    void addRichText(ComRichText comRichText);

    ComRichText getRichText(@Param("textId") int textId);

    void updateRichText(ComRichText comRichText);

}
