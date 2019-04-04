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

public interface SearchService {

    List<ComEssay> essaysearch(String keyword) throws Exception;

    List<Question> questionsearch(String keyword) throws Exception;

    List<Company> companysearch(String keyword) throws Exception;

    List<SolrDTO> multiplesearch(String keyword) throws Exception;

    List<Job> jobsearch(String keyword) throws Exception;

    List<Comment> commentsearch(String keyword) throws Exception;

    List<Video> videosearch(String keyword) throws Exception;

    List<User> usersearch(String keyword) throws Exception;
}
