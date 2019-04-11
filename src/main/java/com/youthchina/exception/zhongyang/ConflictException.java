package com.youthchina.exception.zhongyang;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youthchina.dto.HasStatus;
import com.youthchina.dto.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhongyangwu on 11/18/18.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ConflictException extends BaseException {
    public ConflictException(int code, int statusCode, String message) {
        super(code, statusCode, message);
    }
}
