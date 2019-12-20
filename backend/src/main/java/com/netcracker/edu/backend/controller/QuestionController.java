package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Question;
import com.netcracker.edu.backend.entity.QuestionWithAnswerCount;
import com.netcracker.edu.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/poll/{idPoll}", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getAllQuestionsByPollId(@PathVariable int idPoll) {
        List<Question> questions = questionService.getAllQuestionByIdPoll(idPoll);
        return ResponseEntity.ok(questions);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Question> getQuestionById(@PathVariable int id) {
        return questionService.getById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Question saveQuestion(@RequestBody Question question) {
        return questionService.save(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuestion(@PathVariable int id) {
        questionService.delete(id);
    }

    @RequestMapping("/withAnswerCount")
    public List<QuestionWithAnswerCount> getQuestionWithAnswerCount(){
        return questionService.getDTO();
    }

 }
