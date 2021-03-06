package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.consts.FapiConsts;
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
    public ViewPoll findById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Poll poll = restTemplate.getForObject(backendServerUrl + "api/poll/" + id, Poll.class);
        return pollConverter.convertPollToViewPoll(poll);
    }

    @Override
    public Page<ViewPoll> findAllByUserId(int userId, String theme, String substr,
                                          int page, int size, String sort, String order) {
        RestTemplate restTemplate = new RestTemplate();
        Page<Poll> polls = restTemplate.getForObject(backendServerUrl + "api/poll/user?userId=" + userId
                + "&theme=" + theme + "&substr=" + substr + "&page=" + page
                + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);

        Pageable pageable = createPageable(page, size, sort, order);
        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

    @Override
    public List<ViewPoll> findAllTemplateByTheme(String theme) {
        RestTemplate restTemplate = new RestTemplate();
        Poll[] polls = restTemplate.getForObject(backendServerUrl + "/api/poll/template?theme=" + theme, Poll[].class);
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
    public void deletePoll(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/poll/" + id);
    }

    @Override
    public ViewPoll findByLink(String link) {
        RestTemplate restTemplate = new RestTemplate();
        Poll poll = restTemplate.getForObject(backendServerUrl + "/api/poll?link=" + link, Poll.class);
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
            Question question = questionConverter.convertViewQuestToQuestion(viewQuestion);
            question = restTemplate.postForEntity(backendServerUrl + "/api/question", question, Question.class).getBody();
            for (Answer answer : answers) {
                answer.setIdQuestion(question.getId());
                answer.setId(0);
                answerService.saveAnswer(answer);
            }
        }
        return pollConverter.convertPollToViewPoll(poll);
    }

    @Override
    public ViewPoll submitPoll(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Poll poll = restTemplate.getForObject(backendServerUrl + "api/poll/" + id, Poll.class);
        poll.setLink(poll.hashCode() + "id" + poll.getId());
        poll = poll = restTemplate.postForEntity(backendServerUrl + "/api/poll", poll, Poll.class).getBody();

        return pollConverter.convertPollToViewPoll(poll);
    }

    @Override
    public Page<ViewPoll> findDraftsByUserId(int idUser, String theme, String substr, int page, int size,
                                             String sort, String order) {
        RestTemplate restTemplate = new RestTemplate();
        Page<Poll> polls = restTemplate.getForObject(backendServerUrl + "api/poll/drafts?idUser=" + idUser +
                "&theme=" + theme + "&substr=" + substr + "&page=" + page
                + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);

        Pageable pageable = createPageable(page, size, sort, order);
        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

    @Override
    public Page<ViewPoll> findActivePollsByUserId(int idUser, String theme, String substr,
                                                  int page, int size, String sort, String order) {
        System.out.println(substr);
        RestTemplate restTemplate = new RestTemplate();
        Page<Poll> polls = restTemplate.getForObject(backendServerUrl + "api/poll/activePolls?idUser=" + idUser +
                "&theme=" + theme + "&substr=" + substr + "&page=" + page
                + "&size=" + size + "&sort=" + sort + "&order=" + order, RestPageImpl.class);

        Pageable pageable = createPageable(page, size, sort, order);
        Page<ViewPoll> viewPollPage = PageableExecutionUtils.getPage(
                pollConverter.collectionTransform.apply(polls.getContent()),
                pageable,
                polls::getTotalElements);

        return viewPollPage;
    }

    @Override
    public Page<ViewPoll> getPolls(int userId, String select, String theme, String substr,
                                   int page, int size, String sort, String order) {

        switch (select){
            case "drafts":
                return findDraftsByUserId(userId, theme, substr, page, size, sort, order);
            case "activePolls":
                return findActivePollsByUserId(userId, theme, substr, page, size, sort, order);
            case "all":
                return findAllByUserId(userId, theme, substr, page, size, sort, order);
        }

        return null;
    }

    private Pageable createPageable(int page, int size, String sort, String order) {
        Pageable pageable;
        if (order.toLowerCase().contains(FapiConsts.ASC)) {
            pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return pageable;
    }

}
