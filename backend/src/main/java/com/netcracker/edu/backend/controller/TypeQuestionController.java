package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.TypeQuestion;
import com.netcracker.edu.backend.entity.enums.Type;
import com.netcracker.edu.backend.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public Optional<TypeQuestion> getTypeById(@RequestParam Integer id){
        return typeQuestionService.getTypeById(id);
    }

    @RequestMapping(value = "/type",method = RequestMethod.GET)
    public Integer getIdByType(@RequestParam Type type){
        return typeQuestionService.getIdByType(type);
    }


}
