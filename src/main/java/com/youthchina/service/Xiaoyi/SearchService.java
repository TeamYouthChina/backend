package com.youthchina.service.Xiaoyi;

import com.youthchina.domain.jinhao.Answer;
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

    List<ComEssay> essaySearch(String keyword) throws Exception;

    List<Question> questionSearch(String keyword) throws Exception;

    List<Company> companySearch(String keyword) throws Exception;

    List<SolrDTO> multipleSearch(String keyword) throws Exception;

    List<Job> jobSearch(String keyword) throws Exception;

    List<Comment> commentSearch(String keyword) throws Exception;

    List<Video> videoSearch(String keyword) throws Exception;

    List<Answer> answerSearch(String keyword) throws Exception;

    List<User> userSearch(String keyword) throws Exception;
}
