package com.youthchina.controller.qingyang;

import com.youthchina.controller.zhongyang.DomainCRUDController;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.CompanyDTO;
import com.youthchina.dto.CompanyResponseDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import com.youthchina.service.qingyang.CompanyCURDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
@RestController
@RequestMapping("${web.url.prefix}/companies/**")
public class CompanyController extends DomainCRUDController<CompanyDTO, Company, Integer> {


    private String url;
    private CompanyCURDService companyService;
    private StudentService studentService;

    @Autowired
    public CompanyController(CompanyCURDService companyService, @Value("${web.url.prefix}") String prefix,StudentService studentService) {
        this.companyService = companyService;
        this.url = prefix + "/companies/";
        this.studentService=studentService;
    }

    @Override
    protected DomainCRUDService<Company, Integer> getService() {
        return this.companyService;
    }

    @Override
    protected CompanyDTO DomainToDto(Company domain) {
        return new CompanyDTO(domain);
    }

    @Override
    protected Company DtoToDomain(CompanyDTO companyDTO) {
        return new Company(companyDTO);
    }

    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    @PostMapping("/")
    public ResponseEntity<?> createCompanyInfo(@AuthenticationPrincipal User user, @RequestBody CompanyDTO companyDTO) {
        companyDTO.setUserId(user.getId());
        return add(companyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompanyInfo(@AuthenticationPrincipal User user, @RequestBody CompanyDTO companyDTO) throws NotFoundException {
        companyDTO.setUserId(user.getId());
        return update(companyDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyDetail(@PathVariable(name = "id") Integer companyId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, Authentication authentication) throws BaseException {
        Company company = this.companyService.get(companyId);
        if (detailLevel == 1) {
            return ResponseEntity.ok(new Response(new CompanyResponseDTO(company)));
        }
        throw new BaseException();
    }
    /**
    * @Description: 通过company_id以及user_id添加公司收藏
    * @Param: [company_id, user]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/19
    */
    @PutMapping("/{id}/attention")
    public ResponseEntity<?> addCompanyCollection(@PathVariable("id") Integer company_id, @AuthenticationPrincipal User user) throws NotFoundException{
        Integer integer= studentService.addCompCollect(company_id,user.getId());
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer, new StatusDTO(201, "collect successful")));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400,"cannot collect this company,maybe the company has already delete")));

        }

    }
    /**
    * @Description: 通过collect_id删除公司收藏
    * @Param: [collect_id, user]
    * @return: org.springframework.http.ResponseEntity<?>
    * @Author: Qinghong Wang
    * @Date: 2019/2/19
    */

    @DeleteMapping("/collections/{id}")
    public ResponseEntity<?> deleteCompanyCollection(@PathVariable("id") Integer collect_id,@AuthenticationPrincipal User user)throws NotFoundException{
        Integer integer=studentService.deleteCompCollect(collect_id);
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer, new StatusDTO(201, "delete successful")));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400,"cannot delete this company collection,maybe this collection has already delete")));

        }
    }



}
