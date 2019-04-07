package com.youthchina.controller.zhongyang;

import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.domain.zhongyang.SearchResultItem;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.search.SearchResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.service.Hongsheng.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhongyangwu on 4/7/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/search/**")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/")
    public ResponseEntity<ListResponse> search(@RequestParam(value = "type", defaultValue = "all") String type, @RequestParam(value = "title", defaultValue = "*") String title, @RequestParam(value = "text", defaultValue = "*") String body, PageRequest pageRequest) throws BaseException {
        SearchResult searchResult = searchService.search(type, title, body, pageRequest.getStart(), pageRequest.getEnd());
        List<SearchResponseDTO> searchResultDtos = new ArrayList<>();
        for (SearchResultItem item : searchResult.getResult()) {
            searchResultDtos.add(new SearchResponseDTO(this.convertToDTO(item), item.getType()));
        }
        return ResponseEntity.ok(new ListResponse(pageRequest, searchResult.getCount(), searchResultDtos));
    }

    private ResponseDTO convertToDTO(SearchResultItem item) throws BaseException {
        switch (item.getType()) {
            case "article": {
                return new EssayResponseDTO((ComEssay) item.getDomain());
            }
            default:
                throw new BaseException(5000, 500, "no suitable converter found for search result");
        }
    }
}
