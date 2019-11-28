package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserAnswer;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseEntity<UserAnswer[]> saveAll(List<UserAnswer> userAnswers) {
        Set<Integer> questionIds=new HashSet<>();
        for(UserAnswer userAnswer:userAnswers) {
            questionIds.add(userAnswer.getIdQuestion());
            userAnswer.setDateTime(new Date());
        }
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<UserAnswer[]> userAnswerList=restTemplate.postForEntity(backendServerUrl+"/api/userAnswer/all",userAnswers,UserAnswer[].class);

        for(int id:questionIds) {//вынести в метод и запускать асинхронно
            questionService.updateStatsQuestion(id);
        }
        return userAnswerList;
    }

    @Override
    public Integer countSelected( Integer idAnswer) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/userAnswer/count?idAnswer="+idAnswer,Integer.class);
    }

    @Override
    public Integer countAllSelected(Integer idQuestion) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/userAnswer?idQuestion="+idQuestion,Integer.class);
    }
}
