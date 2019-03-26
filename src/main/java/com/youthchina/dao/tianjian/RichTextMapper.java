package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.ComRichText;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface RichTextMapper {
    void addRichText(ComRichText comRichText);

    ComRichText getAnswerBody(Integer id);
    ComRichText getQuestionBody(Integer id);
    ComRichText getEssayBody(Integer id);
    ComRichText getBriefReviewBody(Integer id);

    void updateRichText(ComRichText comRichText);

    List<ComRichText> getSameTypeRichText(int rich_type);
}
