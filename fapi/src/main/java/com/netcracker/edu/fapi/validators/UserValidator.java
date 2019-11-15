package com.netcracker.edu.fapi.validators;


import com.netcracker.edu.fapi.models.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","Password is empty or has whitespace");
        User user=(User) obj;


        String regExEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(!user.getEmail().matches(regExEmail)){
            errors.rejectValue("email",null,"Incorrect format field email!Recheck this field(Example: cat@mail.com)");
        }
        if(user.getEmail().length()>25){
            errors.rejectValue("email",null,"Incorrect field email length!(length < 25 letter)");
        }
        if(user.getName().length()>20){
            errors.rejectValue("name",null,"Incorrect field length( < 20 letter)!");
        }
        if(user.getSurname().length()>20){
            errors.rejectValue("surname",null,"Incorrect field length( < 20 letter)!");
        }
        if(user.getPassword().length()>25){
            errors.rejectValue("password",null,"Incorrect field length( < 25 letter)!");
        }
    }
}
