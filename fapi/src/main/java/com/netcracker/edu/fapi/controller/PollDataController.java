package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.converters.PollConverter;
import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ClonePoll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.PollService;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.service.impl.PollServiceImpl;
import com.netcracker.edu.fapi.validators.PollValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
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

    @RequestMapping("/user")
    public Page<ViewPoll> getPolls(@RequestParam Integer userId,String select, int page,int size,String sort,String order) {
        return pollService.getPolls(userId,select,page,size,sort,order);
        //return pollService.findAllByUserId(userId,page,size,sort,order);
    }

    @RequestMapping("/search/{substr}")
    public Page<ViewPoll> searchPollBySubstr(@PathVariable String substr,@RequestParam Integer idUser, Integer page,Integer size,String sort,String order){
        return pollService.searchBySubstr(substr,idUser,page,size,sort,order);
    }

    @RequestMapping("/id")
    public ViewPoll getPollById(@RequestParam String id) {
        return pollService.findById(Integer.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> savePoll(@RequestBody ViewPoll viewPoll /*todo server validation*/) {
        return pollService.save(viewPoll);
    }

    @RequestMapping(value = "/template",method = RequestMethod.GET)
    public List<ViewPoll> getAllTemplateByTheme(@RequestParam String theme){
        return pollService.findAllTemplateByTheme(theme);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deletePoll(@RequestParam String id) {
        pollService.deletePoll(Integer.valueOf(id));
    }

    @RequestMapping(value = "/clone",method = RequestMethod.POST)
    public ViewPoll clone(@RequestBody ClonePoll clonePoll){
        return pollService.clonePoll(clonePoll);
    }

    @RequestMapping(value = "/submit/{id}",method = RequestMethod.POST)
    public ViewPoll submitPoll(@PathVariable Integer id){
        return pollService.submitPoll(id);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ViewPoll getByLink(@RequestParam String link){
        return pollService.findByLink(link);
    }


}
