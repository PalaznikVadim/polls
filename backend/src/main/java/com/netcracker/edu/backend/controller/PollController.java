package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Page<Poll> getAllPollByUserId(@RequestParam Integer userId,int page,int size,String sort,String order){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sort).ascending());

        Page<Poll> polls = pollService.findAllByUserId(userId,pageable);
        return polls;
    }

    @RequestMapping(value = "/search/{substr}",method = RequestMethod.GET)
    public Page<Poll> searchPollsBySubstr(@PathVariable String substr,@RequestParam Integer idUser,Integer page,Integer size,String sort,String order){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sort).ascending());

        Page<Poll> polls = pollService.searchBySubstr(substr,idUser,pageable);
        return polls;
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Optional<Poll> getPollById(@RequestParam String id) {
        return pollService.findPollById(Integer.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Poll saveUser(@RequestBody Poll poll) {
        return pollService.save(poll);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void deletePoll(@RequestParam String id) {
        pollService.delete(Integer.valueOf(id));
    }

    @RequestMapping(value = "template",method = RequestMethod.GET)
    public List<Poll> getAllTemplateByTheme(@RequestParam String theme){
        return pollService.findAllTemplateByTheme(theme);
    }



    @RequestMapping(value = "",method = RequestMethod.GET)
    public Poll getByLink(@RequestParam String link){
        return pollService.findByLink(link);
    }
}
