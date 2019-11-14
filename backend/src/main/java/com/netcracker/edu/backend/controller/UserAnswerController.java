package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.UserAnswer;
import com.netcracker.edu.backend.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userAnswer")
public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;

    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public List<UserAnswer> saveAll(@RequestBody List<UserAnswer> userAnswers){
        return userAnswerService.saveAll(userAnswers);
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public Integer countSelected(@RequestParam Integer idAnswer){
        return userAnswerService.getCountSelected(idAnswer);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Integer getCountAllSelected(@RequestParam Integer idQuestion){
        return userAnswerService.getCountAllSelected(idQuestion);
    }
}
