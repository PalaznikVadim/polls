package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.viewModels.ClonePoll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poll")
public class PollDataController {

    @Autowired
    private PollService pollService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/user")
    public Page<ViewPoll> getPolls(@RequestParam int userId, String select, String theme, String substr,
                                   int page, int size, String sort, String order) {
        return pollService.getPolls(userId, select, theme, substr, page, size, sort, order);
    }

    @RequestMapping("/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ViewPoll getPollById(@PathVariable int id) {
        return pollService.findById(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> savePoll(@RequestBody ViewPoll viewPoll) {
        return pollService.save(viewPoll);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public List<ViewPoll> getAllTemplateByTheme(@RequestParam String theme) {
        return pollService.findAllTemplateByTheme(theme);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePoll(@PathVariable int id) {
        pollService.deletePoll(id);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/clone", method = RequestMethod.POST)
    public ViewPoll clone(@RequestBody ClonePoll clonePoll) {
        return pollService.clonePoll(clonePoll);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/submit/{id}", method = RequestMethod.POST)
    public ViewPoll submitPoll(@PathVariable int id) {
        return pollService.submitPoll(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ViewPoll getByLink(@RequestParam String link) {
        return pollService.findByLink(link);
    }


}
