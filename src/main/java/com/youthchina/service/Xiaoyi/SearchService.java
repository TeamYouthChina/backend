package com.youthchina.service.Xiaoyi;


import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.solr.SolrDTO;

import java.util.List;

/**
 * Created by Xiaoyi Wang on 2019/3/30.
 */

public interface SearchService<T> {

    List<ComEssay> essaySearch(String http);

    List<Question> questionSearch(String http);

    List<Company> companySearch(String http);

    List<T> multipleSearch(String http);

    List<Job> jobSearch(String http);

    List<Comment> commmentSearch(String http);

    List<Video> videoSearch(String http);

    List<User> userSearch(String http);
}
