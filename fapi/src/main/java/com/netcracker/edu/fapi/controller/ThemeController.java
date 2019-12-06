package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Theme;
import com.netcracker.edu.fapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<Theme> getAllTheme(){
        return (List<Theme>) themeService.findAll();
    }

    @GetMapping("/id")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public Theme getThemeById(@RequestParam String id){ return themeService.findThemeById(Integer.valueOf(id));}

    @GetMapping("/name")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public Theme getThemeByName(@RequestParam String name){ return themeService.findThemeByName(name);}

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody String titleTheme /*todo server validation*/) {
        return themeService.save(titleTheme);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/template",method = RequestMethod.GET)
    public String[] getTemplateThemes(){
        return themeService.findAllTemplateThemes();
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/userId",method = RequestMethod.GET)
    public String[] getUserPollThemes(@RequestParam Integer userId){
        return themeService.getUserPollThemes(userId);
    }
}
