package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.TypeQuestion;
import com.netcracker.edu.fapi.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TypeQuestionServiceImpl implements TypeQuestionService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public TypeQuestion[] getAllTypes() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/type",TypeQuestion[].class);
    }
}
