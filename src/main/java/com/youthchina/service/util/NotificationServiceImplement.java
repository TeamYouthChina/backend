package com.youthchina.service.util;

import com.youthchina.dao.Hongsheng.NotificationMapper;
import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        Notification notification1=notificationMapper.get(notification.getId());
        notification1.setUser(userService.get(notification.getUser().getId()));
        return notification1;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public List<Notification> getAllNotifications(Integer user_id) throws NotFoundException {
        List<Notification> notifications=notificationMapper.getAllNotifications(user_id);
        return notifications;
    }

    @Override
    public void patchNotificationRead(Integer notification_id) throws NotFoundException {
        Integer id=notificationMapper.patchNotificationRead(notification_id);
        if(id==0){
            throw new NotFoundException(404,404,"do not find this notification");
        }
    }
}
