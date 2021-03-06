package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.viewModels.QuestionWithAnswerCount;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = "/poll/{idPoll}", method = RequestMethod.GET)
    public ResponseEntity<List<ViewQuestion>> getAllQuestionsByPollId(@PathVariable int idPoll) {
        List<ViewQuestion> viewQuestions = questionService.getAllQuestionByIdPoll(idPoll);
        return ResponseEntity.ok(viewQuestions);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ViewQuestion getQuestionById(@PathVariable int id) {
        ViewQuestion viewQuestion = questionService.getById(id);
        return viewQuestion;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity saveQuestion(@RequestBody ViewQuestion viewQuestion) {
        return questionService.save(viewQuestion);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuestion(@PathVariable int id) {
        questionService.delete(id);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/withAnswerCount")
    public List<QuestionWithAnswerCount> getQuestionWithAnswerCount(){
        return questionService.getQuestionWithAnswerCount();
    }
}
