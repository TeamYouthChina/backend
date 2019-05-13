package com.youthchina.service.util;

import com.youthchina.dto.application.EmailSendingDTO;
import com.youthchina.dto.application.JobApplyDTO;
import com.youthchina.dto.security.VerifyEmailDTO;

public interface MessageSendService {
    void sendMessage(EmailSendingDTO emailSendingDTO);

    void sendMessage(VerifyEmailDTO verifyEmailDTO);
}
