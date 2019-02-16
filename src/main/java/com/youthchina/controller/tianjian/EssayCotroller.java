package com.youthchina.controller.tianjian;

import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.tianjian.ComAuthorEssayMap;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.EssayDTO;
import com.youthchina.dto.community.EssayReplyDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.qingyang.CompanyCURDServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${web.url.prefix}/articles/**")
public class EssayCotroller {

    @Autowired
    EssayServiceImpl essayServiceimpl;

    @Autowired
    CompanyCURDServiceImpl companyCURDService;

    @GetMapping("/{id}")
    public ResponseEntity getEssay(@PathVariable Integer id) throws NotFoundException {
        ComEssay comEssay = essayServiceimpl.getEssay(id);
        ComAuthorEssayMap comAuthorEssayMap = essayServiceimpl.getEssayAuthor(id);
        EssayDTO essayDTO = new EssayDTO(comEssay);
        User user = new User();
        user.setId(comAuthorEssayMap.getUser_id());

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
    public ResponseEntity updateEssay(@PathVariable Integer id, @RequestBody EssayDTO essayDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayDTO);
        if(essayDTO.getCompany()!=null){
            ComAuthorEssayMap comAuthorEssayMap = new ComAuthorEssayMap();
            comAuthorEssayMap.setRela_type(2);
            comAuthorEssayMap.setRela_id(essayDTO.getCompany().getCompanyId());
            comAuthorEssayMap.setEssay_id(id);
            comAuthorEssayMap.setUser_id(user.getId());
            essayServiceimpl.updateEssayAuthor(comAuthorEssayMap);
        }
        int i = essayServiceimpl.updateEssay(comEssay);
        essayDTO.setUser(user);
        Timestamp time =  new Timestamp(System.currentTimeMillis());
        essayDTO.setModified_at(time);
        essayDTO.setId(id);
        if (i!=0)
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(essayDTO, new StatusDTO(400,"fail")));
    }

    @PostMapping
    public ResponseEntity addEssay(@RequestBody EssayDTO essayDTO,@AuthenticationPrincipal User user) throws NotFoundException {
        ComEssay comEssay = new ComEssay(essayDTO);
        int rela_type = 1;
        if(essayDTO.getCompany()!=null){
            rela_type = 2;
        }
        List<Integer> lab_num = new ArrayList<Integer>();
        int i = essayServiceimpl.addEssay(comEssay,lab_num,user.getId(),rela_type,essayDTO.getCompany().getCompanyId());
        if(i!=0)
            return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
        else
            return  ResponseEntity.ok(new Response( new StatusDTO(400,"fail")));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity addEssayComments(@PathVariable Integer id, @RequestBody EssayReplyDTO essayReplyDTO, @AuthenticationPrincipal User user) throws NotFoundException {
        ComEssayReply comEssayReply = new ComEssayReply(essayReplyDTO);
        int i = essayServiceimpl.addReply(comEssayReply,id,0);
        if(i!=0)
            return ResponseEntity.ok(new Response(new StatusDTO(200,"success")));
        else
            return  ResponseEntity.ok(new Response( new StatusDTO(400,"fail")));
    }
}
