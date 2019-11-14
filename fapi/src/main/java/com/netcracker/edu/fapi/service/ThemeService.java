package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Theme;

public interface ThemeService {
    Iterable<Theme> findAll();
    Theme findThemeById(Integer id);
    Theme findThemeByName(String name);
    Theme save(String theme);
}
