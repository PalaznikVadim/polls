package com.netcracker.edu.fapi.validators;


import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        System.out.println("date" + user.getDateOfBirth());

        String regExEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (!user.getEmail().matches(regExEmail)) {
            errors.rejectValue("email", null,
                    "Incorrect format field email!Recheck this field(Example: cat@mail.com)");
        }

        if (user.getEmail().length() > 25) {
            errors.rejectValue("email", null,
                    "Incorrect field email length!(length < 25 letter)");
        }

        if (!errors.hasFieldErrors("email")) {
            User userWithEmail = userService.getByEmail(user.getEmail());
            if (userWithEmail != null) {
                errors.rejectValue("email", null,
                        "This email is busy. Recheck data. ");
            }
        }


        if (user.getName().length() > 20 || user.getName().length() < 2)
            errors.rejectValue("name", null, "Incorrect field length( < 20 letter)!");
        if (user.getName().contains(" "))
            errors.rejectValue("name", null, "Field has whitespace!");

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
            if (user.getDateOfBirth().before(formatter.parse("01-01-1950"))
                    || user.getDateOfBirth().after(new Date())) {
                errors.rejectValue("dateOfBirth", null, "Invalid date range!");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (user.getSurname().length() > 20 || user.getSurname().length() < 2)
            errors.rejectValue("surname", null, "Incorrect field length( < 20 letter)!");
        if (user.getSurname().contains(" "))
            errors.rejectValue("surname", null, "Field has whitespace!");


        if (user.getPassword().length() > 25 || user.getPassword().length() < 2) {
            errors.rejectValue("password", null, "Incorrect field length( < 25 letter)!");
        }
        if (user.getPassword().contains(" "))
            errors.rejectValue("password", null, "Field has whitespace!");

    }
}
