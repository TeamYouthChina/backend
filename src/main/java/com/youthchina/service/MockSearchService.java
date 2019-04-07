package com.youthchina.service;

import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.domain.zhongyang.SearchResultItem;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Hongsheng.SearchService;
import com.youthchina.service.tianjian.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 4/7/19.
 */
@Service("SearchService")
public class MockSearchService implements SearchService {
    private EssayService essayService;

    @Autowired
    public MockSearchService(EssayService essayService) {
        this.essayService = essayService;
    }

    @Override
    public SearchResult search(String type, String title, String body, Integer startIndex, Integer endIndex) {
        try {
            List<SearchResultItem> items = new ArrayList<>();
            ComEssay comEssay = this.essayService.getEssay(1);
            items.add(new SearchResultItem(comEssay, "article"));
            return new SearchResult(items, 3);
        } catch (
                NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
