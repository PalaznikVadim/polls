package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Page<Poll> getAllPollByUserId(@RequestParam Integer userId,String theme,String substr,int page,int size,String sort,String order){
        Page<Poll> polls = pollService.findAllByUserId(userId,theme,substr,page,size,sort,order);
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

    @RequestMapping(value = "drafts",method = RequestMethod.GET)
    public Page<Poll> getUserDrafts(@RequestParam Integer idUser,String theme,String substr,Integer page,Integer size,String sort,String order){
        return pollService.findDraftsByUserId(idUser,theme,substr,page,size,sort,order);
    }

    @RequestMapping(value = "activePolls",method = RequestMethod.GET)
    public Page<Poll> getUserActivePolls(@RequestParam Integer idUser,String theme,String substr,Integer page,Integer size,String sort,String order){
        return pollService.findActivePollsByUserId(idUser,theme,substr,page,size,sort,order);
    }

//    @RequestMapping(value = "theme",method = RequestMethod.GET)
//    public Page<Poll> getPollsByTheme(@RequestParam Integer idUser, String theme, String substr, Integer page,Integer size,String sort, String order){
//        return pollService.findAllByTheme(theme,substr,idUser,page,size,sort,order);
//    }
}
