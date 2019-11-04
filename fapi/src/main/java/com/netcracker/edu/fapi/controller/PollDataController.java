package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/poll")
public class PollDataController {

    @Autowired
    private PollService pollService;

    @RequestMapping
    public ResponseEntity<List<Poll>> getAllPolls(@RequestParam String userId) {
        return ResponseEntity.ok(pollService.findAllByUserId(Integer.valueOf(userId)));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Poll> getPollById(Integer id) {
        return ResponseEntity.ok(pollService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Poll> savePoll(@RequestBody Poll poll /*todo server validation*/) {
        if (poll != null) {
            return ResponseEntity.ok(pollService.save(poll));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePoll(@PathVariable String id) {
        pollService.deletePoll(Integer.valueOf(id));
    }








}
