package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface RichTextMapper {
    int addRichText(ComRichText comRichText);

    ComRichText getRichText(@Param("rela_id") int rela_id,@Param("rela_type") int rela_type);

    int updateRichText(ComRichText comRichText);

    List<ComRichText> getSameTypeRichText(int rich_type);
}
