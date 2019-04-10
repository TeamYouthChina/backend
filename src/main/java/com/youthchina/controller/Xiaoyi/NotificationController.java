package com.youthchina.controller.Xiaoyi;

import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.notification.NotificationDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Hongsheng.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("${web.url.prefix}/notifications")
public class NotificationController {

    @Resource
    NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity getNotification(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(new Response(this.notificationService.get(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNotification(@PathVariable Integer id, @RequestBody NotificationDTO notificationDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Notification notification = new Notification(notificationDTO);
        notification.setUser(user);
        Notification returnNotification = notificationService.update(notification);
        NotificationDTO notificationDTO1 = new NotificationDTO(returnNotification);
        return ResponseEntity.ok(new Response(notificationDTO1));
    }

    @PostMapping("/{id}")
    public ResponseEntity addNotification(@PathVariable Integer id, @RequestBody NotificationDTO notificationDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Notification notification = new Notification(notificationDTO);
        notification.setUser(user);
        Notification returnNotification = notificationService.add(notification);
        NotificationDTO retrunnotificationDTO = new NotificationDTO(returnNotification);
        if(retrunnotificationDTO.getId() != null){
            return ResponseEntity.ok(new Response(retrunnotificationDTO));
        }else{
            return ResponseEntity.ok(new Response(new StatusDTO(403, "add failed")));
        }
    }
}
