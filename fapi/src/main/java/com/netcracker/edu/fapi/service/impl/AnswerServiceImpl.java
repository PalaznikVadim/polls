package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private QuestionService questionService;

    @Override
    public List<Answer> getAllAnswerByQuestionId(int idQuestion) {

        RestTemplate restTemplate = new RestTemplate();
        Answer[] answers = restTemplate.getForObject(backendServerUrl + "api/answer/question?id=" +
                idQuestion, Answer[].class);
        return answers == null ? Collections.emptyList() : Arrays.asList(answers);
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/answer", answer, Answer.class).getBody();
    }

    @Override
    public void deleteById(int id) {
        Answer answer = getById(id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/answer/" + id);
        questionService.updateStatsQuestion(answer.getIdQuestion());
    }

    @Override
    public Answer getById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/answer/" + id, Answer.class);
    }
}
