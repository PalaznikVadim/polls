package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.Stats;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.service.StatsService;
import com.netcracker.edu.fapi.service.UserAnswerService;
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

    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private StatsService statsService;

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

    @Override
    public void updateStatsQuestion(Integer id) {
        List<Answer> answers=answerService.getAllAnswerByQuestionId(id);
        for (Answer answer : answers) {
            Stats stats = statsService.getByIdAnswer(answer.getId());
            if (stats == null)
                stats = new Stats();
            stats.setIdAnswer(answer.getId());
            stats.setCount(userAnswerService.countSelected(answer.getId()));

            double percent = Math.round((double)stats.getCount()/ userAnswerService.countAllSelected(id) * 100*100)/100D;
            stats.setPercent(percent);
            statsService.save(stats);
        }
    }
}
