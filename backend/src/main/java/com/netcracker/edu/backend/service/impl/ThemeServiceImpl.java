package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Theme;
import com.netcracker.edu.backend.repository.ThemeRepository;
import com.netcracker.edu.backend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public Iterable<Theme> findAll() {
        return themeRepository.findAll();
    }

    @Override
    public Optional<Theme> findThemeById(Integer id) {
        return themeRepository.findById(id);
    }

    @Override
    public Theme findThemeByName(String name) {
        return themeRepository.findThemeByName(name);
    }

    @Override
    public String[] findAllTemplateThemes() {
        return themeRepository.findAllTemplateTheme();
    }

    @Override
    public Theme save(String theme) {
        Theme themeEntity=new Theme();
        themeEntity.setName(theme);
        return themeRepository.save(themeEntity);
    }
}
