package com.netcracker.edu.fapi.validators;

import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;


@Service
public class QuestionValidator implements Validator {

    @Autowired
    private TypeQuestionService typeQuestionService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ViewQuestion.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ViewQuestion question = (ViewQuestion) object;

        if (question.getTextTitle().length() > 60 || question.getTextTitle().length() < 2)
            errors.rejectValue("textTitle", String.valueOf(question.getId()),
                    "Incorrect length field(3 <...< 60 charaсters)");

        List<String> typesOfQuestion = typeQuestionService.getAll();
        if (!typesOfQuestion.contains(question.getType())) {
            errors.rejectValue("type", null, "This type is not found from the available");
        }

        List<String> reqs = Arrays.asList("Yes", "No");
        if (!reqs.contains(question.getRequired())) {
            errors.rejectValue("required", null,
                    "This required is not found from the available");
        }


    }
}
