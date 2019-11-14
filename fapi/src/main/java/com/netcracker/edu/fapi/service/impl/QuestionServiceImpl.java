package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Question> getAllQuestionByIdPoll(Integer idPoll) {
        RestTemplate restTemplate = new RestTemplate();
        Question[] questionsResponse = (Question[]) restTemplate.getForObject(backendServerUrl + "/api/question/poll?idPoll="+idPoll, Question[].class);
        return Arrays.asList(questionsResponse);
    }

    @Override
    public Question getById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Question question=restTemplate.getForObject(backendServerUrl+"/api/question/id?id="+id,Question.class);
        return question;
    }

    @Override
    public Question save(Question question) {

        RestTemplate restTemplate = new RestTemplate();
        Question quest=restTemplate.postForEntity(backendServerUrl+"/api/question",question,Question.class).getBody();
        System.out.println(quest.toString());

        return quest;
    }

    @Override
    public void delete(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl+"/api/question/delete?id="+id);
    }
}
