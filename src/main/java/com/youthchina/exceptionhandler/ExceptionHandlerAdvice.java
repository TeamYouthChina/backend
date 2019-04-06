package com.youthchina.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.youthchina.dto.Response;
import com.youthchina.exception.zhongyang.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by zhongyangwu on 2/10/19.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleException(BaseException exception) throws JsonProcessingException {
        Response response = new Response();
        response.setContent(null);
        response.setStatus(exception.getStatus());
        return ResponseEntity
                .status(exception.statusCode)
                .body(response);
    }
}