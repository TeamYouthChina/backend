package com.youthchina.service.Xiaoyi;


import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.solr.SolrDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Xiaoyi Wang on 2019/3/30.
 */

public interface SearchService {

    List<ComEssay> essaysearch(String keyword);

    List<Question> questionsearch(String keyword);

    List<Company> companysearch(String keyword);

    List<HashMap<String,Object>> multiplesearch(String keyword);

    List<Job> jobsearch(String keyword);

    List<Comment> commentsearch(String keyword);

    List<Video> videosearch(String keyword);

    List<User> usersearch(String keyword);
}
