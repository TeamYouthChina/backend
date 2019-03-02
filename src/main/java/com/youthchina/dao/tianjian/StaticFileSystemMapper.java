package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.ComMediaDocument;
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
public interface StaticFileSystemMapper {
   int saveFileInfo(ComMediaDocument comMediaDocument);

   void setCloudStorageId(@Param("col")String col,@Param("id") String id,@Param("local_id")String local_id);

   List<ComMediaDocument> getFileSizeOfUserUploading(@Param("user_id") Integer user_id,@Param("startTime") Timestamp startTime,@Param("endTime")Timestamp endTime);

   ComMediaDocument getFileInfo(@Param("local_id")String local_id);
}
