package com.youthchina.controller.qingyang;

import com.youthchina.controller.zhongyang.DomainCRUDController;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.dto.CompanyDTO;
import com.youthchina.dto.Response;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.qingyang.CompanyCURDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @Autowired
    public CompanyController(CompanyCURDService companyService, @Value("${web.url.prefix}") String prefix) {
        this.companyService = companyService;
        this.url = prefix + "/companies/";
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
    public ResponseEntity<?> createCompanyInfo(@RequestBody CompanyDTO companyDTO) {
        return add(companyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompanyInfo(@RequestBody CompanyDTO companyDTO) throws NotFoundException {
        return update(companyDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyDetail(@PathVariable(name = "id") Integer companyId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, Authentication authentication) throws BaseException {
        Company company = this.companyService.get(companyId);
        if (detailLevel == 1) {
            return ResponseEntity.ok(new Response(company));
        }
        throw new BaseException();
    }



}
