package com.youthchina.controller.Hongsheng;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.dto.Applicant.SendingEmailDTO;
import com.youthchina.dto.CompanyResponseDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Qinghong.MessageSendService;
import com.youthchina.service.jinhao.communityQA.CompanyRecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/discovery")
public class CompanyRecommendController {
    @Autowired
    public CompanyRecommendServiceImplement companyRecommendServiceImplement;

    @Autowired
    public MessageSendService messageSendService;

    @GetMapping("/companies")
    public ResponseEntity getRecommandCompany() throws NotFoundException {
        List<Company> companyList = companyRecommendServiceImplement.getPopCompanyForYou();
        List<CompanyResponseDTO> resultList = new ArrayList<>();
        for (Company company : companyList) {
            resultList.add(new CompanyResponseDTO(company));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("companies", resultList);

        if (resultList != null)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400, "fail")));
    }

    @Deprecated
    @PostMapping("/sendTestEmail")
    public ResponseEntity sendEmail() throws IOException {
        SendingEmailDTO sendingEmailDTO = new SendingEmailDTO();
        sendingEmailDTO.setCompany_email("");
        File file = new ClassPathResource("png2pdf.pdf").getFile();
        InputStream input = new FileInputStream(file);
        byte[] bytesArray = new byte[(int) file.length()];

        input.read(bytesArray); //read file into bytes[]
        input.close();
        sendingEmailDTO.setBytes(bytesArray);
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(sendingEmailDTO);
        System.out.print(message);
        messageSendService.sendMessage(message);
        return ResponseEntity.ok(new Response());

    }
}
