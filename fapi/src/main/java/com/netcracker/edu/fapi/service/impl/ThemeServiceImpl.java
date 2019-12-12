package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Theme;
import com.netcracker.edu.fapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Theme> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        Theme[] themesResponse = restTemplate.getForObject(backendServerUrl + "/api/theme", Theme[].class);
        return themesResponse == null ? Collections.emptyList() : Arrays.asList(themesResponse);
    }

    @Override
    public Theme findThemeById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"api/theme/id?id="+id, Theme.class);
    }

    @Override
    public Theme findThemeByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"api/theme/name?name="+name, Theme.class);
    }

    @Override
    public String[] findAllTemplateThemes() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/theme/template",String[].class);
    }


    @Override
    public ResponseEntity<?> save(String theme) {
        if (theme != null) {
            RestTemplate restTemplate = new RestTemplate();
            return ResponseEntity.ok(restTemplate.postForEntity(backendServerUrl + "/api/theme", theme, Theme.class).getBody());
        }
        return null;
    }

    @Override
    public String[] getUserPollThemes(Integer idUser) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/theme/userId?userId="+idUser,String[].class);
    }
}
