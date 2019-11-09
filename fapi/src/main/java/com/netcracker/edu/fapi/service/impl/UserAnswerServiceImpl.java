package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserAnswer;
import com.netcracker.edu.fapi.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public ResponseEntity<UserAnswer> save(UserAnswer userAnswer) {

        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl+"/api/userAnswer",userAnswer,UserAnswer.class);
    }

    @Override
    public Integer countSelected( Integer idAnswer) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/userAnswer/count?idAnswer="+idAnswer,Integer.class);
    }
}
