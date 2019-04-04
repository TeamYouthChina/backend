package com.youthchina.service.Xiaoyi;


import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.solr.SolrDTO;

import java.util.List;

/**
 * Created by Xiaoyi Wang on 2019/3/30.
 */

public interface SearchService<T> {

    List<ComEssay> essaysearch(String http);

    List<Question> questionsearch(String http);

    List<Company> companysearch(String http);

    List<T> multiplesearch(String http);

    List<Job> jobsearch(String http);

    List<Comment> commmentsearch(String http);

    List<Video> videosearch(String http);

    List<SolrDTO> usersearch(String http);
}
