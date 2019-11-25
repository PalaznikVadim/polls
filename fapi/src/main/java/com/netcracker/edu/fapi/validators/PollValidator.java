package com.netcracker.edu.fapi.validators;

import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PollValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ViewPoll.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ViewPoll poll=(ViewPoll)target;

        if (poll.getName().length() > 20 || poll.getName().length() < 1)
            errors.rejectValue("name", null, "Incorrect length field(1 <...< 20 charaсters)");

        if(poll.getDescription()!=null)
        if(poll.getDescription().length()>201)
            errors.rejectValue("description","Incorrect length field(...< 200 charaсters)");
    }
}
