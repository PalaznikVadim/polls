package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Theme;
import com.netcracker.edu.fapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Iterable<Theme> findAll() {
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
    public Theme save(Theme theme) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/theme", theme, Theme.class).getBody();
    }
}
