package com.youthchina.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.exception.BaseException;
import com.youthchina.exception.zhongyang.exception.InternalStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
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

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity handleSqlException(BadSqlGrammarException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.status(new StatusDTO(InternalStatusCode.ACCESS_DENY)));
    }
}