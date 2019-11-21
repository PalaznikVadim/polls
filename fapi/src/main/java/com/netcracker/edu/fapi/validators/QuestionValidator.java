package com.netcracker.edu.fapi.validators;

import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



@Service
public class QuestionValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ViewQuestion.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ViewQuestion question = (ViewQuestion) object;

        if (question.getTextTitle().length() > 60 || question.getTextTitle().length() < 2)
            errors.rejectValue("textTitle", String.valueOf(question.getId()), "Incorrect length field(3 <...< 60 charaсters)");

    }
}
