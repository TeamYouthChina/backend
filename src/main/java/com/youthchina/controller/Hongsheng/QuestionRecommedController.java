package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.QuestionDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.QuestionRecommendServiceImplement;
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
public class QuestionRecommedController {
    @Autowired
    public QuestionRecommendServiceImplement questionRecommendServiceImplement;

    @GetMapping("/questions")
    public ResponseEntity getRecommandQuestion() throws NotFoundException {
        List<Question> questionList = questionRecommendServiceImplement.getQuestionForYou();
        List<QuestionDTO> resultList = new ArrayList<>();
        for(Question question : questionList) {
            resultList.add(new QuestionDTO(question));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("questions", resultList);

        if (resultList!=null)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400,"fail")));
    }
}
