package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.PollConverter;
import com.netcracker.edu.fapi.converters.QuestionConverter;
import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ClonePoll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.PollService;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.validators.PollValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollServiceImpl implements PollService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private PollConverter pollConverter;
    @Autowired
    private PollValidator pollValidator;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionConverter questionConverter;

    @Override
    public ViewPoll findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Poll poll = restTemplate.getForObject(backendServerUrl + "api/poll/id?id=" + id, Poll.class);
        return pollConverter.convertPollToViewPoll(poll);
    }

    @Override
    public Page<ViewPoll> findAllByUserId(Integer userId,String substr, int page, int size, String sort, String order) {
        RestTemplate restTemplate = new RestTemplate();
        Page<Poll> polls= restTemplate.getForObject(backendServerUrl + "api/poll/user?userId=" + userId +
                "&substr="+substr+"&page=" + page
                + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);

        Pageable pageable = createPageable(page,size,sort,order);
        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

//    @Override
//    public Page<ViewPoll> searchBySubstr(String substr, Integer idUser, Integer page, Integer size, String sort, String order) {
//        RestTemplate restTemplate = new RestTemplate();
//        Page<Poll> polls= restTemplate.getForObject(backendServerUrl + "/api/poll/search/" + substr + "?idUser=" + idUser +
//                "&page=" + page + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);
//
//        Pageable pageable = createPageable(page,size,sort,order);
//        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
//                pollConverter.collectionTransform.apply(polls.getContent()),
//                pageable,
//                polls::getTotalElements);
//
//        return viewPollPage;
//    }

    @Override
    public List<ViewPoll> findAllTemplateByTheme(String theme) {
        RestTemplate restTemplate = new RestTemplate();
        Poll[] polls= restTemplate.getForObject(backendServerUrl + "/api/poll/template?theme=" + theme, Poll[].class);
//        List<ViewPoll> viewPolls = new ArrayList<>();
//        for (Poll poll : polls) {
//            viewPolls.add(pollConverter.convertPollToViewPoll(poll));
//        }
        return Arrays.stream(polls).map(poll -> pollConverter.convertPollToViewPoll(poll)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> save(ViewPoll viewPoll) {
        DataBinder dataBinder = new DataBinder(viewPoll);
        dataBinder.addValidators(pollValidator);
        dataBinder.validate();

        if (dataBinder.getBindingResult().hasErrors()) {
            List<ObjectError> errorList = dataBinder.getBindingResult().getAllErrors();
            return ResponseEntity.badRequest().body(errorList);
        } else {
            viewPoll.setDate(new Date());
            Poll poll = pollConverter.convertViewPollToPoll(viewPoll);
            RestTemplate restTemplate = new RestTemplate();
            poll = restTemplate.postForEntity(backendServerUrl + "/api/poll", poll, Poll.class).getBody();
            return ResponseEntity.ok().body(pollConverter.convertPollToViewPoll(poll));
        }
    }

    @Override
    public void deletePoll(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/poll/delete?id=" + id);
    }

    @Override
    public ViewPoll findByLink(String link) {
        RestTemplate restTemplate = new RestTemplate();
        Poll poll=restTemplate.getForObject(backendServerUrl + "/api/poll?link=" + link, Poll.class);
        return pollConverter.convertPollToViewPoll(poll);
    }

    @Override
    public ViewPoll clonePoll(ClonePoll clonePoll) {
        Poll poll = pollConverter.convertViewPollToPoll(findById(clonePoll.getId()));
        List<ViewQuestion> questions = questionService.getAllQuestionByIdPoll(clonePoll.getId());

        poll.setId(0);
        poll.setIdUser(clonePoll.getIdUser());
        poll.setShared("No");
        RestTemplate restTemplate = new RestTemplate();
        poll = restTemplate.postForEntity(backendServerUrl + "/api/poll", poll, Poll.class).getBody();

        for (ViewQuestion viewQuestion : questions) {
            List<Answer> answers = answerService.getAllAnswerByQuestionId(viewQuestion.getId());
            viewQuestion.setIdPoll(poll.getId());
            viewQuestion.setId(0);
            Question question=questionConverter.convertViewQuestToQuestion(viewQuestion);
            question=restTemplate.postForEntity(backendServerUrl+"/api/question",question,Question.class).getBody();
            for (Answer answer : answers) {
                answer.setIdQuestion(question.getId());
                answer.setId(0);
                answerService.saveAnswer(answer);
            }
        }
//        questions.stream().map(question ->{
//            List<Answer> answers = answerService.getAllAnswerByQuestionId(question.getId());
//            question.setIdPoll(poll.getId());
//            viewQuestion.setId(0);
//            Question question=questionConverter.convertViewQuestToQuestion(viewQuestion);
//            question=restTemplate.postForEntity(backendServerUrl+"/api/question",question,Question.class).getBody();
//        })

        return pollConverter.convertPollToViewPoll(poll);
    }

    @Override
    public ViewPoll submitPoll(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        Poll poll = restTemplate.getForObject(backendServerUrl + "api/poll/id?id=" + id, Poll.class);
        poll.setLink(poll.hashCode()+"id"+poll.getId());
        poll=poll = restTemplate.postForEntity(backendServerUrl + "/api/poll", poll, Poll.class).getBody();

        return pollConverter.convertPollToViewPoll(poll);
    }

    @Override
    public Page<ViewPoll> findDraftsByUserId(Integer idUser,String substr, Integer page, Integer size, String sort, String order) {
        System.out.println(substr);
        RestTemplate restTemplate = new RestTemplate();
        Page<Poll> polls= restTemplate.getForObject(backendServerUrl + "api/poll/drafts?idUser=" + idUser +
                "&substr="+substr+"&page=" + page
                + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);

        Pageable pageable = createPageable(page,size,sort,order);
        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

    @Override
    public Page<ViewPoll> findPollsByTheme(String theme,String substr, Integer idUser, Integer page, Integer size,
                                           String sort, String order) {
        System.out.println(substr);
        RestTemplate restTemplate = new RestTemplate();
        Page<Poll> polls= restTemplate.getForObject(backendServerUrl + "api/poll/theme?idUser=" + idUser+"&theme="+
                theme + "&substr="+substr + "&page=" + page
                + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);

        Pageable pageable = createPageable(page,size,sort,order);
        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

    @Override
    public Page<ViewPoll> findActivePollsByUserId(Integer idUser,String substr, Integer page, Integer size, String sort, String order) {
        System.out.println(substr);
        RestTemplate restTemplate = new RestTemplate();
        Page<Poll> polls= restTemplate.getForObject(backendServerUrl + "api/poll/activePolls?idUser=" + idUser +
                "&substr="+substr+"&page=" + page
                + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);

        Pageable pageable = createPageable(page,size,sort,order);
        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

    @Override
    public Page<ViewPoll> getPolls(Integer userId, String select,String substr, int page, int size, String sort, String order) {
        if(select.toLowerCase().equals("all")){
            return findAllByUserId(userId,substr,page,size,sort,order);
        }else if(select.toLowerCase().equals("drafts")){
            return findDraftsByUserId(userId,substr,page,size,sort,order);
        }else if(select.toLowerCase().equals("activePolls".toLowerCase())){
            return findActivePollsByUserId(userId,substr,page,size,sort,order);
        }else if(select.contains("theme_")){
            String theme=select.substring(select.indexOf("_")+1);
            return findPollsByTheme(theme,substr,userId,page,size,sort,order);
        }

        return null;
    }
    private Pageable createPageable(Integer page, Integer size, String sort, String order){
        Pageable pageable;
        if(order.toLowerCase().contains("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        }else{
            pageable=PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return pageable;
    }

}
