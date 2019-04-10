package com.youthchina.service.Hongsheng;

import com.youthchina.domain.Hongsheng.Notification;

public interface NotificationService {
    Notification get(Integer id);
    void add(Notification notification);
    void update(Notification notification);
    Integer checkIfNotificationExist(Integer id);
}
