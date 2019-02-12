package com.youthchina.exception.zhongyang;

import com.youthchina.dto.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhongyangwu on 2/10/19.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientException extends BaseException {
    public ClientException() {
        this.status = new StatusDTO(4000, "Client error");
        this.statusCode = 400;
    }

    public ClientException(String message) {
        this();
        this.setMessage(message);
    }
}
