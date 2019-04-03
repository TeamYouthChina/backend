package com.youthchina.controller.Hongsheng;


import com.youthchina.domain.qingyang.Company;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CompanyRecommendServiceImplement;
import com.youthchina.service.qingyang.CompanyCURDServiceImpl;
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
    private final CompanyRecommendServiceImplement companyRecommendServiceImplement;

    private final CompanyCURDServiceImpl companyCURDService;



    @Autowired
    public CompanyRecommendController(CompanyRecommendServiceImplement companyRecommendServiceImplement, CompanyCURDServiceImpl companyCURDService) {
        this.companyRecommendServiceImplement = companyRecommendServiceImplement;
        this.companyCURDService = companyCURDService;
    }

    @GetMapping("/companies/none") // TODO : Need Revision
    public ResponseEntity getRecommendCompany() throws NotFoundException {
        List<Company> companyList = companyRecommendServiceImplement.getPopCompanyForYou();
        List<CompanyResponseDTO> resultList = new ArrayList<>();
        for (Company company : companyList) {
            resultList.add(new CompanyResponseDTO(company));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("companies", resultList);

        if (resultList.size() != 0)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400, "fail")));
    }

    @GetMapping("/companies")
    public ResponseEntity getRecommendCompanyTMP() throws NotFoundException {
        List<Company> companyList = companyCURDService.getAll();
        List<CompanyResponseDTO> resultList = new ArrayList<>();

        for (int i = 0; i < 5; i++) { //推荐5个公司
            resultList.add(new CompanyResponseDTO(companyList.get(i)));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("companies", resultList);

        if (resultList.size() != 0)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400, "fail")));
    }

}
