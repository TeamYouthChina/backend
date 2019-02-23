package com.youthchina.controller.tianjian;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.tianjian.ComAuthorEssayMap;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.EssayDTO;
import com.youthchina.dto.community.EssayReplyDTO;
import com.youthchina.dto.community.RequestEssayDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.qingyang.CompanyCURDServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/articles")
public class EssayController {

    @Autowired
    EssayServiceImpl essayServiceimpl;

    @Autowired
    CompanyCURDServiceImpl companyCURDService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity getEssay(@PathVariable Integer id) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        if(comEssay == null) {
            throw new NotFoundException(404,404,"没有找到这个文章");
        }
        ComAuthorEssayMap comAuthorEssayMap = essayServiceimpl.getEssayAuthor(id);
        EssayDTO essayDTO = new EssayDTO(comEssay);
        User user = userService.get(comAuthorEssayMap.getUser_id());

        if(comAuthorEssayMap.getRela_type()==2){
            Company company = companyCURDService.get(comAuthorEssayMap.getRela_id());
            essayDTO.setCompany(company);
        }
        essayDTO.setUser(user);

        if (essayDTO!=null)
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(400,"fail")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEssay(@PathVariable Integer id, @RequestBody RequestEssayDTO requestEssayDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(requestEssayDTO);
        comEssay.setEssay_id(id);
        if(requestEssayDTO.getCompany_id()!=null){
            ComAuthorEssayMap comAuthorEssayMap = new ComAuthorEssayMap();
            comAuthorEssayMap.setRela_type(2);
            comAuthorEssayMap.setRela_id(requestEssayDTO.getCompany_id());
            comAuthorEssayMap.setEssay_id(id);
            comAuthorEssayMap.setUser_id(user.getId());
            essayServiceimpl.updateEssayAuthor(comAuthorEssayMap);
        }
        int i = essayServiceimpl.updateEssay(comEssay);
        EssayDTO essayDTO = new EssayDTO();
        essayDTO.setId(id);
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        essayDTO.setModified_at(time);
        if(requestEssayDTO.getCompany_id()!=null)
           essayDTO.setCompany(companyCURDService.get(requestEssayDTO.getCompany_id()));
        essayDTO.setCreat_at(essayServiceimpl.getEssay(id).getEssay_pub_time());
        essayDTO.setBody(comEssay.getEssay_body());
        essayDTO.setTitle(comEssay.getEssay_title());
        essayDTO.setUser(user);

        if (i!=0)
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(400,"fail")));
    }


    @PostMapping
    public ResponseEntity addEssay(@RequestBody RequestEssayDTO requestEssayDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(requestEssayDTO);
        comEssay.setEssay_abbre("abbre");
        Timestamp time = new Timestamp( System.currentTimeMillis());
        comEssay.setEssay_pub_time(time);
        int rela_type = 1;
        if(requestEssayDTO.getCompany_id()!=null){
            rela_type = 2;
        }
        List<Integer> lab_num = new ArrayList<Integer>();
        int i = essayServiceimpl.addEssay(comEssay,lab_num,user.getId(),rela_type,requestEssayDTO.getCompany_id());
        if(i!=0)
            return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
        else
            return  ResponseEntity.ok(new Response( new StatusDTO(400,"fail")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addEssayComments(@PathVariable Integer id, @RequestBody EssayReplyDTO essayReplyDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssayReply comEssayReply = new ComEssayReply(essayReplyDTO);
        comEssayReply.setIs_delete(0);
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        comEssayReply.setReply_pub_time(time);
        comEssayReply.setUser_id(user.getId());
        comEssayReply.setReply_edit_time(time);
        int i = essayServiceimpl.addReply(comEssayReply,id,0);
        if(i!=0)
            return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
        else
            return  ResponseEntity.ok(new Response( new StatusDTO(400,"fail")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEssay(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        int i = essayServiceimpl.deleteEssay(id,time);
        if(i!=0)
            return ResponseEntity.ok(new Response(new StatusDTO(204,"success")));
        else
            return  ResponseEntity.ok(new Response( new StatusDTO(403,"fail")));
    }
}