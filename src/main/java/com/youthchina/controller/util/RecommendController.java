package com.youthchina.controller.util;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.util.dictionary.SearchType;
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
@RequestMapping({"${web.url.prefix}/recommend/**"})
public class RecommendController {

    private final SearchController searchController;

    @Autowired
    public RecommendController(SearchController searchController) {
        this.searchController = searchController;
    }

    @GetMapping("/")
    public ResponseEntity<ListResponse> recommend(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search(SearchType.ALL, "*", "*", pageRequest);
    }

    @GetMapping("/companies")
    public ResponseEntity<ListResponse> recommendCompany(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search(SearchType.COMPANY, "*", "*", pageRequest);
    }

    @GetMapping("/users")
    public ResponseEntity<ListResponse> recommendUser(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search(SearchType.USER, "*", null, pageRequest);
    }

    @GetMapping("/articles")
    public ResponseEntity<ListResponse> recommendArticles(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search(SearchType.ARTICLE, "*", null, pageRequest);
    }

    @GetMapping("/questions")
    public ResponseEntity<ListResponse> recommendQuestions(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search(SearchType.QUESTION, "*", "*", pageRequest);
    }

    @GetMapping("/jobs")
    public ResponseEntity<ListResponse> recommendJobs(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search(SearchType.JOB, "*", "*", pageRequest);
    }

    @GetMapping("/editorials")
    public ResponseEntity<ListResponse> recommendEditorials(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search(SearchType.EDITORIAL, "*", "*", pageRequest);
    }
}
