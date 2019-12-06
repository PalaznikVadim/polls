package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.TypeQuestion;
import com.netcracker.edu.fapi.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/type")
public class TypeQuestionController {

    @Autowired
    private TypeQuestionService typeQuestionService;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "",method = RequestMethod.GET)
    public TypeQuestion[] getAllTypes(){
        return typeQuestionService.getAllTypes();
    }

}
