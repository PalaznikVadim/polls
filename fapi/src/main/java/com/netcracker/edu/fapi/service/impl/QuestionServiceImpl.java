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
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private AnswerConverter answerConverter;
    @Autowired
    private QuestionValidator questionValidator;
    @Autowired
    private AnswerValidator answerValidator;

    @Override
    public List<ViewQuestion> getAllQuestionByIdPoll(Integer idPoll) {
        RestTemplate restTemplate = new RestTemplate();
        Question[] questions = restTemplate.getForObject(backendServerUrl + "/api/question/poll?idPoll="+idPoll, Question[].class);
        List<ViewQuestion> viewQuestions=new ArrayList<>();
        for(Question question:questions){
            viewQuestions.add(questionConverter.convertQuestionToViewQuestionWithAnswer(question));
        }
        return viewQuestions;
    }

    @Override
    public ViewQuestion getById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Question question=restTemplate.getForObject(backendServerUrl+"/api/question/id?id="+id,Question.class);
        return questionConverter.convertQuestionToViewQuestionWithAnswer(question);
    }

    @Override
    public ResponseEntity<?> save(ViewQuestion viewQuestion) {
        List<ObjectError> errorList=new ArrayList<>();
        List<ViewAnswer> viewAnswers = viewQuestion.getAnswers();

        DataBinder dataBinder=new DataBinder(viewQuestion);
        dataBinder.addValidators(questionValidator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors())
            errorList.addAll(dataBinder.getBindingResult().getAllErrors());

        for(ViewAnswer viewAnswer:viewAnswers){
            dataBinder=new DataBinder(viewAnswer);
            dataBinder.addValidators(answerValidator);
            dataBinder.validate();
            if(dataBinder.getBindingResult().hasErrors())
                errorList.addAll(dataBinder.getBindingResult().getAllErrors());
        }

        if(errorList.size()!=0){
            return ResponseEntity.badRequest().body(errorList);
        }else {
            Question question=questionConverter.convertViewQuestToQuestion(viewQuestion);
            RestTemplate restTemplate = new RestTemplate();
            question=restTemplate.postForEntity(backendServerUrl+"/api/question",question,Question.class).getBody();
            for (ViewAnswer viewAnswer : viewAnswers) {
                answerService.saveAnswer(answerConverter.convertViewAnswerToAnswer(viewAnswer, question.getId()));
            }
            return ResponseEntity.ok(questionConverter.convertQuestionToViewQuestionWithAnswer(question));
        }
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

    @Override
    public List<ViewQuestion> getPollStats(Integer idPoll) {
        List<ViewQuestion> questions=getAllQuestionByIdPoll(idPoll);
        List<ViewQuestion> viewQuestions=new ArrayList<>();
        for(ViewQuestion question:questions){
            viewQuestions.add(questionConverter.convertViewQuestionToViewQuestionWithAnswers(question));
        }
        return viewQuestions;
    }
}
