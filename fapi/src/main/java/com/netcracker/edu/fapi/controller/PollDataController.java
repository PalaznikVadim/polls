package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.converters.PollConverter;
import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.service.PollService;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/poll")
public class PollDataController {

    @Autowired
    private PollService pollService;
    @Autowired
    private PollConverter pollConverter;
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/user")
    public List<ViewPoll> getAllPolls(@RequestParam String userId) {

        List<Poll> polls= Arrays.asList(pollService.findAllByUserId(Integer.valueOf(userId)));
        List<ViewPoll> viewPolls=new ArrayList<>();

        for(Poll poll:polls){
            viewPolls.add(pollConverter.convertPollToViewPoll(poll));
        }

        return (List<ViewPoll>) viewPolls;
    }

    @RequestMapping("/id")
    public ViewPoll getPollById(@RequestParam String id) {
        Poll poll=pollService.findById(Integer.valueOf(id));
        ViewPoll viewPoll=pollConverter.convertPollToViewPoll(poll);
        return viewPoll;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ViewPoll savePoll(@RequestBody ViewPoll viewPoll /*todo server validation*/) {
        if (viewPoll != null) {
            viewPoll.setDate(new Date());
            Poll poll=pollConverter.convertViewPollToPoll(viewPoll);
            poll=pollService.save(poll);
            return pollConverter.convertPollToViewPoll(poll);
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deletePoll(@RequestParam String id) {
        pollService.deletePoll(Integer.valueOf(id));
    }

}
