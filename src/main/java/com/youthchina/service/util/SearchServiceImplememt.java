package com.youthchina.service.util;

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
import com.youthchina.exception.zhongyang.exception.ClientException;
import com.youthchina.service.application.CompanyCURDService;
import com.youthchina.service.application.JobService;
import com.youthchina.service.community.*;
import com.youthchina.service.user.UserService;
import com.youthchina.util.dictionary.SearchType;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        SearchResult searchResult;
        int count = 0;
        switch (type) {
            case SearchType.ARTICLE: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<ComEssay> essays = essaySearch(title, body, startIndex, endIndex);
                for (ComEssay i : essays) {
                    SearchResultItem item = new SearchResultItem(i, "article");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.QUESTION: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Question> questions = questionSearch(title, body, startIndex, endIndex);
                for (Question i : questions) {
                    SearchResultItem item = new SearchResultItem(i, "question");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.ANSWER: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Answer> answers = answerSearch(title, body, startIndex, endIndex);
                for (Answer i : answers) {
                    SearchResultItem item = new SearchResultItem(i, "answer");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.JOB: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Job> jobs = jobSearch(title, body, startIndex, endIndex);
                for (Job i : jobs) {
                    SearchResultItem item = new SearchResultItem(i, "job");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.COMPANY: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Company> companies = companySearch(title, body, startIndex, endIndex);
                for (Company i : companies) {
                    SearchResultItem item = new SearchResultItem(i, "company");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.EDITORIAL: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<BriefReview> briefReviews = briefReviewSearch(title, body, startIndex, endIndex);
                for (BriefReview i : briefReviews) {
                    SearchResultItem item = new SearchResultItem(i, "editorial");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.COMMENT: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Comment> comments = commentSearch(title, body, startIndex, endIndex);
                for (Comment i : comments) {
                    SearchResultItem item = new SearchResultItem(i, "comment");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.USER: {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<User> users = userSearch(title, body, startIndex, endIndex);
                for (User i : users) {
                    SearchResultItem item = new SearchResultItem(i, "user");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            case SearchType.ALL: {
                List<SearchResultItem> searchList = new ArrayList<>();

                List<ComEssay> essays = essaySearch(title, body, startIndex, endIndex);
                for (ComEssay i : essays) {
                    SearchResultItem item = new SearchResultItem(i, "article");
                    searchList.add(item);
                    count++;
                }

                List<Question> questions = questionSearch(title, body, startIndex, endIndex);
                for (Question i : questions) {
                    SearchResultItem item = new SearchResultItem(i, "question");
                    searchList.add(item);
                    count++;
                }

                List<Answer> answers = answerSearch(title, body, startIndex, endIndex);
                for (Answer i : answers) {
                    SearchResultItem item = new SearchResultItem(i, "answer");
                    searchList.add(item);
                    count++;
                }

                List<Job> jobs = jobSearch(title, body, startIndex, endIndex);
                for (Job i : jobs) {
                    SearchResultItem item = new SearchResultItem(i, "job");
                    searchList.add(item);
                    count++;
                }

                List<Company> companies = companySearch(title, body, startIndex, endIndex);
                for (Company i : companies) {
                    SearchResultItem item = new SearchResultItem(i, "company");
                    searchList.add(item);
                    count++;
                }

                List<BriefReview> briefReviews = briefReviewSearch(title, body, startIndex, endIndex);
                for (BriefReview i : briefReviews) {
                    SearchResultItem item = new SearchResultItem(i, "editorial");
                    searchList.add(item);
                    count++;
                }

                List<Comment> comments = commentSearch(title, body, startIndex, endIndex);
                for (Comment i : comments) {
                    SearchResultItem item = new SearchResultItem(i, "comment");
                    searchList.add(item);
                    count++;
                }

                List<User> users = userSearch(title, body, startIndex, endIndex);
                for (User i : users) {
                    SearchResultItem item = new SearchResultItem(i, "user");
                    searchList.add(item);
                    count++;
                }


                int size = searchList.size();//随机打乱
                Random random = new Random();
                for (int i = 0; i < size; i++) {
                    // 获取随机位置
                    int randomPos = random.nextInt(size);

                    // 当前元素与随机元素交换
                    Collections.swap(searchList, i, randomPos);
                }

                searchResult = new SearchResult(searchList, count);
                return searchResult;
            }
            default:
                throw new ClientException("cannot search target type, please try one of the following " + SearchType.getNameString());
        }

    }


    public List<ComEssay> essaySearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> essayIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (title != null) {
            query.set("q", "title:" + title);
            query.set("fq", "type:ESSAY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                essayIdList.add(Integer.parseInt(sp[1]));
            }
        }


        if (body != null) {
            query.set("q", "body:" + body);
            query.set("fq", "type:ESSAY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                essayIdList.add(Integer.parseInt(sp[1]));
            }
        }

        List<ComEssay> essayList = essayService.get(essayIdList);
        return essayList;
    }


    public List<Question> questionSearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> quesIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (title != null) {
            query.set("q", "title:" + title);
            query.set("fq", "type:QUESTION");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {

                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                quesIdList.add(Integer.parseInt(sp[1]));

            }
        }

        if (body != null) {
            query.set("q", "body:" + body);
            query.set("fq", "type:QUESTION");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                quesIdList.add(Integer.parseInt(sp[1]));
            }
        }

        List<Question> quesList = questionService.get(quesIdList);
        return quesList;
    }

    public List<Answer> answerSearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> answerIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (body != null) {
            query.set("q", "body:" + body);
            query.set("fq", "type:ANSWER");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                answerIdList.add(Integer.parseInt(sp[1]));
            }
        }


        List<Answer> answerList = answerService.get(answerIdList);
        return answerList;
    }

    public List<Job> jobSearch(String name, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> jobIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (name != null) {
            query.set("q", "name:" + name);
            query.set("fq", "type:JOB");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                jobIdList.add(Integer.parseInt(sp[1]));
            }
        }


        if (body != null) {
            query.set("q", "body:" + body);
            query.set("fq", "type:JOB");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                jobIdList.add(Integer.parseInt(sp[1]));
            }
        }

        List<Job> jobList = jobService.get(jobIdList);
        return jobList;
    }

    public List<Company> companySearch(String name, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> companyIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (name != null) {
            query.set("q", "name:" + name);
            query.set("fq", "type:COMPANY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                companyIdList.add(Integer.parseInt(sp[1]));
            }
        }


        if (body != null) {
            query.set("q", "body:" + body);
            query.set("fq", "type:COMPANY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                companyIdList.add(Integer.parseInt(sp[1]));
            }
        }
        List<Company> companyList = companyCURDService.get(companyIdList);
        return companyList;
    }

    public List<User> userSearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> userIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (title != null) {
            query.set("q", "title:" + title);
            query.set("fq", "type:USER");
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();

            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                userIdList.add(Integer.parseInt(sp[1]));
            }
        }
        List<User> userList = userService.get(userIdList);
        return userList;
    }

    public List<BriefReview> briefReviewSearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> briefReviewIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (body != null) {
            query.set("q", "body:" + body);
            query.set("fq", "type:REVIEW");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                briefReviewIdList.add(Integer.parseInt(sp[1]));
            }
        }

        List<BriefReview> briefReviewList = briefReviewService.get(briefReviewIdList);
        return briefReviewList;
    }

    public List<Comment> commentSearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> comIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (body != null) {
            query.set("q", "body:" + body);
            query.set("fq", "type:COMMENT");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                //System.out.println(id);
                String[] sp = id.split("_");
                comIdList.add(Integer.parseInt(sp[1]));
            }
        }
        List<Comment> comList = commentService.get(comIdList);
        return comList;
    }
}
