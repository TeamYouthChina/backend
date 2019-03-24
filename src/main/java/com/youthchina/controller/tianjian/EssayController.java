package com.youthchina.controller.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.tianjian.ComAuthorEssayMap;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComEssayAttention;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.article.EssayReplyResponseDTO;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.community.article.EssayReplyRequestDTO;
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
        if(comEssay == null) {
            throw new NotFoundException(404,404,"没有找到这个文章"); //TODO
        }
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO(comEssay);
        User user = userService.get(comEssay.getEssayId());

        if(comEssay.getRelaType()==2){
            Company company = companyCURDService.get(comEssay.getEssayId());
            essayResponseDTO.setCompany(new CompanyResponseDTO(company));
        }
        essayResponseDTO.setAuthor(new UserDTO(user));

        if (essayResponseDTO !=null)
            return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(400,"fail")));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEssay(@PathVariable Integer id, @RequestBody EssayRequestDTO essayRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayRequestDTO);
        comEssay.setEssayId(id);
        comEssay.setUserId(user.getId());
        if(essayRequestDTO.getCompany_id()!=null){
            comEssay.setRelaType(2);
            comEssay.setRelaId(essayRequestDTO.getCompany_id());
        }
        essayServiceimpl.updateEssay(comEssay);

        EssayResponseDTO essayResponseDTO = new EssayResponseDTO();
        essayResponseDTO.setId(id);
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        essayResponseDTO.setModified_at(time);
        if(essayRequestDTO.getCompany_id()!=null)
            essayResponseDTO.setCompany(new CompanyResponseDTO(companyCURDService.get(essayRequestDTO.getCompany_id())));
        essayResponseDTO.setCreate_at(essayServiceimpl.getEssay(id).getEssayPubTime());
        essayResponseDTO.setTitle(comEssay.getEssayTitle());
        essayResponseDTO.setAuthor(new UserDTO(user));
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssay.getEssayBody().getJson_content(), RichTextDTO.class);
            essayResponseDTO.setBody(richt);
        }catch (Exception e){
            System.out.println("Exception");
        }

        return ResponseEntity.ok(new Response(essayResponseDTO, new StatusDTO(200,"success")));
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

    @PutMapping("/{id}/attention")
    public ResponseEntity updateAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssayAttention comEssayAttention = new ComEssayAttention();
        comEssayAttention.setAtten_cancel(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssayAttention.setAtten_time(time);
        comEssayAttention.setUser_id(user.getId());
        essayServiceimpl.addFavoriteEssay(comEssayAttention,id);
        return ResponseEntity.ok(new Response( new StatusDTO(201,"success")));
    }

    @DeleteMapping("/attentions/{id}")
    public ResponseEntity deleteAttention(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotFoundException {
        System.out.println("start here");
        essayServiceimpl.deleteFavoriteEssay(id,user.getId());
        return ResponseEntity.ok(new Response( new StatusDTO(204,"success")));
    }

    @PostMapping
    public ResponseEntity addEssay(@RequestBody EssayRequestDTO essayRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayRequestDTO);
        Timestamp time = new Timestamp( System.currentTimeMillis());
        comEssay.setEssayPubTime(time);
        comEssay.setEssayEditTime(time);
        comEssay.setRelaType(1);
        comEssay.setUserId(user.getId());
        if(essayRequestDTO.getCompany_id()!=null){
            comEssay.setRelaType(2);
            comEssay.setRelaId(essayRequestDTO.getCompany_id());
        }
        List<Integer> lab_num = new ArrayList<Integer>();
        int essayId = essayServiceimpl.addEssay(comEssay,lab_num);
        EssayResponseDTO essayResponseDTO = new EssayResponseDTO();
        essayResponseDTO.setTitle(comEssay.getEssayTitle());
        //essayResponseDTO.setBody(comEssay.getEssay_body());
        essayResponseDTO.setCreate_at(comEssay.getEssayPubTime());
        if(essayRequestDTO.getCompany_id()!=null){
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(companyCURDService.get(essayRequestDTO.getCompany_id()));
            essayResponseDTO.setCompany( companyResponseDTO);
        }
        essayResponseDTO.setModified_at(comEssay.getEssayEditTime());
        essayResponseDTO.setId(essayId);
        User useressay = userService.get(user.getId());
        essayResponseDTO.setAuthor(new UserDTO(useressay));
        essayResponseDTO.setIs_anonymous(essayRequestDTO.isIs_anonymous());
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssay.getEssayBody().getJson_content(), RichTextDTO.class);
            essayResponseDTO.setBody(richt);
        }catch (Exception e){
            System.out.println("Exception");
        }
        return ResponseEntity.ok(new Response(essayResponseDTO,new StatusDTO(200,"success")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addEssayComments(@PathVariable Integer id, @RequestBody EssayReplyRequestDTO essayReplyRequestDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        Comment comment = new Comment();
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        comment.setPubTime(time);
        comment.setUser(user);
        comment.setEditTime(time);
        comment.setTargetType(2);
        comment.setTargetId(id);
        comment.setIsAnony((essayReplyRequestDTO.isAnonymous())? 1:0);
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(essayReplyRequestDTO.getBody());
            comment.setContent(requestJson);
        } catch (Exception e) {
            System.out.println("Exception");
        }
         essayServiceimpl.addComent(comment);
        return ResponseEntity.ok(new Response(new StatusDTO(201,"success")));

    }

    @GetMapping("/{id}/comments")
    public ResponseEntity getEssayComments(@PathVariable Integer id) {
        List<ComEssayReply> comEssayReplies = essayServiceimpl.getReply(id);
        List<EssayReplyResponseDTO> essayReplyResponseDTOS = new ArrayList<>();
        if(comEssayReplies!=null){
            Iterator it = comEssayReplies.iterator();
            while(it.hasNext()){
                ComEssayReply comEssayReply = (ComEssayReply) it.next();
                EssayReplyResponseDTO essayReplyResponseDTO = new EssayReplyResponseDTO(comEssayReply);
                essayReplyResponseDTO.setCreator(new UserDTO(userService.get(comEssayReply.getUser_id())));
                essayReplyResponseDTOS.add(essayReplyResponseDTO);
            }
        }
        ListResponse listResponse = new ListResponse(essayReplyResponseDTOS,"comments");
            return ResponseEntity.ok(listResponse);

    }
}
