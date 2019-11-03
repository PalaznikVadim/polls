package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;
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

    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public ResponseEntity<List<Poll>> getAllPollsByUserId(@RequestParam User user) {
        List<Poll> polls = pollService.findAllByUser(user);
        return ResponseEntity.ok(polls);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Optional<Poll> getPollById(@RequestParam Integer id) {
        return pollService.findPollById(Integer.valueOf(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    public Poll saveUser(@RequestBody Poll poll) {
        return pollService.save(poll);
    }
}
