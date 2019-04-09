package com.youthchina.controller.tianjian;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.answer.SimpleAnswerResponseDTO;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AnswerServiceImpl;
import com.youthchina.service.jinhao.AttentionServiceImpl;
import com.youthchina.service.jinhao.CommentServiceImpl;
import com.youthchina.service.jinhao.EvaluateServiceImpl;
import com.youthchina.service.tianjian.FriendsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/friend")
public class FriendController {

    @Autowired
    FriendsServiceImpl friendsService;

    @PostMapping
    public ResponseEntity addFriend(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComFriendRelation comFriendRelation = new ComFriendRelation();
        comFriendRelation.setUserId(user.getId());
        comFriendRelation.setFriendId(id);
        friendsService.saveFriend(comFriendRelation);

        return ResponseEntity.ok(new Response(new StatusDTO(200, "success")));
    }
}
