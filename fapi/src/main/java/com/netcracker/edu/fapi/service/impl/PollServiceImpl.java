package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.PollService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
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
    public List<Poll> findAllByUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        Poll[] polls=restTemplate.getForObject(backendServerUrl+"api/poll/userId?userId="+user,Poll[].class);
        return polls == null ? Collections.emptyList() : Arrays.asList(polls);
    }

    @Override
    public Poll save(Poll poll) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/poll", poll, Poll.class).getBody();
    }

    @Override
    public void deletePoll(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/poll/" + id);
    }


}
