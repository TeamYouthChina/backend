package com.youthchina.service.Hongsheng;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service("SearchService")
public class SearchServiceImplememt implements SearchService {
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
            case "article": {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<ComEssay> essays = essaySearch(title, body, startIndex, endIndex);
                for (ComEssay i : essays) {
                    SearchResultItem item = new SearchResultItem(i,"article");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case "question": {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Question> questions = questionSearch(title, body, startIndex, endIndex);
                for (Question i : questions) {
                    SearchResultItem item = new SearchResultItem(i,"question");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case "answer" : {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Answer> answers = answerSearch(title, body, startIndex, endIndex);
                for (Answer i : answers) {
                    SearchResultItem item = new SearchResultItem(i,"answer");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case "job" : {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Job> jobs = jobSearch(title, body, startIndex, endIndex);
                for (Job i : jobs) {
                    SearchResultItem item = new SearchResultItem(i,"job");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case "company" : {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Company> companies = companySearch(title, body, startIndex, endIndex);
                for (Company i : companies) {
                    SearchResultItem item = new SearchResultItem(i,"company");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case "briefReview" : {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<BriefReview> briefReviews = briefReviewSearch(title, body, startIndex, endIndex);
                for (BriefReview i : briefReviews) {
                    SearchResultItem item = new SearchResultItem(i,"briefReview");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case "comment" : {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<Comment> comments = commentSearch(title, body, startIndex, endIndex);
                for (Comment i : comments) {
                    SearchResultItem item = new SearchResultItem(i,"comment");
                    searchList.add(item);
                    count++;
                }
                searchResult = new SearchResult(searchList,count);
                return searchResult;
            }
            case "all" : {
                List<SearchResultItem> searchList = new ArrayList<>();
                List<ComEssay> essays = essaySearch(title, body, startIndex, endIndex);
                for (ComEssay i : essays) {
                    SearchResultItem item = new SearchResultItem(i,"article");
                    searchList.add(item);
                    count++;
                }

                List<Question> questions = questionSearch(title, body, startIndex, endIndex);
                for (Question i : questions) {
                    SearchResultItem item = new SearchResultItem(i,"question");
                    searchList.add(item);
                    count++;
                }

                List<Answer> answers = answerSearch(title, body, startIndex, endIndex);
                for (Answer i : answers) {
                    SearchResultItem item = new SearchResultItem(i,"answer");
                    searchList.add(item);
                    count++;
                }

                List<Job> jobs = jobSearch(title, body, startIndex, endIndex);
                for (Job i : jobs) {
                    SearchResultItem item = new SearchResultItem(i,"job");
                    searchList.add(item);
                    count++;
                }

                List<Company> companies = companySearch(title, body, startIndex, endIndex);
                for (Company i : companies) {
                    SearchResultItem item = new SearchResultItem(i,"company");
                    searchList.add(item);
                    count++;
                }

                List<BriefReview> briefReviews = briefReviewSearch(title, body, startIndex, endIndex);
                for (BriefReview i : briefReviews) {
                    SearchResultItem item = new SearchResultItem(i,"briefReview");
                    searchList.add(item);
                    count++;
                }

                List<Comment> comments = commentSearch(title, body, startIndex, endIndex);
                for (Comment i : comments) {
                    SearchResultItem item = new SearchResultItem(i,"comment");
                    searchList.add(item);
                    count++;
                }


                int size = searchList.size();//随机打乱
                Random random = new Random();

                for(int i = 0; i < size; i++) {
                    // 获取随机位置
                    int randomPos = random.nextInt(size);

                    // 当前元素与随机元素交换
                    Collections.swap(searchList, i, randomPos);
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
            query.set("q", "title:"+title);
            query.set("fq","type:ESSAY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                essayIdList.add(Integer.parseInt(sp[1]));
            }
        }


        if (body != null) {
            query.set("q", "body:"+ body);
            query.set("fq","type:ESSAY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                essayIdList.add(Integer.parseInt(sp[1]));
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
            query.set("q", "title:"+title);
            query.set("fq","type:QUESTION");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {

                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                quesIdList.add(Integer.parseInt(sp[1]));

            }
        }

        if (body != null) {
            query.set("q", "body:"+ body);
            query.set("fq","type:QUESTION");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                quesIdList.add(Integer.parseInt(sp[1]));
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
            query.set("fq","type:ANSWER");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                answerIdList.add(Integer.parseInt(sp[1]));
            }
        }


        List<Answer> answerList = answerService.get(answerIdList);
        return answerList;
    }

    public List<Job> jobSearch(String name, String body,Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> jobIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (name != null) {
            query.set("q", "name:"+ name);
            query.set("fq","type:JOB");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                jobIdList.add(Integer.parseInt(sp[1]));
            }
        }


        if (body != null) {
            query.set("q", "body:"+ body);
            query.set("fq","type:JOB");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                jobIdList.add(Integer.parseInt(sp[1]));
            }
        }

        List<Job> jobList = jobService.get(jobIdList);
        return jobList;
    }

    public List<Company> companySearch(String name, String body,Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> companyIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (name != null) {
            query.set("q", "name:"+ name);
            query.set("fq","type:COMPANY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                companyIdList.add(Integer.parseInt(sp[1]));
            }
        }


        if (body != null) {
            query.set("q", "body:"+ body);
            query.set("fq","type:COMPANY");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                companyIdList.add(Integer.parseInt(sp[1]));
            }
        }
        List<Company> companyList = companyCURDService.get(companyIdList);
        return companyList;
    }
/*
    public List<User> userSearch(String keyword) throws Exception {
        List<Integer> userIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        //下面设置solr查询参数
        query.set("q", "user_name"+ keyword);// 参数q  查询所有
        //query.set("q","周星驰");//相关查询，比如某条数据某个字段含有周、星、驰三个字  将会查询出来 ，这个作用适用于联想查询

        //参数fq, 给query增加过滤查询条件
        //query.addFilterQuery("id:[0 TO 9]");//id为0-44

        //给query增加布尔过滤条件
        //query.addFilterQuery("description:演员");  //description字段中含有“演员”两字的数据

        //参数df,给query设置默认搜索域
        //query.set("df", "name");

        //参数sort,设置返回结果的排序规则
        //query.setSort("id",SolrQuery.ORDER.desc);

        //设置分页参数
        query.setStart(0);
        query.setRows(10);//每一页多少值

        //参数hl,设置高亮
        //query.setHighlight(true);
        //设置高亮的字段
        //query.addHighlightField("name");
        //设置高亮的样式
        //query.setHighlightSimplePre("<font color='red'>");
        //query.setHighlightSimplePost("</font>");

        //获取查询结
        QueryResponse response = solrServer.query(query);
        //两种结果获取：得到文档集合或者实体对象

        //查询得到文档的集合
        SolrDocumentList solrDocumentList = response.getResults();
        //System.out.println("通过文档集合获取查询的结果");
        //System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());
        //遍历列表
        List<Integer> useridlist = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            //System.out.println("id:" + doc.get("id") + "   name:" + doc.get("name") + "    gender:" + doc.get("gender"));
            //System.out.println(doc);
            useridlist.add(Integer.parseInt(doc.get("user_id").toString()));
        }

        query.set("q", "user_phone:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            useridlist.add(Integer.parseInt(doc.get("user_id").toString()));
        }

        List<User> userlist = userService.get(useridlist);
        return userlist;
    }
    */

    public List<BriefReview> briefReviewSearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> briefReviewIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (body != null) {
            query.set("q", "body:"+ body);
            query.set("fq","type:REVIEW");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                briefReviewIdList.add(Integer.parseInt(sp[1]));
            }
        }

        List<BriefReview> briefReviewList = briefReviewService.get(briefReviewIdList);
        return briefReviewList;
    }

    public List<Comment>  commentSearch(String title, String body, Integer startIndex, Integer endIndex) throws Exception {
        List<Integer> comIdList = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        if (body != null) {
            query.set("q", "body:"+ body);
            query.set("fq","type:COMMENT");
            // 参数q  查询所有
            query.setStart(startIndex);
            query.setRows(endIndex - startIndex + 1);//每一页多少值
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                String id = doc.get("id").toString();
                System.out.println(id);
                String[] sp = id.split("_");
                comIdList.add(Integer.parseInt(sp[1]));
            }
        }
        List<Comment> comList = commentService.get(comIdList);
        return comList;
    }
}
