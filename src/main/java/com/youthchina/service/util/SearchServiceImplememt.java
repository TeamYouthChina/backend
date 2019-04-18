package com.youthchina.service.util;

import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.domain.zhongyang.SearchResultItem;
import com.youthchina.exception.zhongyang.exception.BaseException;
import com.youthchina.exception.zhongyang.exception.ClientException;
import com.youthchina.exception.zhongyang.exception.InternalStatusCode;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.application.CompanyCURDService;
import com.youthchina.service.application.JobService;
import com.youthchina.service.community.*;
import com.youthchina.service.user.UserService;
import com.youthchina.util.dictionary.SearchType;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("SearchService")
public class SearchServiceImplememt implements SearchService {
    private static String SOLR_URL;

    @Resource
    private UserService userService;

    @Resource
    private EssayService essayService;

    @Resource
    private QuestionService questionService;

    @Resource
    private JobService jobService;

    @Resource
    private CommentService commentService;

    @Resource
    private VideoService videoService;

    @Resource
    private CompanyCURDService companyCURDService;

    @Resource
    private AnswerService answerService;

    @Resource
    private BriefReviewService briefReviewService;

    @Autowired
    public SearchServiceImplememt(@Value("${search.url}") String solr_url) {
        SOLR_URL = solr_url;
    }

    @Override
    public SearchResult search(String type, String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        return doSearch(getClient(), getQuery(type, title, body, startIndex, endIndex));
    }

    private HttpSolrClient getClient() {
        return new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
    }

    private SolrQuery getQuery(String type, String title, String body, Integer startIndex, Integer endIndex) throws ClientException {
        if(title == null && body == null){
            throw new ClientException("Must specific a search param");
        }
        SolrQuery query = new SolrQuery();
        String queryType = getSolrTypeBySearchType(type);
        query.set("fq", "type:" + queryType);
//        if(queryType.equals("JOB") || queryType.equals("COMMENT") || queryType.equals("ANSWER") || queryType.equals())
        String queryString = "";
        switch (queryType) {
            case "REVIEW":
                queryString = "body:" + (body == null ? "*" : body);
                break;
            case "JOB":
                queryString = "body:" + (body == null ? "*" : body) + " OR title:" + (title == null ? "*" : title);
                break;
            case "USER":
                queryString = "title:" + (title == null ? "*" : title);
                break;
            case "COMMENT":
                queryString = "body:" + (body == null ? "*" : body);
                break;
            case "COMPANY":
                queryString = "body:" + (body == null ? "*" : body) + " OR title:" + (title == null ? "*" : title);
                break;
            case "ANSWER":
                queryString = "body:" + (body == null ? "*" : body);
                break;
            case "QUESTION":
                queryString = "body:" + (body == null ? "*" : body);
                break;
            case "ESSAY":
                queryString = "body:" + (body == null ? "*" : body) + " OR title:" + (title == null ? "*" : title);
                break;
            case "*":
                queryString = "body:" + (body == null ? "*" : body) + " OR title:" + (title == null ? "*" : title);
                break;
            default:
                throw new ClientException("cannot search target type, please try one of the following " + SearchType.getNameString());
        }

        query.setQuery(queryString);
        query.setStart(startIndex);
        query.setRows(endIndex - startIndex + 1);
        return query;

    }

    private SearchResult doSearch(HttpSolrClient httpSolrClient, SolrQuery query) throws BaseException {
        QueryResponse response;
        try {
            response = httpSolrClient.query(query);
        } catch (SolrServerException | IOException e) {
            throw new BaseException(InternalStatusCode.SERVICE_UNAVAILABLE.value(), HttpStatus.SERVICE_UNAVAILABLE.value(), InternalStatusCode.SERVICE_UNAVAILABLE.getMessage());
        }
        SearchResult searchResult = new SearchResult();
        SolrDocumentList solrDocumentList = response.getResults();
        searchResult.setCount(Math.toIntExact(solrDocumentList.getNumFound()));
        List<Integer> idList = new ArrayList<>();
        List<String> typeList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            String id = doc.get("id").toString();
            //System.out.println(id);
            String[] sp = id.split("_");
            typeList.add(sp[0]);
            idList.add(Integer.parseInt(sp[1]));
        }

        List<SearchResultItem> resultItemList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            resultItemList.add(getSingleResponse(idList.get(i), getSearchTypeBySolrType(typeList.get(i))));
        }
        searchResult.setResult(resultItemList);
        return searchResult;
    }

    private SearchResultItem getSingleResponse(Integer id, String type) throws ClientException, NotFoundException {
        switch (type) {
            case SearchType.ARTICLE: {
                return new SearchResultItem(essayService.get(id), SearchType.ARTICLE);
            }
            case SearchType.QUESTION: {
                return new SearchResultItem(questionService.get(id), SearchType.QUESTION);
            }
            case SearchType.ANSWER: {
                return new SearchResultItem(answerService.get(id), SearchType.ANSWER);
            }
            case SearchType.JOB: {
                return new SearchResultItem(jobService.get(id), SearchType.JOB);
            }
            case SearchType.COMPANY: {
                return new SearchResultItem(companyCURDService.get(id), SearchType.COMPANY);
            }
            case SearchType.EDITORIAL: {
                return new SearchResultItem(briefReviewService.get(id), SearchType.EDITORIAL);
            }
            case SearchType.COMMENT: {
                return new SearchResultItem(commentService.get(id), SearchType.COMMENT);
            }
            case SearchType.USER: {
                return new SearchResultItem(userService.get(id), SearchType.USER);
            }
            default:
                throw new ClientException("cannot search target type, please try one of the following " + SearchType.getNameString());
        }
    }

    private String getSolrTypeBySearchType(String type) throws ClientException {
        String queryType = null;
        switch (type) {
            case SearchType.ARTICLE: {
                queryType = "ESSAY";
                break;
            }
            case SearchType.QUESTION: {
                queryType = "QUESTION";
                break;
            }
            case SearchType.ANSWER: {
                queryType = "ANSWER";
                break;
            }
            case SearchType.JOB: {
                queryType = "JOB";
                break;
            }
            case SearchType.COMPANY: {
                queryType = "COMPANY";
                break;
            }
            case SearchType.EDITORIAL: {
                queryType = "REVIEW";
                break;
            }
            case SearchType.COMMENT: {
                queryType = "COMMENT";
                break;
            }
            case SearchType.USER: {
                queryType = "USER";
                break;
            }
            case SearchType.ALL: {
                queryType = "*";
                break;
            }
            default:
                throw new ClientException("cannot search target type, please try one of the following " + SearchType.getNameString());
        }
        return queryType;
    }

    private String getSearchTypeBySolrType(String type) throws ClientException {

        String queryType = null;
        switch (type) {
            case "ESSAY": {
                queryType = SearchType.ARTICLE;
                break;
            }
            case "QUESTION": {
                queryType = SearchType.QUESTION;
                break;
            }
            case "ANSWER": {
                queryType = SearchType.ANSWER;
                break;
            }
            case "JOB": {
                queryType = SearchType.JOB;
                break;
            }
            case "COMPANY": {
                queryType = SearchType.COMPANY;
                break;
            }
            case "REVIEW": {
                queryType = SearchType.EDITORIAL;
                break;
            }
            case "COMMENT": {
                queryType = SearchType.COMMENT;
                break;
            }
            case "USER": {
                queryType = SearchType.USER;
                break;
            }
            case "*":{
                queryType = SearchType.ALL;
                break;
            }
            default:
                throw new ClientException("cannot search target type, please try one of the following " + SearchType.getNameString());
        }
        return queryType;
    }
}
