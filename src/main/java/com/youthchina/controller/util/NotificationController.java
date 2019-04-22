package com.youthchina.controller.util;

import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.applicant.WorkResponseDTO;
import com.youthchina.dto.notification.NotificationDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.util.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/notifications")
public class NotificationController {

    @Resource
    NotificationService notificationService;

    @GetMapping("/{user_id}")
    public ResponseEntity getNotification(@PathVariable Integer user_id, PageRequest pageRequest) throws NotFoundException {
        List<Notification> notifications=notificationService.getAllNotifications(user_id);
        List<NotificationDTO> dtos=new ArrayList<>();
        if(notifications!=null&&notifications.size()!=0){
            for(Notification notification:notifications) {
                NotificationDTO notificationDTOS = new NotificationDTO(notification);
                dtos.add(notificationDTOS);
            }

        }
        List<NotificationDTO> result=dtos.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,dtos.size()));
        ListResponse listResponse = new ListResponse(pageRequest, notifications.size(), result);
        return ResponseEntity.ok(listResponse);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity updateNotification(@PathVariable Integer id, @RequestBody NotificationDTO notificationDTO, @AuthenticationPrincipal User user) throws NotFoundException {
//        Notification notification = new Notification(notificationDTO);
//        notification.setId(id);
//        notification.setUser(user);
//        Notification returnNotification = notificationService.update(notification);
//        NotificationDTO notificationDTO1 = new NotificationDTO(returnNotification);
//        return ResponseEntity.ok(new Response(notificationDTO1));
//    }
//
//    @PostMapping("/")
//    public ResponseEntity addNotification(@RequestBody NotificationDTO notificationDTO, @AuthenticationPrincipal User user) throws NotFoundException {
//        Notification notification = new Notification(notificationDTO);
//        notification.setUser(user);
//        Notification returnNotification = notificationService.add(notification);
//        NotificationDTO retrunNotificationDTO = new NotificationDTO(returnNotification);
//        if(retrunNotificationDTO.getId() != null){
//            return ResponseEntity.ok(new Response(retrunNotificationDTO));
//        }else{
//            return ResponseEntity.ok(new Response(new StatusDTO(403, "add failed")));
//        }
//    }
}
