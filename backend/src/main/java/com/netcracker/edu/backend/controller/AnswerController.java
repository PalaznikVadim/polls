package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Answer;
import com.netcracker.edu.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;


    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public List<Answer> getAllAnswersByQuestionId(@RequestParam int id) {
        List<Answer> answers = answerService.getAllAnswerByQuestionId(id);
        return answers;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Answer saveAnswer(@RequestBody Answer answer) {
        Answer answ = answerService.saveAnswer(answer);
        return answ;
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public Iterable<Answer> saveAllAnswer(@RequestBody List<Answer> answers) {
        return answerService.saveAllAnswers(answers);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAnswer(@PathVariable int id) {
        answerService.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Answer> getById(@PathVariable int id) {
        return answerService.getById(id);
    }
}
