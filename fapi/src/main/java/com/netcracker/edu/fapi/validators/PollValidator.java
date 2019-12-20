package com.netcracker.edu.fapi.validators;

import com.netcracker.edu.fapi.models.Theme;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Service
public class PollValidator implements Validator {

    @Autowired
    private ThemeService themeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ViewPoll.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ViewPoll poll = (ViewPoll) target;

        poll.setName(poll.getName().trim());
        if(poll.getDescription()!=null) {
            poll.setDescription(poll.getDescription().trim());
        }

        if (poll.getName().length() > 20 || poll.getName().length() < 1) {
            errors.rejectValue("name", null, "Incorrect length field(1 <...< 20 charaсters)");
        }


        List<Theme> themes = themeService.findAll();
        long count = themes.stream().map(theme -> theme.getName()).filter(theme -> theme.equals(poll.getTheme())).count();
        if (count == 0)
            errors.rejectValue("theme", null, "This theme is not found from the available");


        if (poll.getDescription() != null) {
            if (poll.getDescription().length() > 201) {
                errors.rejectValue("description", "Incorrect length field(...< 200 charaсters)");
            }
        }
    }
}
