package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.UserAnswer;
import com.netcracker.edu.fapi.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userAnswer")
public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;


    @RequestMapping(value = "/all", method = RequestMethod.POST)
    List<UserAnswer> save(@RequestBody List<UserAnswer> userAnswers) {
        return userAnswerService.saveAll(userAnswers);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Integer countSelected(@RequestParam Integer idAnswer) {
        return userAnswerService.countSelected(idAnswer);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Integer getAllSelected(@RequestParam Integer idQuestion) {
        return userAnswerService.countAllSelected(idQuestion);
    }
}
