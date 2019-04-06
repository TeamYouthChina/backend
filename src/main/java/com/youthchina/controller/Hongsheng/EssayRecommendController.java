package com.youthchina.controller.Hongsheng;

import com.youthchina.service.jinhao.toBeDeleted.EssayRecommendServiceImplement;
import com.youthchina.service.qingyang.CompanyCURDService;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/discovery")
public class EssayRecommendController {
    private EssayRecommendServiceImplement essayRecommendServiceImplement;
    private EssayServiceImpl essayServiceimpl;
    private UserService userService;
    private CompanyCURDService companyCURDService;

    @Autowired
    public EssayRecommendController(EssayRecommendServiceImplement essayRecommendServiceImplement, EssayServiceImpl essayServiceimpl, UserService userService, CompanyCURDService companyCURDService) {
        this.essayRecommendServiceImplement = essayRecommendServiceImplement;
        this.essayServiceimpl = essayServiceimpl;
        this.userService = userService;
        this.companyCURDService = companyCURDService;
    }

//    @GetMapping("/articles")
//    public ResponseEntity getRecommendEssay() throws NotFoundException {
//        List<ComEssay> essayList = essayRecommendServiceImplement.getEssayForYou();
//        List<EssayDTO> resultList = new ArrayList<>();
//        for (ComEssay essay : essayList) {
//            EssayDTO essayDTO = new EssayDTO(essay);
//            ComAuthorEssayMap comAuthorEssayMap = essayServiceimpl.getEssayAuthor(essay.getEssay_id());
//
//            User user = userService.get(comAuthorEssayMap.getUser_id());
//
//            if (comAuthorEssayMap.getRela_type() == 2) {
//                Company company = companyCURDService.get(comAuthorEssayMap.getRela_id());
//                essayDTO.setCompany(new CompanyResponseDTO(company));
//            }
//            essayDTO.setAuthor(new UserDTO(user));
//
//            resultList.add(essayDTO);
//        }
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("articles", resultList);
//
//        if (resultList.size() != 0)
//            return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
//        else
//            return ResponseEntity.ok(new Response(map, new StatusDTO(400, "fail")));
//    }
}
