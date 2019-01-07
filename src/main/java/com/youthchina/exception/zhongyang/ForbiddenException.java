package com.youthchina.exception.zhongyang;

import com.youthchina.dto.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhongyangwu on 1/2/19.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends BaseException {
    public ForbiddenException() {
        this.status = new StatusDTO(4030, "Cannot access");
        this.statusCode = 403;
    }
}
