package com.youthchina.exception.zhongyang;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhongyangwu on 11/18/18.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotBelongException extends BaseException {
    public NotBelongException(int code, int statusCode, String message) {
        super(code, statusCode, message);
    }
}
