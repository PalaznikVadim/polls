package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/question",method = RequestMethod.GET)
    public ResponseEntity<List<Answer>> getAllAnswersByQuestionId(@RequestParam String id){
        List<Answer> answers = answerService.getAllAnswerByQuestionId(Integer.valueOf(id));
        return  ResponseEntity.ok(answers);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Answer saveAnswer(@RequestBody Answer answer){
        System.out.println(answer.toString());
        answer=answerService.saveAnswer(answer);

        return answer;
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public void deleteAnswer(@RequestParam Integer id){
        Answer answer =answerService.getById(id);
        answerService.deleteById(id);
        questionService.updateStatsQuestion(answer.getIdQuestion());
    }

}
