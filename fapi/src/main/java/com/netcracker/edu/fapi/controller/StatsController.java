package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.Stats;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private StatsService statsService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<ViewQuestion> getPollStats(@RequestParam Integer idPoll){
        return questionService.getPollStats(idPoll);
    }

    @RequestMapping(value = "/stat",method = RequestMethod.GET)
    public Stats getStat(@RequestParam Integer idAnswer){
        return statsService.getByIdAnswer(idAnswer);
    }

}
