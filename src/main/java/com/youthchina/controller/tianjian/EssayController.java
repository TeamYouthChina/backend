package com.youthchina.controller.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.tianjian.ComAuthorEssayMap;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComEssayAttention;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.article.EssayDTO;
import com.youthchina.dto.community.article.EssayReplyDTO;
import com.youthchina.dto.community.article.RequestEssayDTO;
import com.youthchina.dto.community.article.RequestEssayReplyDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTO;
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
import java.util.Iterator;
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
        if (comEssay == null) {
            throw new NotFoundException(404, 404, "没有找到这个文章"); //TODO
        }
        ComAuthorEssayMap comAuthorEssayMap = essayServiceimpl.getEssayAuthor(id);
        EssayDTO essayDTO = new EssayDTO(comEssay);
        User user = userService.get(comAuthorEssayMap.getUser_id());

        if (comAuthorEssayMap.getRela_type() == 2) {
            Company company = companyCURDService.get(comAuthorEssayMap.getRela_id());
            essayDTO.setCompany(new CompanyResponseDTO(company));
        }
        essayDTO.setAuthor(new UserDTO(user));

        if (essayDTO != null)
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(400, "fail")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEssay(@PathVariable Integer id, @RequestBody RequestEssayDTO requestEssayDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(requestEssayDTO);
        comEssay.setEssay_id(id);
        if (requestEssayDTO.getCompany_id() != null) {
            ComAuthorEssayMap comAuthorEssayMap = new ComAuthorEssayMap();
            comAuthorEssayMap.setRela_type(2);
            comAuthorEssayMap.setRela_id(requestEssayDTO.getCompany_id());
            comAuthorEssayMap.setEssay_id(id);
            comAuthorEssayMap.setUser_id(user.getId());
            essayServiceimpl.updateEssayAuthor(comAuthorEssayMap);
        }
        essayServiceimpl.updateEssay(comEssay);

        EssayDTO essayDTO = new EssayDTO();
        essayDTO.setId(id);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        essayDTO.setModified_at(time);
        if (requestEssayDTO.getCompany_id() != null)
            essayDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(requestEssayDTO.getCompany_id())));
        essayDTO.setCreate_at(essayServiceimpl.getEssay(id).getEssay_pub_time());
        essayDTO.setTitle(comEssay.getEssay_title());
        essayDTO.setAuthor(new UserDTO(user));
        try {
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssay.getEssay_body(), RichTextDTO.class);
            essayDTO.setBody(richt);
        } catch (Exception e) {
            System.out.println("Exception");
        }

        return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(200, "success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEssay(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int i = essayServiceimpl.deleteEssay(id, time);
        if (i != 0)
            return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
        else
            return ResponseEntity.ok(new Response(new StatusDTO(403, "fail")));
    }

    @PutMapping("/{id}/attention")
    public ResponseEntity updateAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssayAttention comEssayAttention = new ComEssayAttention();
        comEssayAttention.setAtten_cancel(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssayAttention.setAtten_time(time);
        comEssayAttention.setUser_id(user.getId());
        essayServiceimpl.addFavoriteEssay(comEssayAttention, id);
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));
    }

    @DeleteMapping("/attentions/{id}")
    public ResponseEntity deleteAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        essayServiceimpl.deleteFavoriteEssay(id, user.getId());
        return ResponseEntity.ok(new Response(new StatusDTO(204, "success")));
    }

    @PostMapping
    public ResponseEntity addEssay(@RequestBody RequestEssayDTO requestEssayDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(requestEssayDTO);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEssay_pub_time(time);
        comEssay.setEssay_edit_time(time);
        int rela_type = 1;
        if (requestEssayDTO.getCompany_id() != null) {
            rela_type = 2;
        }
        List<Integer> lab_num = new ArrayList<Integer>();
        int essayId = essayServiceimpl.addEssay(comEssay, lab_num, user.getId(), rela_type, requestEssayDTO.getCompany_id());
        EssayDTO essayDTO = new EssayDTO();
        essayDTO.setTitle(comEssay.getEssay_title());
        //essayDTO.setBody(comEssay.getEssay_body());
        essayDTO.setCreate_at(comEssay.getEssay_pub_time());
        if (requestEssayDTO.getCompany_id() != null) {
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(companyCURDService.get(requestEssayDTO.getCompany_id()));
            essayDTO.setCompany(companyResponseDTO);
        }
        essayDTO.setModified_at(comEssay.getEssay_edit_time());
        essayDTO.setId(essayId);
        User useressay = userService.get(user.getId());
        essayDTO.setAuthor(new UserDTO(useressay));
        essayDTO.setIs_anonymous(requestEssayDTO.isIs_anonymous());
        try {
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssay.getEssay_body(), RichTextDTO.class);
            essayDTO.setBody(richt);
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(200, "success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addEssayComments(@PathVariable Integer id, @RequestBody RequestEssayReplyDTO requestEssayReplyDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssayReply comEssayReply = new ComEssayReply(requestEssayReplyDTO);
        comEssayReply.setIs_delete(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssayReply.setReply_pub_time(time);
        comEssayReply.setUser_id(user.getId());
        comEssayReply.setReply_edit_time(time);
        essayServiceimpl.addReply(comEssayReply, id, 0);
        return ResponseEntity.ok(new Response(new StatusDTO(201, "success")));

    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getEssayComments(@PathVariable Integer id) {
        List<ComEssayReply> comEssayReplies = essayServiceimpl.getReply(id);
        List<EssayReplyDTO> essayReplyDTOS = new ArrayList<>();
        if (comEssayReplies != null) {
            Iterator it = comEssayReplies.iterator();
            while (it.hasNext()) {
                ComEssayReply comEssayReply = (ComEssayReply) it.next();
                EssayReplyDTO essayReplyDTO = new EssayReplyDTO(comEssayReply);
                essayReplyDTO.setCreator(new UserDTO(userService.get(comEssayReply.getUser_id())));
                essayReplyDTOS.add(essayReplyDTO);
            }
        }
        ListResponse listResponse = new ListResponse(essayReplyDTOS, "comments");
        return ResponseEntity.ok(listResponse);

    }
}
