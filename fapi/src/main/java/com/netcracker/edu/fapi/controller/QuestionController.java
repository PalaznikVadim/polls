package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Question getQuestionById(@RequestParam String id){
//        Optional<Question> question=questionService.getById(Integer.valueOf(id));
        return questionService.getById(Integer.valueOf(id));
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Question saveQuestion(@RequestBody Question question){
        Question quest=questionService.save(question);
        System.out.println(quest.toString());
        return quest;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deleteQuestion(@RequestParam Integer id){
        questionService.delete(Integer.valueOf(id));
    }
}
