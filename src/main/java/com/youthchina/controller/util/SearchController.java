package com.youthchina.controller.util;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.domain.zhongyang.SearchResultItem;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.briefreview.BriefReviewResponseDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobResponseDTO;
import com.youthchina.dto.search.SearchResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.BaseException;
import com.youthchina.service.util.SearchService;
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
    public ResponseEntity<ListResponse> search(@RequestParam(value = "type", defaultValue = "all") String type, @RequestParam(value = "title",required = false) String title, @RequestParam(value = "text",required = false) String body, PageRequest pageRequest) throws Exception {
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
            case "editorial": {
                return new BriefReviewResponseDTO((BriefReview) item.getDomain());
            }
            case "comment": {
                return new CommentResponseDTO((Comment) item.getDomain());
            }
            case "user": {
                return new UserDTO((User) item.getDomain());
            }
            default:
                throw new BaseException(5000, 500, "no suitable converter found for search result");
        }
    }
}
