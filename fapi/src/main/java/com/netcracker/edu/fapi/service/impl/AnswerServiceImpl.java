package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.AnswerConverter;
import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import com.netcracker.edu.fapi.service.AnswerService;
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
    private AnswerConverter answerConverter;

    @Override
    public List<Answer> getAllAnswerByQuestionId(Integer idQuestion) {

        RestTemplate restTemplate = new RestTemplate();
        Answer[] answers=restTemplate.getForObject(backendServerUrl+"api/answer/question?id="+idQuestion,Answer[].class);
        return answers==null ? Collections.emptyList() : Arrays.asList(answers);
    }

    @Override
    public Answer saveAnswer(Answer answer) {

        RestTemplate restTemplate = new RestTemplate();
        Answer ans=restTemplate.postForEntity(backendServerUrl + "/api/answer", answer, Answer.class).getBody();

        return answer;
    }

    @Override
    public void deleteById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/answer/delete?id=" + id);
    }

    @Override
    public Answer getById(Integer id) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/answer/id?id="+id,Answer.class);
    }

//    @Override
//    public Iterable<Answer> saveAllAnswers(List<Answer> answers) {
//        RestTemplate restTemplate = new RestTemplate();
//        List<Answer> answerList=restTemplate.getForObject(backendServerUrl + "/api/answer", answers, Iterable<Answer>.class)
//        return null;
//    }
}
