package com.youthchina.exception.zhongyang.exception;

import com.youthchina.dto.StatusDTO;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        this.status = new StatusDTO(4010, "Unauthorized");
        this.statusCode = 401;
    }
}
