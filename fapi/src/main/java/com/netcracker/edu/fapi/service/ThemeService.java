package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Theme;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ThemeService {
    List<Theme> findAll();

    Theme findThemeById(int id);

    Theme findThemeByName(String name);

    List<String> findAllTemplateThemes();

    ResponseEntity<?> save(String theme);

    List<String> getUserPollThemes(int idUser);
}
