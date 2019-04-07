package com.youthchina.service;

import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.service.Hongsheng.SearchService;
import org.springframework.stereotype.Service;

/**
 * Created by zhongyangwu on 4/7/19.
 */
@Service("SearchService")
public class MockSearchService implements SearchService {
    @Override
    public SearchResult search(String type, String title, String body, Integer startIndex, Integer endIndex) {
        return null;
    }
}
