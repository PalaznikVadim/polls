package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.TypeQuestion;
import com.netcracker.edu.backend.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/type")
public class TypeQuestionController {

    @Autowired
    private TypeQuestionService typeQuestionService;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<TypeQuestion> getAllTypes(){
        List<TypeQuestion> typeQuestions=typeQuestionService.getAllTypeQuestion();
        System.out.println(typeQuestions.toString());
        return typeQuestions;
    }


}
