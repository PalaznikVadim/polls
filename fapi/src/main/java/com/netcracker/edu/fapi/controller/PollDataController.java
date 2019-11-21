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
    @Autowired
    private PollConverter pollConverter;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PollValidator pollValidator;

    @RequestMapping("/user")
    public Page<ViewPoll> getAllPolls(@RequestParam String userId,int page,int size,String sort,String order) {

        Page<Poll> polls= pollService.findAllByUserId(Integer.valueOf(userId), page, size, sort, order);
        List<ViewPoll> viewPolls=new ArrayList<>();

        Pageable pageable=new PageRequest(page,size, Sort.by(sort).ascending());

        Page<ViewPoll> viewPollPage= PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

    @RequestMapping("/id")
    public ViewPoll getPollById(@RequestParam String id) {
        Poll poll=pollService.findById(Integer.valueOf(id));
        System.out.println(poll.toString());
        ViewPoll viewPoll=pollConverter.convertPollToViewPoll(poll);
        return viewPoll;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> savePoll(@RequestBody ViewPoll viewPoll /*todo server validation*/) {
        DataBinder dataBinder=new DataBinder(viewPoll);
        dataBinder.addValidators(pollValidator);
        dataBinder.validate();

        if(dataBinder.getBindingResult().hasErrors()){
            List<ObjectError> errorList=dataBinder.getBindingResult().getAllErrors();
            return ResponseEntity.badRequest().body(errorList);
        }else {
            viewPoll.setDate(new Date());
            Poll poll=pollConverter.convertViewPollToPoll(viewPoll);
            poll=pollService.save(poll);
            return ResponseEntity.ok().body(pollConverter.convertPollToViewPoll(poll));
        }

    }

    @RequestMapping(value = "/template",method = RequestMethod.GET)
    public List<ViewPoll> getAllTemplateByTheme(@RequestParam String theme){
        Poll[] polls=pollService.findAllTemplateByTheme(theme);
        List<ViewPoll> viewPolls=new ArrayList<>();
        for(Poll poll:polls){
            viewPolls.add(pollConverter.convertPollToViewPoll(poll));
        }
        return viewPolls;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deletePoll(@RequestParam String id) {
        pollService.deletePoll(Integer.valueOf(id));
    }

    @RequestMapping(value = "/clone",method = RequestMethod.POST)
    public ViewPoll clone(@RequestBody ClonePoll clonePoll){
        Poll poll=pollService.findById(clonePoll.getId());
        List<Question> questions =questionService.getAllQuestionByIdPoll(clonePoll.getId());

        poll.setId(0);
        poll.setIdUser(clonePoll.getIdUser());
        poll.setShared("No");

        poll=pollService.save(poll);

        for(Question question:questions){
            List<Answer> answers=answerService.getAllAnswerByQuestionId(question.getId());
            question.setIdPoll(poll.getId());
            question.setId(0);
            question=questionService.save(question);
            for(Answer answer:answers){
                answer.setIdQuestion(question.getId());
                answerService.saveAnswer(answer);
            }
        }
        return pollConverter.convertPollToViewPoll(poll);
    }

    @RequestMapping(value = "/submit/{id}",method = RequestMethod.POST)
    public ViewPoll submitPoll(@PathVariable Integer id){
        Poll poll=pollService.findById(id);
        poll.setLink(poll.hashCode()+"id"+poll.getId());
        poll=pollService.save(poll);

        return pollConverter.convertPollToViewPoll(poll);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ViewPoll getByLink(@RequestParam String link){
        return pollConverter.convertPollToViewPoll(pollService.findByLink(link));
    }

}
