package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.converters.AnswerConverter;
import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerConverter answerConverter;

    @RequestMapping(value = "/poll",method = RequestMethod.GET)
    public ResponseEntity<List<ViewQuestion>> getAllQuestionsByPollId(@RequestParam String idPoll){

        List<Question> questions = questionService.getAllQuestionByIdPoll(Integer.valueOf(idPoll));
        List<ViewQuestion> viewQuestions=new ArrayList<>();
        for(Question question:questions){
            viewQuestions.add(questionConverter.convertQuestionToViewQuestionWithAnswer(question));
        }
        return ResponseEntity.ok(viewQuestions);
    }

    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public ViewQuestion getQuestionById(@RequestParam String id){

        Question question=questionService.getById(Integer.valueOf(id));
        ViewQuestion viewQuestion=questionConverter.convertQuestionToViewQuestionWithAnswer(question);

        return viewQuestion;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ViewQuestion saveQuestion(@RequestBody ViewQuestion viewQuestion){

        List<ViewAnswer> viewAnswers=viewQuestion.getAnswers();
        Question question=questionConverter.convertViewQuestToQuestion(viewQuestion);
        question=questionService.save(question);

        for(ViewAnswer viewAnswer:viewAnswers){
            answerService.saveAnswer(answerConverter.convertViewAnswerToAnswer(viewAnswer,question.getId()));
        }

        return questionConverter.convertQuestionToViewQuestionWithAnswer(question);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteQuestion(@RequestParam Integer id){
        questionService.delete(Integer.valueOf(id));
    }
}
