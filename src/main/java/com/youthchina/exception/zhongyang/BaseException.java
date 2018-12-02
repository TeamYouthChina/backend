package com.youthchina.exception.zhongyang;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhongyangwu on 11/18/18.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends Throwable {
    public int code;
    public int statusCode;
    public String reason;

    public BaseException(int code, int statusCode, String message) {
        this.code = code;
        this.statusCode = statusCode;
        this.reason = message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", statusCode=" + statusCode +
                ", reason='" + reason + '\'' +
                '}';
    }
}
