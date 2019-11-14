package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Stats;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.models.UserAnswer;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.StatsService;
import com.netcracker.edu.fapi.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/userAnswer")
public class UserAnswerController {

    @Autowired
    private StatsService statsService;
    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/all",method = RequestMethod.POST)
    ResponseEntity<UserAnswer[]> save(@RequestBody List<UserAnswer> userAnswers){

        Set<Integer> questionIds=new HashSet<>();
        for(UserAnswer userAnswer:userAnswers) {
            questionIds.add(userAnswer.getIdQuestion());
            userAnswer.setDateTime(new Date());
        }
        ResponseEntity<UserAnswer[]> userAnswerList=userAnswerService.saveAll(userAnswers);

        for(int id:questionIds) {//вынести в метод и запускать асинхронно
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

        return userAnswerList;
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public Integer countSelected(@RequestParam Integer idAnswer){
        return userAnswerService.countSelected(idAnswer);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Integer getAllSelected(@RequestParam Integer idQuestion){
        return userAnswerService.countAllSelected(idQuestion);
    }
}
