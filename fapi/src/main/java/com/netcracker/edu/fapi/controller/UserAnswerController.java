package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.UserAnswer;
import com.netcracker.edu.fapi.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/userAnswer")
public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    ResponseEntity<UserAnswer> save(@RequestBody UserAnswer userAnswer){
        userAnswer.setDateTime(new Date());
        return userAnswerService.save(userAnswer);
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public Integer countSelected(@RequestParam Integer idAnswer){
        return userAnswerService.countSelected(idAnswer);
    }
}
