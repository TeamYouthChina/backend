package com.youthchina.util.zhongyang;

import com.youthchina.domain.zhongyang.PageRequest;
import com.youthchina.exception.zhongyang.ClientException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * Created by zhongyangwu on 4/4/19.
 */
public class PageRequestArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(PageRequest.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ServletRequest request = (ServletRequest) webRequest.getNativeRequest();
        Map<String, String[]> queryMap = request.getParameterMap();
        PageRequest pageRequest = new PageRequest();
        if (queryMap.containsKey("limit")) {
            if (queryMap.get("limit").length != 1) {
                throw new ClientException("limit can only have 1 number");
            } else {
                pageRequest.setOffset(Integer.valueOf(queryMap.get("limit")[0]));
            }
        } else {
            return null;
        }


        if (queryMap.containsKey("offset")) {
            if (queryMap.get("offset").length != 1) {
                throw new ClientException("offset can only have 1 number");
            } else {
                pageRequest.setOffset(Integer.valueOf(queryMap.get("offset")[0]));
            }
        } else if (queryMap.containsKey("page")) {
            if (queryMap.get("page").length != 1) {
                throw new ClientException("page can only have 1 number");
            } else {
                int pageNum = Integer.valueOf(queryMap.get("page")[0]);
                pageRequest.setOffset(pageNum * pageRequest.getLimit());
            }
        }
        return pageRequest;


    }
}
