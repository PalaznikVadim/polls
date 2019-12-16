package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.TypeQuestion;
import com.netcracker.edu.backend.entity.enums.Type;
import com.netcracker.edu.backend.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type")
public class TypeQuestionController {

    @Autowired
    private TypeQuestionService typeQuestionService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TypeQuestion> getAllTypes() {
        List<TypeQuestion> typeQuestions = typeQuestionService.getAllTypeQuestion();
        return typeQuestions;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<TypeQuestion> getTypeById(@PathVariable int id) {
        return typeQuestionService.getTypeById(id);
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public Integer getIdByType(@RequestParam Type type) {
        return typeQuestionService.getIdByType(type);
    }

    @GetMapping("/all")
    public List<Type> getAll() {
        return typeQuestionService.getAllType();
    }

//return Arrays.asList(restTemplate.getForObject(backendServerUrl + "/api/type/all", String[].class));
}
