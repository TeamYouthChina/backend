package com.youthchina.dao.Hongsheng;

import com.youthchina.domain.Hongsheng.Notification;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NotificationMapper {
    Notification get(Integer id);
    Integer add(Notification notification);
    Integer update(Notification notification);
    Integer checkIfNotificationExist(Integer id);
    List<Notification> getAllNotifications(Integer user_id);
    Integer patchNotificationRead(Integer id);
}
