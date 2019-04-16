package com.youthchina.service.util;

import com.youthchina.domain.zhongyang.SearchResult;

/**
 * Created by zhongyangwu on 4/7/19.
 */
public interface SearchService {

    SearchResult search(String type, String title, String body, Integer startIndex, Integer endIndex) throws Exception;
}
