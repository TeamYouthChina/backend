package com.youthchina.service.util;

import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface NotificationService extends DomainCRUDService<Notification,Integer> {
    void ifNotificationExist(Integer id) throws NotFoundException;
    List<Notification> getAllNotifications(Integer user_id) throws NotFoundException;
}
