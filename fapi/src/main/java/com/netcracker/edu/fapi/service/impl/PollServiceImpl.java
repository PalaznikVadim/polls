package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.PollConverter;
import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.PollService;
import com.netcracker.edu.fapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public Poll findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Poll poll=restTemplate.getForObject(backendServerUrl+"api/poll/id?id="+id,Poll.class);
        return poll;
    }

    @Override
    public Poll[] findAllByUserId(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"api/poll/user?userId="+userId,Poll[].class);
    }

    @Override
    public Poll save(Poll poll) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/poll", poll, Poll.class).getBody();

    }

    @Override
    public void deletePoll(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/poll/delete?id=" + id);
    }


}
