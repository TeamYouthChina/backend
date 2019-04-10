package com.youthchina.service.Hongsheng;

import com.youthchina.domain.Hongsheng.Notification;
import org.springframework.stereotype.Service;

@Service("NotificationService")
public class NotificationServiceImpl implements NotificationService{
    @Override
    Notification get(Integer id){

    }

    void add(Notification notification);
    void update(Notification notification);
    Integer checkIfNotificationExist(Integer id);
}
