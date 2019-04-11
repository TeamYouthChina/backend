package com.youthchina.service.Hongsheng;

import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

public interface NotificationService extends DomainCRUDService<Notification,Integer> {
    void ifNotificationExist(Integer id) throws NotFoundException;
}