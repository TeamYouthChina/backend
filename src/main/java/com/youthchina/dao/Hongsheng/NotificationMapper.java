package com.youthchina.dao.Hongsheng;

import com.youthchina.domain.Hongsheng.Notification;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NotificationMapper {
    Notification get(Integer id);
    void add(Notification notification);
    void update(Notification notification);
    Integer checkIfNotificationExist(Integer id);
}
