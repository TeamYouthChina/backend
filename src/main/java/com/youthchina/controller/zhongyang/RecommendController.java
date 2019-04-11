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
@RequestMapping({"${web.url.prefix}/recommend/**", "${web.url.prefix}/discovery/**"})
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

    @GetMapping("/companies")
    public ResponseEntity<ListResponse> recommendCompany(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search("company", "t", null, pageRequest);
    }

    @GetMapping("/users")
    public ResponseEntity<ListResponse> recommendUser(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search("user", "t", null, pageRequest);
    }

    @GetMapping("/articles")
    public ResponseEntity<ListResponse> recommendArticles(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search("article", "t", null, pageRequest);
    }

    @GetMapping("/questions")
    public ResponseEntity<ListResponse> recommendQuestions(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search("question", "t", null, pageRequest);
    }

    @GetMapping("/jobs")
    public ResponseEntity<ListResponse> recommendJobs(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search("job", "t", null, pageRequest);
    }

    @GetMapping("/editorials")
    public ResponseEntity<ListResponse> recommendEditorials(@AuthenticationPrincipal User user, PageRequest pageRequest) throws Exception {
        return searchController.search("briefReview", "t", null, pageRequest);
    }
}
