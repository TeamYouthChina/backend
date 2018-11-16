package com.youthchina.controller.miaomiaozhang;


import com.youthchina.domain.miaomiaozhang.CompanyHr;
import com.youthchina.domain.miaomiaozhang.CompanyInfo;
import com.youthchina.service.miaomiaozhang.CompanyHrService;
import com.youthchina.util.miaommiaozhang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

//Controller
@RestController
@RequestMapping("companyHrMdg")
@Slf4j
public class CompanyHrController {

    @Autowired
    private CompanyHrService companyHrService;

    /**
     *add hr controller
     * @param companyHr
     * @return
     */
    @PostMapping("/addHr")
    public Result addHr(CompanyHr companyHr ){
        try {
            Map<String, Object> resultMap = companyHrService.addHr(companyHr);
            if (null != resultMap) {
                if ((boolean) resultMap.get("result")) {
                    return new Result<Map>().setCode(0).setMessage("注册成功");
                } else {
                    return new Result<Map>().setCode(400).setMessage("注册失败");
                }
            } else {
                return new Result<Map>().setCode(500).setMessage("系统异常");
            }
        } catch (Exception e) {
            log.error(e.getMessage() + "addHr");
            return new Result<Map>().setCode(500).setMessage("系统异常");
        }

    }

    /**
     * update hr controller
     * @param hr_id
     * @return
     */

    @PutMapping("/updateHr")
    public Result updateHr(String hr_id){
        try {
            Map<String, Object> resultMap = companyHrService.updateHr(hr_id);
            if (null != resultMap) {
                if ((boolean) resultMap.get("result")) {
                    return new Result<Map>().setCode(0).setMessage("修改成功");
                } else {
                    return new Result<Map>().setCode(400).setMessage("修改失败");
                }
            } else {
                return new Result<Map>().setCode(500).setMessage("系统异常");
            }
        } catch (Exception e) {
            log.error(e.getMessage() + "updateHr");
            return new Result<Map>().setCode(500).setMessage("系统异常");
        }

    }

    @GetMapping("/selectHr")
    public CompanyHr selectHr(String hr_id) {

        CompanyHr companyHr = companyHrService.selectHr(hr_id);

        return companyHr;
    }


    @GetMapping("/selectEnterpriseVerification")
    public CompanyInfo selectEnterpriseVerification(String hr_id) {
        CompanyInfo companyInfo = companyHrService.selectEnterpriseVerification(hr_id);

        return companyInfo;
    }

    @GetMapping("/selectPersonalVerification")
    public CompanyHr selectPersonalVerification(String hr_id){
        CompanyHr companyHr=companyHrService.selectPersonalVerification(hr_id);
        return companyHr;
    }

    @PutMapping("/updatePersonalVerification")
    public Result updatePersonalVerification(String hr_id){

        try {
            Map<String, Object> resultMap = companyHrService.updatePersonalVerification(hr_id);
            if (null != resultMap) {

                if ((boolean) resultMap.get("result")) {
                    return new Result<Map>().setCode(0).setMessage("修改成功 ");
                } else {

                    return new Result<Map>().setCode(400).setMessage("修改失败");
                }
            } else {
                return new Result<Map>().setCode(500).setMessage("系统异常");
            }
        } catch (Exception e) {
            log.error(e.getMessage() + "updateHr");
            return new Result<Map>().setCode(500).setMessage("系统异常");
        }

    }



}
