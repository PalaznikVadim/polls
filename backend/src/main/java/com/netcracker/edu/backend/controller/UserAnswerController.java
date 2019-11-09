package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.UserAnswer;
import com.netcracker.edu.backend.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAnswer")
public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public UserAnswer save(@RequestBody UserAnswer userAnswer){
        return userAnswerService.save(userAnswer);
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public Integer countSelected(@RequestParam Integer idAnswer){
        return userAnswerService.getCountSelected(idAnswer);
    }
}
