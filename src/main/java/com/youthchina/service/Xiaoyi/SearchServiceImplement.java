package com.youthchina.service.Xiaoyi;

import java.util.*;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.solr.SolrDTO;
import com.youthchina.service.jinhao.CommentService;
import com.youthchina.service.jinhao.QuestionService;
import com.youthchina.service.jinhao.VideoService;
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

/**
 * Created by Xiaoyi Wang on 2019/3/31.
 */

@Service
public class SearchServiceImplement implements SearchService{
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

    @Override
    public List<User> usersearch(String keyword) throws Exception {
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

    @Override
    public List<ComEssay> essaysearch(String keyword) throws Exception {
        List<Integer> essayidlist = new ArrayList<>();
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "essay_name:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            essayidlist.add(Integer.parseInt(doc.get("essay_id").toString()));
        }

        query.set("q", "essay_abbre:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            essayidlist.add(Integer.parseInt(doc.get("essay_id").toString()));
        }

        query.set("q", "essay_body:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            essayidlist.add(Integer.parseInt(doc.get("essay_id").toString()));
        }

        List<ComEssay> essaylist = essayService.get(essayidlist);
        return essaylist;
    }

    @Override
    public List<Question> questionsearch(String keyword) throws Exception {
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "ques_title:"+keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> quesidlist = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            quesidlist.add(Integer.parseInt(doc.get("ques_id").toString()));
        }

        query.set("q", "ques_abbre:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            quesidlist.add(Integer.parseInt(doc.get("ques_id").toString()));
        }

        query.set("q", "ques_body:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            quesidlist.add(Integer.parseInt(doc.get("ques_id").toString()));
        }

        List<Question> queslist = questionService.get(quesidlist);
        return queslist;
    }

    @Override
    public List<Job> jobsearch(String keyword) throws Exception {
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "job_name:"+keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> jobidlist = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            jobidlist.add(Integer.parseInt(doc.get("job_id").toString()));
        }

        query.set("q", "job_description:"+keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            jobidlist.add(Integer.parseInt(doc.get("job_id").toString()));
        }

        List<Job> joblist = jobService.get(jobidlist);
        return joblist;
    }

    @Override
    public List<Comment> commentsearch(String keyword) throws Exception {
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "comment_content:"+keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> comidlist = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            comidlist.add(Integer.parseInt(doc.get("comm_id").toString()));
        }
        List<Comment> comlist = commentService.get(comidlist);
        return comlist;
    }

    @Override
    public List<Video> videosearch(String keyword) throws Exception {
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "video_title:"+keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> videoidlist = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            videoidlist.add(Integer.parseInt(doc.get("video_id").toString()));
        }

        query.set("q", "video_name:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            videoidlist.add(Integer.parseInt(doc.get("video_id").toString()));
        }

        query.set("q", "video_description:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            videoidlist.add(Integer.parseInt(doc.get("video_id").toString()));
        }


        List<Video> videolist = videoService.get(videoidlist);
        return videolist;
    }

    @Override
    public List<Company> companysearch(String keyword) throws Exception {
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "company_name:"+keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        List<Integer> companyidlist = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            companyidlist.add(Integer.parseInt(doc.get("company_id").toString()));
        }

        query.set("q", "company_location:"+keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        response = solrServer.query(query);
        solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            companyidlist.add(Integer.parseInt(doc.get("company_id").toString()));
        }
        List<Company> companylist = companyCURDService.get(companyidlist);
        return companylist;
    }

    @Override
    public List<SolrDTO> multiplesearch(String keyword) throws Exception{
        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        query.set("q", "*:"+ keyword);// 参数q  查询所有
        query.setStart(0);
        query.setRows(10);//每一页多少值
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();

        List<SolrDTO> solrlist = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            //HashMap<String,Object> map = new HashMap<>();
            if(doc.get("user_id") != null){
                User user = userService.get(Integer.parseInt(doc.get("user_id").toString()));
                solrlist.add(new SolrDTO("User", user));
            }
            else if(doc.get("ques_id") != null){
                Question question = questionService.get(Integer.parseInt(doc.get("ques_id").toString()));
                //map.put("Ques", question);
                solrlist.add(new SolrDTO("Question", question));
            }
            else if(doc.get("essay_id") != null){
                ComEssay comEssay = essayService.get(Integer.parseInt(doc.get("essay_id").toString()));
                //map.put("Essay", comEssay);
                solrlist.add(new SolrDTO("Essay", comEssay));
            }
            else if(doc.get("video_id") != null){
                Video video = videoService.get(Integer.parseInt(doc.get("video_id").toString()));
                //map.put("Essay", video);
                solrlist.add(new SolrDTO("Video", video));
            }
            else if(doc.get("comment_id") != null){
                Comment comment = commentService.get(Integer.parseInt(doc.get("comment_id").toString()));
                //map.put("Comment", comment);
                solrlist.add(new SolrDTO("Comment", comment));
            }
            else if(doc.get("job_id") != null){
                Job job = jobService.get(Integer.parseInt(doc.get("job_id").toString()));
                //map.put("Job", job);
                solrlist.add(new SolrDTO("Job", job));
            }
            else if(doc.get("company_id") != null){
                Company company = companyCURDService.get(Integer.parseInt(doc.get("company_id").toString()));
                //map.put("Job", company);
                solrlist.add(new SolrDTO("Company", company));
            }
        }

        int size = solrlist.size();//随机打乱
        Random random = new Random();

        for(int i = 0; i < size; i++) {
            // 获取随机位置
            int randomPos = random.nextInt(size);

            // 当前元素与随机元素交换
            Collections.swap(solrlist, i, randomPos);
        }

        return solrlist;
    }
}
