package com.youthchina.service.tianjian;

import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;

public interface RichTextService {
    void getComRichText(RichTextable richTextable);

    void addComRichText(ComRichText comRichText);

    void updateComRichText(ComRichText comRichText);
}
