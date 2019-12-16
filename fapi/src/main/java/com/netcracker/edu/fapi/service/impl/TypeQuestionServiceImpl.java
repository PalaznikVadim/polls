package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.TypeQuestion;
import com.netcracker.edu.fapi.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class TypeQuestionServiceImpl implements TypeQuestionService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<TypeQuestion> getAllTypes() {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(restTemplate.getForObject(backendServerUrl + "/api/type", TypeQuestion[].class));
    }

    @Override
    public TypeQuestion getById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/type/" + id, TypeQuestion.class);
    }

    @Override
    public int getIdByType(String type) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/type/type?type=" + type, Integer.class);
    }

    @Override
    public List<String> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(restTemplate.getForObject(backendServerUrl + "/api/type/all", String[].class));
    }


}
