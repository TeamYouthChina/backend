package com.youthchina.service.Xiaoyi;
import java.io.IOException;
import java.util.List;

import com.youthchina.dto.solr.SolrDTO;
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

public class SearchServiceImplement {

    private final static String SOLR_URL = "http://localhost:8983/solr/";

    public List<SolrDTO> usersearch(String sql) throws Exception {

        HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL + "youthchinacore/").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        SolrQuery query = new SolrQuery();
        //下面设置solr查询参数
        query.set("q", "*:*");// 参数q  查询所有
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
        //query.setStart(0);
        //query.setRows(10);//每一页多少值

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
        //for (SolrDocument doc : solrDocumentList) {
        //    System.out.println("id:" + doc.get("id") + "   name:" + doc.get("name") + "    gender:" + doc.get("gender"));
            //System.out.println(doc);
        //}
        //得到实体对象

        List<SolrDTO> tmpLists = response.getBeans(SolrDTO.class);
        if (tmpLists != null && tmpLists.size() > 0) {
            System.out.println("通过文档集合获取查询的结果");
            for (SolrDTO per : tmpLists) {
                System.out.println("id:" + per.getId());
            }
        }
        return tmpLists;
    }

}
