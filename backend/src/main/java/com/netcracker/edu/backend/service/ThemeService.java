package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Theme;

import java.util.Optional;

public interface ThemeService {

    Iterable<Theme> findAll();
    Optional<Theme> findThemeById(Integer id);
    Theme findThemeByName(String name);
    Theme save(Theme theme);
}