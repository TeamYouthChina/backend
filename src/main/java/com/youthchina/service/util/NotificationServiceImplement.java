package com.youthchina.service.util;

import com.youthchina.dao.Hongsheng.NotificationMapper;
import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("NotificationService")
public class NotificationServiceImplement implements NotificationService{
    @Resource
    NotificationMapper notificationMapper;

    @Resource
    UserService userService;

    @Override
    public void ifNotificationExist(Integer id) throws NotFoundException {
        if(notificationMapper.checkIfNotificationExist(id) == null) {
            throw new NotFoundException(4040,404,"Not found this Noti");
        }
    }

    @Override
    public Notification get(Integer id) throws NotFoundException {
        Notification notification = notificationMapper.get(id);
        if (notification == null) {
            throw new NotFoundException(4040,404,"Not found this Noti");
        }
        notification.setUser(userService.get(notification.getUser().getId()));
        return notification;
    }

    @Override
    public Notification update(Notification notification) throws NotFoundException {
        ifNotificationExist(notification.getId());
        notificationMapper.update(notification);
        return get(notification.getId());
    }

    @Override
    public Notification add(Notification notification) throws NotFoundException {
        notificationMapper.add(notification);
        notification.setUser(userService.get(notification.getUser().getId()));
        return notification;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }
}
