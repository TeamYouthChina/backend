package com.youthchina.exception.zhongyang.error;

/**
 * Created by zhongyangwu on 4/27/19.
 */
public class ConvertError extends BaseError {
    /**
     * @param message  error message
     * @param causedBy the caused class
     */
    public ConvertError(String message, Class causedBy) {
        super(message, causedBy);
    }
}
