package com.youthchina.exception.zhongyang;

/**
 * Created by zhongyangwu on 11/18/18.
 */
public class BaseException extends Throwable {
    public int code;
    public int statusCode;
    public String message;

    public BaseException(int code, int statusCode, String message) {
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
