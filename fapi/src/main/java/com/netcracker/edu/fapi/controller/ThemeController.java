package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Theme;
import com.netcracker.edu.fapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping
    public List<Theme> getAllTheme(){
        return (List<Theme>) themeService.findAll();
    }

    @GetMapping("/id")
    public Theme getThemeById(@RequestParam String id){ return themeService.findThemeById(Integer.valueOf(id));}

    @GetMapping("/name")
    public Theme getThemeByName(@RequestParam String name){ return themeService.findThemeByName(name);}

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody String titleTheme /*todo server validation*/) {
        return themeService.save(titleTheme);
    }

    @RequestMapping(value = "/template",method = RequestMethod.GET)
    public String[] getTemplateThemes(){
        return themeService.findAllTemplateThemes();
    }

    @RequestMapping(value = "/userId",method = RequestMethod.GET)
    public String[] getUserPollThemes(@RequestParam Integer userId){
        return themeService.getUserPollThemes(userId);
    }
}
