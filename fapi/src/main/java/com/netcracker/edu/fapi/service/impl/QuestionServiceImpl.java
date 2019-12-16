package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.AnswerConverter;
import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.Stats;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.service.StatsService;
import com.netcracker.edu.fapi.service.UserAnswerService;
import com.netcracker.edu.fapi.validators.AnswerValidator;
import com.netcracker.edu.fapi.validators.QuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private AnswerConverter answerConverter;
    @Autowired
    private QuestionValidator questionValidator;
    @Autowired
    private AnswerValidator answerValidator;

    @Override
    public List<ViewQuestion> getAllQuestionByIdPoll(int idPoll) {
        RestTemplate restTemplate = new RestTemplate();
        Question[] questions = restTemplate.getForObject(backendServerUrl + "/api/question/poll/" + idPoll,
                Question[].class);
        List<ViewQuestion> viewQuestions = Arrays.stream(questions).
                map(question -> questionConverter.convertQuestionToViewQuestionWithAnswer(question))
                .collect(Collectors.toList());
        return viewQuestions;
    }

    @Override
    public ViewQuestion getById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Question question = restTemplate.getForObject(backendServerUrl + "/api/question/" + id, Question.class);
        return questionConverter.convertQuestionToViewQuestionWithAnswer(question);
    }

    @Override
    public ResponseEntity<?> save(ViewQuestion viewQuestion) {
        List<ObjectError> errorList = new ArrayList<>();
        List<ViewAnswer> viewAnswers = viewQuestion.getAnswers();

        DataBinder dataBinder = new DataBinder(viewQuestion);
        dataBinder.addValidators(questionValidator);
        dataBinder.validate();

        if (dataBinder.getBindingResult().hasErrors())
            errorList.addAll(dataBinder.getBindingResult().getAllErrors());

        for (ViewAnswer viewAnswer : viewAnswers) {
            dataBinder = new DataBinder(viewAnswer);
            dataBinder.addValidators(answerValidator);
            dataBinder.validate();
            if (dataBinder.getBindingResult().hasErrors())
                errorList.addAll(dataBinder.getBindingResult().getAllErrors());
        }

        if (errorList.size() != 0) {
            return ResponseEntity.badRequest().body(errorList);
        } else {
            Question question = questionConverter.convertViewQuestToQuestion(viewQuestion);
            RestTemplate restTemplate = new RestTemplate();
            question = restTemplate.postForEntity(backendServerUrl + "/api/question", question, Question.class).getBody();
            for (ViewAnswer viewAnswer : viewAnswers) {
                answerService.saveAnswer(answerConverter.convertViewAnswerToAnswer(viewAnswer, question.getId()));
            }
            return ResponseEntity.ok(questionConverter.convertQuestionToViewQuestionWithAnswer(question));
        }
    }

    @Override
    public void delete(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/question/" + id);
    }

    @Override
    public void updateStatsQuestion(int questionId) {
        List<Answer> answers = answerService.getAllAnswerByQuestionId(questionId);
        for (Answer answer : answers) {
            Stats stats = statsService.getByIdAnswer(answer.getId());
            if (stats == null) {
                stats = new Stats();
                stats.setIdAnswer(answer.getId());
            }
            stats.setCount(userAnswerService.countSelected(answer.getId()));

            double percent = Math.round((double) stats.getCount() / userAnswerService.countAllSelected(questionId) * 100);
            stats.setPercent(percent);
            statsService.save(stats);
        }
    }

    @Override
    public List<ViewQuestion> getPollStats(int idPoll) {
        List<ViewQuestion> questions = getAllQuestionByIdPoll(idPoll);
        List<ViewQuestion> viewQuestions = new ArrayList<>();
        for (ViewQuestion question : questions) {
            viewQuestions.add(questionConverter.convertViewQuestionToViewQuestionWithAnswers(question));
        }
        return viewQuestions;
    }
}
