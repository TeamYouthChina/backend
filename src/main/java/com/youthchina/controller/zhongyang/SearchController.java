package com.youthchina.controller.zhongyang;

import com.youthchina.domain.jinhao.*;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.domain.zhongyang.SearchResultItem;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.community.video.VideoResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
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
    public ResponseEntity<ListResponse> search(@RequestParam(value = "type", defaultValue = "all") String type, @RequestParam(value = "title", defaultValue = "*") String title, @RequestParam(value = "text", defaultValue = "*") String body, PageRequest pageRequest) throws Exception {
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
            case "question": {
                return new QuestionResponseDTO((Question) item.getDomain());
            }
            case "answer": {
                return new SimpleAnswerResponseDTO((Answer) item.getDomain());
            }
            case "job": {
                return new JobResponseDTO((Job) item.getDomain());
            }
            case "company": {
                return new CompanyResponseDTO((Company) item.getDomain());
            }
            case "video": {
                return new VideoResponseDTO((Video) item.getDomain());
            }
            case "briefReview": {
                return new BriefReviewResponseDTO((BriefReview) item.getDomain());
            }
            case "comment": {
                return new CommentDTO((Comment) item.getDomain());
            }
            default:
                throw new BaseException(5000, 500, "no suitable converter found for search result");
        }
    }
}
