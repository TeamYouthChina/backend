package com.youthchina.controller.zhongyang;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.util.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongyangwu on 4/9/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/recommend/**")
public class RecommendController {

    private final SearchController searchController;

    @Autowired
    public RecommendController(SearchController searchController) {
        this.searchController = searchController;
    }

    @GetMapping("/")
    public ResponseEntity<ListResponse> recommend(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search("all", "A", "B", pageRequest);
    }
}
