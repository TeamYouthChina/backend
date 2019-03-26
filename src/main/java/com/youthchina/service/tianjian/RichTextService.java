package com.youthchina.service.tianjian;

import com.youthchina.domain.tianjian.ComRichText;

public interface RichTextService {
    ComRichText getComRichText(int textId);

    void addComRichText(ComRichText comRichText);

    void updateComRichText(ComRichText comRichText);
}
