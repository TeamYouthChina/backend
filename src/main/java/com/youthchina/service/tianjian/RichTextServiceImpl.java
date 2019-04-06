package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.RichTextMapper;
import com.youthchina.domain.jinhao.property.RichTextable;
import com.youthchina.domain.tianjian.ComRichText;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("RichTextServiceImpl")
public class RichTextServiceImpl implements RichTextService {
    @Resource
    RichTextMapper richTextMapper;

    @Override
    public void getComRichText(RichTextable richTextable) {
        Integer type = richTextable.getRichTextRelaType();
        switch (type){
            case 1: richTextable.setBody(this.getEssayBody(richTextable.getId())); break;
            case 2: richTextable.setBody(this.getQuestionBody(richTextable.getId())); break;
            case 3: richTextable.setBody(this.getBriefReviewBody(richTextable.getId())); break;
            case 4: richTextable.setBody(this.getAnswerBody(richTextable.getId())); break;
        }
    }

    private ComRichText getAnswerBody(int id){
        return richTextMapper.getAnswerBody(id);
    }
    private ComRichText getQuestionBody(int id){
        return richTextMapper.getQuestionBody(id);
    }
    private ComRichText getBriefReviewBody(int id){
        return richTextMapper.getBriefReviewBody(id);
    }
    private ComRichText getEssayBody(int id){
        return richTextMapper.getEssayBody(id);
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