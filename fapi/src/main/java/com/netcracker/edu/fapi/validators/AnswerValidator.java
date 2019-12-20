package com.netcracker.edu.fapi.validators;

import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AnswerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ViewAnswer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ViewAnswer answer = (ViewAnswer) (target);
        answer.setText(answer.getText().trim());
        if (answer.getText().length() > 60 || answer.getText().length() < 1) {
            errors.rejectValue("text", String.valueOf(answer.getId()),
                    "Incorrect length the answer!(1<..<60 character)");
        }
    }
}
