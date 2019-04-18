package com.youthchina.controller.application;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.controller.DomainCRUDController;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.company.CompanyRequestDTO;
import com.youthchina.dto.company.CompanyResponseDTO;
import com.youthchina.dto.job.JobNoCompanyDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.exception.BaseException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.application.CompanyCURDService;
import com.youthchina.service.user.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
@RestController
@RequestMapping("${web.url.prefix}/companies/**")
public class CompanyController extends DomainCRUDController<Company, Integer> {


    private String url;
    private CompanyCURDService companyService;
    private StudentService studentService;

    @Autowired
    public CompanyController(CompanyCURDService companyService, @Value("${web.url.prefix}") String prefix, StudentService studentService) {
        this.companyService = companyService;
        this.url = prefix + "/companies/";
        this.studentService = studentService;
    }

    @Override
    protected DomainCRUDService<Company, Integer> getService() {
        return this.companyService;
    }


    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    /**
     * 添加公司
     * @param user
     * @param company
     * @return
     * @throws NotFoundException
     */
    @PostMapping("/")
    @ResponseBodyDTO(CompanyResponseDTO.class)
    public ResponseEntity<?> createCompanyInfo(@AuthenticationPrincipal User user, @RequestBodyDTO(CompanyRequestDTO.class) Company company) throws NotFoundException {
        company.setUserId(user.getId());
        return add(company);
    }

    /**
     * 更新公司
     * @param id
     * @param user
     * @param company
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    @ResponseBodyDTO(CompanyResponseDTO.class)
    public ResponseEntity<?> updateCompanyInfo(@PathVariable Integer id, @AuthenticationPrincipal User user, @RequestBodyDTO(CompanyRequestDTO.class) Company company) throws NotFoundException {
        company.setCompanyId(id);
        company.setUserId(user.getId());
        return update(company);
    }

    /**
     * 获取公司
     * @param companyId
     * @param detailLevel
     * @param user
     * @return
     * @throws BaseException
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyDetail(@PathVariable(name = "id") Integer companyId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, @AuthenticationPrincipal User user) throws BaseException {
        Company company = null ;
        if(user != null){
            company = companyService.getCompanyWithCollected(companyId, user.getId());
        } else {
            company = companyService.get(companyId);
        }
        if (detailLevel == 1) {
            return ResponseEntity.ok(new Response(new CompanyResponseDTO(company)));
        }
        throw new BaseException();
    }

    /**
     * 获取公司下属职位 (未对DeadLine 进行判断)
     * @param companyId
     * @param user
     * @param pageRequest
     * @return
     * @throws BaseException
     */
    @GetMapping("/{id}/jobs")
    public ResponseEntity<?> getAllJobsOfOneCompany(@PathVariable(name = "id") Integer companyId, @AuthenticationPrincipal User user, PageRequest pageRequest) throws BaseException {
        List<Job> jobList = companyService.getJobsByCompanyId(companyId,user.getId());
        List<JobNoCompanyDTO> jobResponseDTOList = new JobNoCompanyDTO().convertToDTO(jobList);
        List<JobNoCompanyDTO> result=new ArrayList<>();
        if(jobResponseDTOList!=null&&jobResponseDTOList.size()!=0){
            result=jobResponseDTOList.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,jobResponseDTOList.size()));
        }
        ListResponse listResponse = new ListResponse(pageRequest, jobList.size(), result);
        return ResponseEntity.ok(listResponse);

    }

    /**
     * 删除公司 (暂时关闭)
     * @param companyId
     * @param detailLevel
     * @param user
     * @return
     * @throws BaseException
     */
//    @DeleteMapping("/{id}/none/none/none")
//    public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Integer companyId, @RequestParam(value = "detailLevel", defaultValue = "1") Integer detailLevel, @AuthenticationPrincipal User user) throws BaseException {
////        this.companyService.delete(companyId);
////        if (detailLevel == 1) {
////            return ResponseEntity.ok(new Response());
////        }
////        throw new BaseException();
//
//        return delete(companyId);
//    }

    /**
     * @Description: 通过company_id以及user_id添加公司收藏
     * @Param: [company_id, user]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: Qinghong Wang
     * @Date: 2019/2/19
     */
    @PutMapping("/{id}/attention")
    public ResponseEntity<?> addCompanyCollection(@PathVariable("id") Integer company_id, @AuthenticationPrincipal User user) throws NotFoundException {
        Integer integer = studentService.addCompCollect(company_id, user.getId());
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400, "cannot collect this company,maybe the company has already delete")));

        }

    }

    /**
     * @Description: 通过collect_id删除公司收藏
     * @Param: [collect_id, user]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: Qinghong Wang
     * @Date: 2019/2/19
     */

    @DeleteMapping("/attentions/{id}")
    public ResponseEntity<?> deleteCompanyCollection(@PathVariable("id") Integer company_id, @AuthenticationPrincipal User user) throws NotFoundException {
        Integer collectionId = studentService.getCollectionByCompanyId(company_id, user.getId());
        if (collectionId == null) {
            return ResponseEntity.status(400).body(new StatusDTO(400, "no such company collection"));
        }
        Integer integer = studentService.deleteCompCollect(collectionId);
        if (integer == 1) {
            return ResponseEntity.ok(new Response
                    (integer));
        } else {
            return ResponseEntity.ok(new Response(integer, new StatusDTO(400, "cannot delete this company collection,maybe this collection has already delete")));

        }
    }


}
