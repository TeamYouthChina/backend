package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.ComMediaDocument;
import com.youthchina.domain.tianjian.StuResume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface PDFUploadMapper {
   StuResume get(Integer resumeId);

   void add(StuResume stuResume);

   void update(StuResume stuResume);

   void delete(Integer resumeId);
}
