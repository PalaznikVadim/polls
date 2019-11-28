package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Theme;
import com.netcracker.edu.backend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Theme> getAllThemes() {
        return  (List<Theme>)themeService.findAll();
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Optional<Theme> getThemeById(@RequestParam String id) {
        return  themeService.findThemeById(Integer.valueOf(id));
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public Theme getThemeByName(@RequestParam String name) {
        return  themeService.findThemeByName(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Theme saveTheme(@RequestBody String theme) {
        return themeService.save(theme);
    }

    @RequestMapping(value = "/template",method = RequestMethod.GET)
    public String[] getTemplateThemes(){
        return  themeService.findAllTemplateThemes();
    }

    @RequestMapping(value = "/userId",method = RequestMethod.GET)
    public String[] getUserPollThemes(@RequestParam Integer userId){
        return themeService.findAllByIdUser(userId);
    }
}
