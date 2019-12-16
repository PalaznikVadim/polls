package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserAnswer;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private QuestionService questionService;

    @Override
    public List<UserAnswer> saveAll(List<UserAnswer> userAnswers) {
        Set<Integer> questionIds = new HashSet<>();
        for (UserAnswer userAnswer : userAnswers) {
            questionIds.add(userAnswer.getIdQuestion());
            userAnswer.setDateTime(new Date());
        }
        RestTemplate restTemplate = new RestTemplate();
        UserAnswer[] userAnswerList = (restTemplate.postForEntity(backendServerUrl
                + "/api/userAnswer/all", userAnswers, UserAnswer[].class)).getBody();

        for (int id : questionIds) {
            questionService.updateStatsQuestion(id);
        }
        return Arrays.asList(userAnswerList);
    }

    @Override
    public int countSelected(int idAnswer) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/userAnswer/count?idAnswer=" + idAnswer, Integer.class);
    }

    @Override
    public int countAllSelected(int idQuestion) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/userAnswer?idQuestion=" +
                idQuestion, Integer.class);
    }
}
