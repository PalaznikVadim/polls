package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ResponseEntity<List<Poll>> getAllPollByUserId(@RequestParam String userId){
        List<Poll> polls = pollService.findAllByUserId(Integer.valueOf(userId));
        return ResponseEntity.ok(polls);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Optional<Poll> getPollById(@RequestParam String id) {
        return pollService.findPollById(Integer.valueOf(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    public Poll saveUser(@RequestBody Poll poll) {
        return pollService.save(poll);
    }

    @RequestMapping(value = "/id",method = RequestMethod.DELETE)
    public void deletePoll(@RequestParam String id) {
        pollService.delete(Integer.valueOf(id));
    }
}
