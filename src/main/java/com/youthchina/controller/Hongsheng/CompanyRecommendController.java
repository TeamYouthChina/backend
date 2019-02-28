package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.dto.CompanyResponseDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CompanyRecommendServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/companies")
    public ResponseEntity getRecommandCompany() throws NotFoundException {
        List<Company> companyList = companyRecommendServiceImplement.getPopCompanyForYou();
        List<CompanyResponseDTO> resultList = new ArrayList<>();
        for(Company company : companyList) {
            resultList.add(new CompanyResponseDTO(company));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("users", resultList);

        if (resultList!=null)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400,"fail")));
    }
}
