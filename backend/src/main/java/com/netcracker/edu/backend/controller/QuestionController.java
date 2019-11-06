package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Question;
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

    @RequestMapping(value = "/poll",method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getAllQuestionsByPollId(@RequestParam String idPoll){
        List<Question> questions = questionService.getAllQuestionByIdPoll(Integer.valueOf(idPoll));
        return ResponseEntity.ok(questions);
    }

    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public Optional<Question> getQuestionById(@RequestParam String id){
//        Optional<Question> question=questionService.getById(Integer.valueOf(id));
        return questionService.getById(Integer.valueOf(id));
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Question saveQuestion(@RequestBody Question question){
        return questionService.save(question);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteQuestion(@RequestParam Integer id){
        questionService.delete(Integer.valueOf(id));
    }
}
