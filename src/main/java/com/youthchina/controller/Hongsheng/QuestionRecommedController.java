package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.jinhao.Question;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.question.QuestionDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.toBeDeleted.QuestionRecommendServiceImplement;
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
    public ResponseEntity getRecommendQuestion() throws NotFoundException {
        List<Question> questionList = questionRecommendServiceImplement.getQuestionForYou();
        List<QuestionDTO> resultList = new ArrayList<>();
        for(Question question : questionList) {
            resultList.add(new QuestionResponseDTO(question));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("questions", resultList);

        if (resultList!=null)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200,"success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400,"fail")));
    }
}
