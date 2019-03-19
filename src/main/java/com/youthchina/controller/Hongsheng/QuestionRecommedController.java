package com.youthchina.controller.Hongsheng;

import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.dto.Response;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.question.QuestionResponseDTO;
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
    private final QuestionRecommendServiceImplement questionRecommendServiceImplement;

    @Autowired
    public QuestionRecommedController(QuestionRecommendServiceImplement questionRecommendServiceImplement) {
        this.questionRecommendServiceImplement = questionRecommendServiceImplement;
    }

    @GetMapping("/questions")
    public ResponseEntity getRecommendQuestion() {
        List<Question> questionList = questionRecommendServiceImplement.getQuestionForYou();
        List<QuestionResponseDTO> resultList = new ArrayList<>();
        for (Question question : questionList) {
            resultList.add(new QuestionResponseDTO(question));
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("questions", resultList);

        if (resultList.size() != 0)
            return ResponseEntity.ok(new Response(map, new StatusDTO(200, "success")));
        else
            return ResponseEntity.ok(new Response(map, new StatusDTO(400, "fail")));
    }
}
