package com.youthchina.controller.tianjian;


import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.friend.FriendResponseDTO;
import com.youthchina.exception.zhongyang.ConflictException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.FriendsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${web.url.prefix}/friends")
public class FriendController {

    @Autowired
    FriendsServiceImpl friendsService;

    @PostMapping("/{id}/apply")
    public ResponseEntity addFriendApplication(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ConflictException {
        ComFriendApply comFriendApply = new ComFriendApply();
        comFriendApply.setUserId(user.getId());
        comFriendApply.setFriendId(id);
        ComFriendApply comFriendApply1 = friendsService.addFriendApply(comFriendApply);
        FriendResponseDTO friendResponseDTO = new FriendResponseDTO(comFriendApply1);
        return ResponseEntity.ok(new Response(friendResponseDTO,new StatusDTO(201, "success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFriend(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComFriendRelation comFriendRelation = new ComFriendRelation();
        comFriendRelation.setUserId(user.getId());
        comFriendRelation.setFriendId(id);
        friendsService.deleteFriend(comFriendRelation);
        return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity getFriendApplication(@PathVariable Integer id){
        ComFriendApply comFriendApply = friendsService.getFriendApplication(id);
        FriendResponseDTO friendResponseDTO = new FriendResponseDTO(comFriendApply);
        return ResponseEntity.ok(new Response(friendResponseDTO,new StatusDTO(200,"success")));
    }

    @PutMapping("application/{reference_id}/approval")
    public ResponseEntity addApprovalApplication(@PathVariable Integer reference_id){
        ComFriendApply comFriendApply = friendsService.getFriendApplication(reference_id);
        comFriendApply.setFriApplyAccept(1);
        friendsService.changeApplicationStatus(comFriendApply);
        return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
    }

    @PutMapping("application/{reference_id}/deny")
    public ResponseEntity addDenyApplication(@PathVariable Integer reference_id){
        ComFriendApply comFriendApply = friendsService.getFriendApplication(reference_id);
        comFriendApply.setFriApplyAccept(0);
        friendsService.changeApplicationStatus(comFriendApply);
        return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
    }
}
