package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.Stats;
import com.netcracker.edu.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @RequestMapping(value = "/answer",method = RequestMethod.GET)
    public Stats getByIdAnswer(@RequestParam Integer idAnswer){
        return statsService.getByIdAnswer(idAnswer);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Stats save(@RequestBody Stats stats){
        return statsService.save(stats);
    }

}
