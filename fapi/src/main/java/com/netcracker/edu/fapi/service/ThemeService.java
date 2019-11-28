package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Theme;
import org.springframework.http.ResponseEntity;

public interface ThemeService {
    Iterable<Theme> findAll();
    Theme findThemeById(Integer id);
    Theme findThemeByName(String name);
    String[] findAllTemplateThemes();
    ResponseEntity<?> save(String theme);
    String[] getUserPollThemes(Integer idUser);
}
