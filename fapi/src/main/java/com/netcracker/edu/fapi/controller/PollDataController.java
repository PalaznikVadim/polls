package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.PollModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ba")
public class PollDataController {

    @RequestMapping
    public List<PollModel> getAllPolls() {
        List<PollModel> list=new ArrayList<>();
        list.add((new PollModel(1,"poll1",new Date())));
        list.add((new PollModel(2,"poll2",new Date())));

        return  list;
    }
}
