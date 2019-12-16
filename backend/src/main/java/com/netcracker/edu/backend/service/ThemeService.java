package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {

    Iterable<Theme> findAll();

    Optional<Theme> findThemeById(int id);

    Theme findThemeByName(String name);

    List<String> findAllTemplateThemes();

    Theme save(String theme);

    List<String> findAllByIdUser(int idUser);
}
