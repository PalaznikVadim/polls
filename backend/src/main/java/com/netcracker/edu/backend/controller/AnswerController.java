package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Answer;
import com.netcracker.edu.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;


    @RequestMapping(value = "/question",method = RequestMethod.GET)
    public List<Answer> getAllAnswersByQuestionId(@RequestParam String id){
        Answer[] answers = answerService.getAllAnswerByQuestionId(Integer.valueOf(id));
        return Arrays.asList(answers);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Answer saveAnswer(@RequestBody Answer answer){
        System.out.println(answer.toString());
        Answer answ=answerService.saveAnswer(answer);

        return answ;
    }

    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public Iterable<Answer> saveAllAnswer(@RequestBody List<Answer> answers){
        return answerService.saveAllAnswers(answers);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public void deleteAnswer(@RequestParam String id){
        answerService.deleteById(Integer.valueOf(id));
    }
//    List<Answer> getAllAnswerByQuestionId(Integer idQuestion);
//    Answer saveAnswer(Answer answer);
//    void deleteById(Integer id);
//    Iterable<Answer> saveAllAnswers(List<Answer> answers);
}
