package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.UserAnswer;
import com.netcracker.edu.backend.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userAnswer")
public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<UserAnswer>> saveAll(@RequestBody List<UserAnswer> userAnswers) {
        return ResponseEntity.ok().body(userAnswerService.saveAll(userAnswers));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Integer countSelected(@RequestParam int idAnswer) {
        return userAnswerService.getCountSelected(idAnswer);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Integer getCountAllSelected(@RequestParam int idQuestion) {
        return userAnswerService.getCountAllSelected(idQuestion);
    }
}
