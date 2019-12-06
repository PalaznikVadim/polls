package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

//    @RequestMapping(value = "/question",method = RequestMethod.GET)
//    public ResponseEntity<List<Answer>> getAllAnswersByQuestionId(@RequestParam String id){
//        List<Answer> answers = answerService.getAllAnswerByQuestionId(Integer.valueOf(id));
//        return  ResponseEntity.ok(answers);
//    }
//
//    @RequestMapping(value = "",method = RequestMethod.POST)
//    public Answer saveAnswer(@RequestBody Answer answer){
//        answer=answerService.saveAnswer(answer);
//        return answer;
//    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public void deleteAnswer(@RequestParam Integer id){
        answerService.deleteById(id);
    }
}
