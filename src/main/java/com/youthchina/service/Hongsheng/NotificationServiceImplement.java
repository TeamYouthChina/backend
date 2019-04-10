package com.youthchina.service.Hongsheng;

import com.youthchina.dao.Hongsheng.NotificationMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.zhongyang.UserService;

import javax.annotation.Resource;
import java.util.List;

public class NotificationServiceImplement implements NotificationService{
    @Resource
    NotificationMapper notificationMapper;

    @Resource
    UserService userService;

    @Override
    public void ifNotificationExist(Integer id) throws Exception {
        if(notificationMapper.checkIfNotificationExist(id) == null) {
            throw new NotFoundException(4040,404,"Not found this Notification");
        }
    }

    @Override
    public Notification get(Integer id) throws NotFoundException {
        Notification notification = notificationMapper.get(id);
        if (notification == null) {
            throw new NotFoundException(4040,404,"Not found this Notification");
        }
        notification.setUser(userService.get(notification.getUser().getId()));
        return notification;
    }

    @Override
    public List<Notification> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Notification update(Notification notification) throws NotFoundException {
        return null;
    }

    @Override
    public Notification add(Notification entity) throws NotFoundException {
        return null;
    }
}
