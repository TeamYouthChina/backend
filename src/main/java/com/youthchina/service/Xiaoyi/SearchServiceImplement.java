package com.youthchina.service.Xiaoyi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.solr.SolrDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.CommentServiceImpl;
import com.youthchina.service.jinhao.QuestionServiceImpl;
import com.youthchina.service.jinhao.VideoServiceImpl;
import com.youthchina.service.qingyang.CompanyCURDServiceImpl;
import com.youthchina.service.qingyang.JobServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * Created by Xiaoyi Wang on 2019/3/31.
 */

public class SearchServiceImplement<T> {

    private final static String SOLR_URL = "http://localhost:8983/solr/";
    private UserServiceImpl userService;
    private EssayServiceImpl essayService;
    private QuestionServiceImpl questionService;
    private JobServiceImpl jobService;
    private CompanyCURDServiceImpl companyCURDService;
    private CommentServiceImpl commentService;
    private VideoServiceImpl videoService;

    public List<User> userSearch(String keyWord) throws Exception {

        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();

        SolrQuery query = new SolrQuery();
        //下面设置solr查询参数
        query.set("q", "user_id:"+keyWord);// 参数q  查询所有
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
        List<Integer> idList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {

            idList.add(Integer.valueOf(doc.get("user_id").toString()));
            //System.out.println(doc);
        }

        query.set("q", "user_name:"+keyWord);
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("user_id").toString()));

        }

        query.set("q", "user_phone:"+keyWord);
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("user_id").toString()));

        }





        //得到实体对象

        List<User> tmpLists = new ArrayList<>();
        tmpLists = userService.get(idList);

        return tmpLists;
    }

    List<ComEssay> essaySearch(String keyWord) throws Exception {
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "essay_id:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> idList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("essay_id").toString()));
        }

        query.set("q", "essay_title:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("essay_id").toString()));
        }

        query.set("q", "essay_abbre:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("essay_id").toString()));
        }

        List<ComEssay> tmpLists = new ArrayList<>();
        tmpLists = essayService.get(idList);

        return tmpLists;
    }

    List<Question> questionSearch(String keyWord) throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "question_id:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> idList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("question_id").toString()));
        }

        query.set("q", "question_title:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("question_id").toString()));
        }

        query.set("q", "question_abbre:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("question_id").toString()));
        }

        List<Question> tmpLists = new ArrayList<>();
        tmpLists = questionService.get(idList);

        return tmpLists;
    }

    List<Company> companySearch(String keyWord) throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "company_id:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> idList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("company_id").toString()));
        }

        query.set("q", "company_name:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("company_id").toString()));
        }

        query.set("q", "company_location:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("company_id").toString()));
        }

        List<Company> tmpLists = new ArrayList<>();
        tmpLists = companyCURDService.get(idList);

        return tmpLists;
    }

    List<HashMap<String,Object>> multipleSearch(String keyWord) throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();

        SolrQuery query = new SolrQuery();
        //下面设置solr查询参数
        query.set("q", "*:keyWord");// 参数q  查询所有
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


        //获取查询结
        QueryResponse response = solrServer.query(query);
        //两种结果获取：得到文档集合或者实体对象

        //查询得到文档的集合
        SolrDocumentList solrDocumentList = response.getResults();
        //System.out.println("通过文档集合获取查询的结果");
        //System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());
        //遍历列表
        List<HashMap<String,Object>> list = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            HashMap<String, Object> map = new HashMap<>();
            if(doc.get("user_id") != null) {
                int id = Integer.valueOf(doc.get("user_id").toString());
                User user = userService.get(id);
                map.put("user", user);
                list.add(map);
            }

            if(doc.get("essay_id") != null) {
                int id = Integer.valueOf(doc.get("essay_id").toString());
                ComEssay comEssay = essayService.get(id);
                map.put("essay", comEssay);
                list.add(map);
            }

            if(doc.get("question_id") != null) {
                int id = Integer.valueOf(doc.get("question_id").toString());
                Question question = questionService.get(id);
                map.put("question", question);
                list.add(map);
            }

            if(doc.get("company_id") != null) {
                int id = Integer.valueOf(doc.get("company_id").toString());
                Company company = companyCURDService.get(id);
                map.put("company", company);
                list.add(map);
            }

            if(doc.get("job_id") != null) {
                int id = Integer.valueOf(doc.get("job_id").toString());
                Job job = jobService.get(id);
                map.put("job", job);
                list.add(map);
            }

            if(doc.get("comment_id") != null) {
                int id = Integer.valueOf(doc.get("comment_id").toString());
                Comment comment = commentService.get(id);
                map.put("comment", comment);
                list.add(map);
            }

            if(doc.get("video_id") != null) {
                int id = Integer.valueOf(doc.get("video_id").toString());
                Video video = videoService.get(id);
                map.put("video", video);
                list.add(map);
            }

            //System.out.println(doc);
        }
        //得到实体对象



        return list;
    }

    List<Job> jobSearch(String keyWord) throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "job_id:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> idList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("job_id").toString()));
        }

        query.set("q", "job_title:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("job_id").toString()));
        }

        query.set("q", "job_abbre:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("job_id").toString()));
        }
        List<Job> tmpLists = new ArrayList<>();
        tmpLists = jobService.get(idList);

        return tmpLists;
    }

    List<Comment> commmentSearch(String keyWord) throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "comment_id:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> idList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("comment_id").toString()));
        }

        query.set("q", "comment_content:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("comment_id").toString()));
        }




        List<Comment> tmpLists = new ArrayList<>();
        tmpLists = commentService.get(idList);

        return tmpLists;
    }

    List<Video> videoSearch(String keyWord) throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "video_id:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> idList = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("job_id").toString()));
        }

        query.set("q", "job_title:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("job_id").toString()));
        }

        query.set("q", "job_abbre:"+keyWord);
        query.setStart(0);
        query.setRows(10);
        response = solrServer.query(query);
        solrDocumentList = response.getResults();

        for (SolrDocument doc : solrDocumentList) {
            idList.add(Integer.valueOf(doc.get("job_id").toString()));
        }
        //得到实体对象

        List<Video> tmpLists = new ArrayList<>();
        tmpLists = videoService.get(idList);

        return tmpLists;
    }



}
