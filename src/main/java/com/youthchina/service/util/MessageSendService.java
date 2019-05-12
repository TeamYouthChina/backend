package com.youthchina.service.util;

import com.youthchina.dto.security.VerifyEmailDTO;

public interface MessageSendService {
    void sendMessage(String message);

    void sendMessage(VerifyEmailDTO verifyEmailDTO);
}
