package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.converters.AnswerConverter;
import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.validators.AnswerValidator;
import com.netcracker.edu.fapi.validators.QuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/poll",method = RequestMethod.GET)
    public ResponseEntity<List<ViewQuestion>> getAllQuestionsByPollId(@RequestParam Integer idPoll){
        List<ViewQuestion> viewQuestions = questionService.getAllQuestionByIdPoll(idPoll);
        return ResponseEntity.ok(viewQuestions);
    }

    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public ViewQuestion getQuestionById(@RequestParam String id){
        ViewQuestion viewQuestion=questionService.getById(Integer.valueOf(id));
        return viewQuestion;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity saveQuestion(@RequestBody ViewQuestion viewQuestion) {
        return questionService.save(viewQuestion);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteQuestion(@RequestParam Integer id){
        questionService.delete(id);
    }
}
