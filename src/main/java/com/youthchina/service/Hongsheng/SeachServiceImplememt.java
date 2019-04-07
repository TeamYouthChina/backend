package com.youthchina.service.Hongsheng;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.domain.zhongyang.SearchResultItem;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.service.jinhao.*;
import com.youthchina.service.qingyang.CompanyCURDService;
import com.youthchina.service.qingyang.JobService;
import com.youthchina.service.tianjian.EssayService;
import com.youthchina.service.zhongyang.UserService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class SeachServiceImplememt implements SearchService {
    private final static String SOLR_URL = "http://localhost:8983/solr/";

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

    @Override
    public SearchResult search(String type, String title, String body, Integer startIndex, Integer endIndex) throws Exception{
        SearchResult searchResult;
        int count = 0;
        switch (type) {
            case("article"): {
                List<SearchResultItem> searchList= new ArrayList<>();
                List<ComEssay> essays = essaySearch(title, body, startIndex, endIndex);
                for (ComEssay i : essays) {
                    SearchResultItem item = new SearchResultItem(i,"article");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case("question"): {
                List<SearchResultItem> searchList= new ArrayList<>();
                List<Question> questions = questionSearch(title, body, startIndex, endIndex);
                for (Question i : questions) {
                    SearchResultItem item = new SearchResultItem(i,"question");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case("answer"): {
                List<SearchResultItem> searchList= new ArrayList<>();
                List<Question> questions = questionSearch(title, body, startIndex, endIndex);
                for (Question i : questions) {
                    SearchResultItem item = new SearchResultItem(i,"question");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            default:
                throw new BaseException(5000, 500, "no suitable converter found for search result");
        }

    }


    public List<ComEssay> essaySearch(String title, String body,Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> essayIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (title != null) {
            query.set("q", "title:"+ title);
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                essayIdList.add(Integer.parseInt(doc.get("id").toString()));
            }
        }


        if (body != null) {
            query.set("q", "body:"+ body);
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                essayIdList.add(Integer.parseInt(doc.get("id").toString()));
            }
        }

        List<ComEssay> essayList = essayService.get(essayIdList);
        return essayList;
    }


    public List<Question> questionSearch(String title, String body,Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> quesIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (title != null) {
            query.set("q", "title:"+ title);
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                quesIdList.add(Integer.parseInt(doc.get("id").toString()));
            }
        }


        if (body != null) {
            query.set("q", "body:"+ body);
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                quesIdList.add(Integer.parseInt(doc.get("id").toString()));
            }
        }

        List<Question> quesList = questionService.get(quesIdList);
        return quesList;
    }

    public List<Answer> answerSearch(String title, String body,Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> answerIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (body != null) {
            query.set("q", "body:"+ body);
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                answerIdList.add(Integer.parseInt(doc.get("id").toString()));
            }
        }


        List<Answer> answerList = answerService.get(answerIdList);
        return answerList;
    }
}
