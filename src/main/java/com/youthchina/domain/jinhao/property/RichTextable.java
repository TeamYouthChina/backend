package com.youthchina.domain.jinhao.property;

import com.youthchina.domain.tianjian.ComRichText;

public interface RichTextable extends IsExist{
    ComRichText getBody();
    void setBody(ComRichText richText);
    Integer getRichTextRelaType();
    Integer getId();
}
