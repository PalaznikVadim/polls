package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Theme;
import com.netcracker.edu.fapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
    public Theme findThemeById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/theme/" + id, Theme.class);
    }

    @Override
    public Theme findThemeByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/theme/name?name=" + name, Theme.class);
    }

    @Override
    public List<String> findAllTemplateThemes() {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(restTemplate.getForObject(backendServerUrl + "/api/theme/template", String[].class));
    }


    @Override
    public ResponseEntity<?> save(String theme) {
        Map<String, List<String>> errors = validateTheme(theme);

        if (errors.size() == 0) {
            RestTemplate restTemplate = new RestTemplate();
            return ResponseEntity.ok(restTemplate.postForEntity(backendServerUrl + "/api/theme",
                    theme, Theme.class).getBody());
        } else {
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @Override
    public List<String> getUserPollThemes(int idUser) {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(restTemplate.getForObject(backendServerUrl + "/api/theme/user?userId="
                + idUser, String[].class));
    }

    private Map<String, List<String>> validateTheme(String theme) {
        Map<String, List<String>> errors = new HashMap<>();
        List<String> themeErrors = new ArrayList<>();
        if (theme == null) {
            themeErrors.add("The field is empty!");
        }

        if (theme.length() > 20) {
            themeErrors.add("The field length is more than 20 letter!");
        }
        if (themeErrors.size() != 0) {
            errors.put("themeName", themeErrors);
        }
        return errors;
    }
}
