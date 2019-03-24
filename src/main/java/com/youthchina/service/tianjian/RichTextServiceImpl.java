package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.RichTextMapper;
import com.youthchina.domain.tianjian.ComRichText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RichTextServiceImpl")
public class RichTextServiceImpl implements RichTextService {
    @Autowired
    RichTextMapper richTextMapper;
    @Override
    public ComRichText getComRichText(int relaId, int relaType) {
        return richTextMapper.getRichText(relaId,relaType);
    }

    @Override
    public void addComRichText(ComRichText comRichText) {
        richTextMapper.addRichText(comRichText);
    }

    @Override
    public void updateComRichText(ComRichText comRichText) {
        richTextMapper.updateRichText(comRichText);
    }
}
