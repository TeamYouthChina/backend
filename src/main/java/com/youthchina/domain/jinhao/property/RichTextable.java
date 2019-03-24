package com.youthchina.domain.jinhao.property;

import com.youthchina.domain.tianjian.ComRichText;

public interface RichTextable {
    ComRichText getRichText();
    void setRichText(ComRichText richText);
    Integer getRichTextRelaType();
    Integer getId();
}
