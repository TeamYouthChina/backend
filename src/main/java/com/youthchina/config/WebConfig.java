package com.youthchina.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.util.zhongyang.DTOtoDomainArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by zhongyangwu on 3/28/19.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new DTOtoDomainArgumentResolver(new ObjectMapper()));
    }
}
