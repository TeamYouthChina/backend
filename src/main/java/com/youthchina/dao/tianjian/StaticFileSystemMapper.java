package com.youthchina.dao.tianjian;

import com.youthchina.domain.tianjian.*;
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
}
