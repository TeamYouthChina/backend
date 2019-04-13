package com.youthchina.controller.user;


import com.youthchina.domain.tianjian.ComFriendApply;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.friend.FriendApplicationResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.ConflictException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.FriendsServiceImpl;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/friends")
public class FriendController {

    @Autowired
    FriendsServiceImpl friendsService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/{id}/apply")
    public ResponseEntity addFriendApplication(@PathVariable Integer id, @AuthenticationPrincipal User user) throws ConflictException {
        ComFriendApply comFriendApply = new ComFriendApply();
        comFriendApply.setUserId(user.getId());
        comFriendApply.setFriendId(id);
        ComFriendApply comFriendApply1 = friendsService.addFriendApply(comFriendApply);
        FriendApplicationResponseDTO friendApplicationResponseDTO = new FriendApplicationResponseDTO(comFriendApply1);
        return ResponseEntity.ok(new Response(friendApplicationResponseDTO,new StatusDTO(201, "success")));
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
    public ResponseEntity getApplication(@PathVariable Integer id){
        ComFriendApply comFriendApply = friendsService.getFriendApplication(id);
        FriendApplicationResponseDTO friendApplicationResponseDTO = new FriendApplicationResponseDTO(comFriendApply);
        return ResponseEntity.ok(new Response(friendApplicationResponseDTO,new StatusDTO(200,"success")));
    }

    @PutMapping("applications/{reference_id}/approval")
    public ResponseEntity addApprovalApplication(@PathVariable Integer reference_id){
        ComFriendApply comFriendApply = friendsService.getFriendApplication(reference_id);
        comFriendApply.setFriApplyAccept(1);
        friendsService.changeApplicationStatus(comFriendApply);
        return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
    }

    @PutMapping("applications/{reference_id}/deny")
    public ResponseEntity addDenyApplication(@PathVariable Integer reference_id){
        ComFriendApply comFriendApply = friendsService.getFriendApplication(reference_id);
        comFriendApply.setFriApplyAccept(0);
        friendsService.changeApplicationStatus(comFriendApply);
        return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
    }

    @GetMapping
    public ResponseEntity getAllFriend(@AuthenticationPrincipal User user, PageRequest pageRequest){
        List<ComFriendRelation> comFriendRelations  = friendsService.getFriend(user.getId());
        List<UserDTO> userDTOS = new ArrayList<>();
        Iterator iterator = comFriendRelations.iterator();
        while (iterator.hasNext()){
            ComFriendRelation comFriendRelation = (ComFriendRelation)iterator.next();
            User user1 = userService.get(comFriendRelation.getFriendId());
            UserDTO userDTO = new UserDTO(user1);
            userDTOS.add(userDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, userDTOS.size(), userDTOS);
        return ResponseEntity.ok(new Response(listResponse,new StatusDTO(200,"success")));
    }

    @GetMapping("/applications")
    public ResponseEntity getUserAllApplication(@AuthenticationPrincipal User user,PageRequest pageRequest){
        List<ComFriendApply> comFriendApply = friendsService.getAllFriendApply(user.getId());
        List<FriendApplicationResponseDTO> friendApplicationResponseDTOS = new ArrayList<>();
        Iterator iterator = comFriendApply.iterator();
        while(iterator.hasNext()){
            ComFriendApply comFriendApply1 = (ComFriendApply) iterator.next();
            FriendApplicationResponseDTO friendApplicationResponseDTO = new FriendApplicationResponseDTO(comFriendApply1);
            friendApplicationResponseDTOS.add(friendApplicationResponseDTO);
        }
        ListResponse listResponse = new ListResponse(pageRequest, friendApplicationResponseDTOS.size(), friendApplicationResponseDTOS);

        return ResponseEntity.ok(new Response(listResponse,new StatusDTO(200,"success")));
    }
}
